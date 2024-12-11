package demo.demo.controller;

import demo.demo.Module.Booking;
import demo.demo.Module.Room;
import demo.demo.Module.RoomRequest;
import demo.demo.Module.User;
import demo.demo.Repository.BookingRepository;
import demo.demo.Repository.CancellationRepository;
import demo.demo.Repository.RoomRequestRepository;
import demo.demo.Repository.ServiceRequestRepository;
import demo.demo.Services.BillService;
import demo.demo.Services.BookingService;
import demo.demo.Services.DashboardService;
import demo.demo.Services.ServiceService;
import demo.demo.jsonResponse.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:8080")
public class BookingController {

    private final BookingService bookingService;
    private final BillService billService;
    private final DashboardService dashboardService;
    private final ServiceService serviceService;
    private final BookingRepository bookingRepository;
    private final RoomRequestRepository roomRequestRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final CancellationRepository cancellationRepository;

    @Autowired
    public BookingController(BookingService bookingService, BillService billService, DashboardService dashboardService, ServiceService serviceService, BookingRepository bookingRepository, RoomRequestRepository roomRequestRepository, ServiceRequestRepository serviceRequestRepository, CancellationRepository cancellationRepository) {
        this.bookingService = bookingService;
        this.billService = billService;
        this.dashboardService = dashboardService;
        this.serviceService = serviceService;
        this.bookingRepository = bookingRepository;
        this.roomRequestRepository = roomRequestRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.cancellationRepository = cancellationRepository;
    }

    @PostMapping("/dashboard/check")
    public ResponseEntity checkAvail(@RequestBody BookingRequest bookingRequest) {
        System.out.println(bookingRequest.getRoomType());
        System.out.println(bookingRequest.getCheckInDate());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String pEmail = userDetails.getUsername();
        System.out.println(bookingRequest.getNoOfGuests());
          int var=bookingService.getAvail(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate(), bookingRequest.getRoomType(),bookingRequest.getHotelId(),pEmail);
          System.out.println(var);
          if(var>0)
        {
            System.out.println(var);
            return ResponseEntity.ok("Rooms Available");
        }
        return ResponseEntity.badRequest().body("No rooms available");
    }

    @PostMapping("/dashboard/book")
    public ResponseEntity<?> makeBooking(@RequestBody BookingRequest bookingRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String pEmail = userDetails.getUsername();

            // Retrieve the customer information
            User customer = dashboardService.getCustomerByEmail(pEmail);

            // Get the room based on the booking request
            LocalDate checkInDate = bookingRequest.getCheckInDate();
            LocalDate checkOutDate = bookingRequest.getCheckOutDate();
            long no_of_days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            System.out.println("Hotel ID: " + bookingRequest.getHotelId());
            System.out.println("Check-in Date: " + checkInDate);
            System.out.println("Check-out Date: " + checkOutDate);

            Room room = bookingService.getRoomForBooking(bookingRequest);

            // Generate a new booking ID
            UUID roomRequestID = UUID.randomUUID();

            // Add the room request
            bookingRepository.addRoomRequest(roomRequestID,room.getRoomID(), checkInDate, checkOutDate, customer.getUserID());

            // Calculate the total cost
            double cost = room.getCost() * no_of_days;

            // Create a response object
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Booking created successfully.");
            response.put("cost", cost);
            response.put("roomRequestID", roomRequestID);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();

            // Return a failure response with the error message
            return ResponseEntity.status(500).body("Error creating booking: " + e.getMessage());
        }
    }

    @GetMapping("/getbookings")
    public List<GetBooking> getBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String pEmail = userDetails.getUsername();
        User customer = dashboardService.getCustomerByEmail(pEmail);
//        List<RoomRequest>=RoomRequestRepository.getallrooms(customer.getUserID());
        List<GetBooking> res = bookingService.getGetBookings(customer.getUserID());
//        System.out.println(res.get(0).getBookingID());
        return res;
    }
    @GetMapping("/getservicebookings")
    public List<GetServiceBook> serviceBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String pEmail = userDetails.getUsername();
        User customer = dashboardService.getCustomerByEmail(pEmail);
//        List<RoomRequest>=RoomRequestRepository.getallrooms(customer.getUserID());
        List<GetServiceBook> res = bookingRepository.getGetserviceBookings(customer.getUserID());
//        System.out.println(res.get(0).getBookingID());
        return res;
    }


    @PostMapping("/booking/cancel")
    public ResponseEntity deleteBooking(@RequestBody CancelRequest id) {
        System.out.println("HI");
        try {
            cancellationRepository.addCancellation(id);
            bookingRepository.update(id.getBookingID());
            Optional<UUID> roomRequestId = bookingRepository.getRoomRequestIdByBookingId(id.getBookingID());
            if (roomRequestId.isPresent()) {
                System.out.println("Room Request ID: " + roomRequestId.get());
                roomRequestRepository.updatestatustono(roomRequestId.get());
            } else {
                System.out.println("No room request found for this booking ID.");
            }
            Optional<UUID> serviceRequestId = bookingRepository.getserviceRequestIdByBookingId(id.getBookingID());
            if(serviceRequestId.isPresent()) {
                serviceRequestRepository.updateservicestatustono(serviceRequestId.get());
            }
            return ResponseEntity.ok("Booking cancelled");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some error occurred");
        }
    }
//
//    @PostMapping("/addService")
//    public ResponseEntity addService(@RequestBody ServiceRequest sr) {
//        Services s = serviceService.getServiceByName(sr.getServiceName());
//        System.out.println(s.getName());
//        try {
//            serviceService.addServiceToBooking(s.getServiceID(), sr.getBookingID());
//            return ResponseEntity.ok("Service added successfully.");
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.badRequest().body("Some error occurred.");
//        }
//    }
//
//    @PostMapping("/getBill")
//    public GenerateBill getBill(@RequestBody Id id) {
//        GetBooking b = bookingService.getGetBooking(id.getBookingID());
//        System.out.println(id.getBookingID());
//        GenerateBill bill = new GenerateBill( b.getGetBookingID(), b.getGetCheckInDate(), b.getGetCheckOutDate(), b.getCost(), b.getTypeName(), b.getGetServices());
//        return bill;
//    }

}
