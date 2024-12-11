package demo.demo.controller;

import demo.demo.Module.Service;
import demo.demo.Module.User;
import demo.demo.Repository.BookingRepository;
import demo.demo.Repository.ServiceRepository;
import demo.demo.Repository.ServiceRequestRepository;
import demo.demo.Services.DashboardService;
import demo.demo.Services.ServiceService;
import demo.demo.jsonResponse.GetService;
import demo.demo.jsonResponse.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@RestController
@CrossOrigin("http://localhost:8080")
public class ServiceController {


        private final ServiceRepository serviceRepository;
        private final DashboardService dashboardService;
        private final BookingRepository bookingRepository;
        private final ServiceRequestRepository serviceRequestRepository;// Assuming a ServiceService is available to fetch services

        @Autowired
        public ServiceController(ServiceRepository serviceRepository, DashboardService dashboardService, BookingRepository bookingRepository, ServiceRequestRepository serviceRequestRepository) {
            this.serviceRepository = serviceRepository;

            this.dashboardService = dashboardService;
            this.bookingRepository = bookingRepository;
            this.serviceRequestRepository = serviceRequestRepository;
        }
    @GetMapping("/getAvailableServices/{hotelID}")
    public ResponseEntity<?> getService(@PathVariable int hotelID) {
        try {
            System.out.println("Received hotelID: " + hotelID);
            List<Service> availableServices = serviceRepository.getServicesForUser(hotelID);
            System.out.println(availableServices.size());
            return ResponseEntity.ok(availableServices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching services: " + e.getMessage());
        }
    }

    @PostMapping("/checkstatus")
    public ResponseEntity<?> getServices(@RequestBody GetService getService) {
        try {
            // Retrieve the start and end times for the specified service
            System.out.println(getService.getServiceID());
            times serviceTimes = serviceRepository.getServicesdates(getService.getServiceID());
            UUID serviceID = getService.getServiceID();
            LocalTime time = getService.getTime();
            System.out.println();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails obj = (UserDetails) auth.getPrincipal();
            String pEmail = obj.getUsername();
            User customer = dashboardService.getCustomerByEmail(pEmail);

            // Check if the provided time is within the service availability range
            if (time.isAfter(serviceTimes.getStart()) && time.isBefore(serviceTimes.getEnd())) {
                // Fetch the service price
                Double servicePrice = serviceTimes.getPrice();
                // Generate a request ID and store the service request
                String requestid = UUID.randomUUID().toString();
                serviceRequestRepository.addServiceRequest(requestid, serviceID, customer.getUserID(), getService.getDate(), getService.getTime());

                // Create a response object containing status and amount
                Map<String, Object> response = new HashMap<>();
                response.put("available", "Yes"); // Availability status
                response.put("amount", servicePrice);
                response.put("requestid", requestid);
                System.out.println(response);
                return ResponseEntity.ok(response); // Return response with amount
            } else {
                // Return a JSON response indicating unavailability
                Map<String, Object> response = new HashMap<>();
                response.put("available", "No");
                response.put("message", "The requested time is not within the available service hours.");
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            System.out.println(e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while fetching services.");
            errorResponse.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}


