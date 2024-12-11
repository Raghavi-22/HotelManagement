package demo.demo.controller;
import demo.demo.Module.RoomRequest;
import demo.demo.Module.ServiceRequest;
import demo.demo.Module.User;
import demo.demo.Repository.RoomRequestRepository;
import demo.demo.Repository.ServiceRequestRepository;
import demo.demo.Services.BookingService;
import demo.demo.Services.DashboardService;
import demo.demo.Services.DiscountService;
import demo.demo.Services.PaymentService;
import demo.demo.jsonResponse.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final DashboardService dashboardService;
    private final BookingService bookingService;
    private final RoomRequestRepository roomRequestRepository;
    private final DiscountService discountService;
    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public PaymentController(PaymentService paymentService, DashboardService dashboardService, BookingService bookingService, RoomRequestRepository roomRequestRepository, DiscountService discountService, ServiceRequestRepository serviceRequestRepository) {
        this.paymentService = paymentService;
        this.dashboardService = dashboardService;
        this.bookingService = bookingService;
        this.roomRequestRepository = roomRequestRepository;
        this.discountService = discountService;
        this.serviceRequestRepository = serviceRequestRepository;
    }

    @PostMapping("/dopayment")
    public ResponseEntity<?> doPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails obj = (UserDetails) auth.getPrincipal();
            String pEmail = obj.getUsername();
            User customer = dashboardService.getCustomerByEmail(pEmail);
            RoomRequest roomrequest = roomRequestRepository.getRoomRequestByID(paymentRequest.getRoomReqId());
           System.out.println(roomrequest.getStatus());

            boolean isSuccess = roomrequest.getStatus().equals("No");


            System.out.println(isSuccess);
            if (isSuccess) {
                UUID bookingID = UUID.randomUUID();
                bookingService.book(bookingID, paymentRequest.getRoomReqId(), paymentRequest.getDiscountId(), null);
                paymentService.addPayment(UUID.randomUUID(),bookingID,paymentRequest.getPaymentMode(),paymentRequest.getTransactionId(),paymentRequest.getCost());
                int req_cost=discountService.getScoreRequired(paymentRequest.getDiscountId());
                System.out.println(req_cost);
                dashboardService.updatescoreAttribute(customer.getUserID(), "score", customer.getScore() -req_cost+ (int)(paymentRequest.getCost() * 0.005));
                roomRequestRepository.updateRoomAvailabilityStatus(roomrequest.getRoomRequestId());
                return ResponseEntity.ok("Payment processed successfully.");
            } else {
                return ResponseEntity.status(400).body("payment already done on this request id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing payment: " + e.getMessage());
        }
    }


    @PostMapping("/doservicepayment")

    public ResponseEntity<?> doservicePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails obj = (UserDetails) auth.getPrincipal();
            String pEmail = obj.getUsername();
            User customer = dashboardService.getCustomerByEmail(pEmail);
//            ServiceRequest roomrequest = serviceRequestRepository.getServiceRequestByID(paymentRequest.getRoomReqId());

            UUID bookingID = UUID.randomUUID();
            bookingService.book(bookingID,null , paymentRequest.getDiscountId(),paymentRequest.getRoomReqId());
            paymentService.addPayment(UUID.randomUUID(),bookingID,paymentRequest.getPaymentMode(),paymentRequest.getTransactionId(),paymentRequest.getCost());

            dashboardService.updatescoreAttribute(customer.getUserID(), "score", customer.getScore() + (int)(paymentRequest.getCost() * 0.005));
            serviceRequestRepository.updateservicestatustoyes(paymentRequest.getRoomReqId());
            return ResponseEntity.ok("Payment processed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing payment: " + e.getMessage());
        }
    }
}