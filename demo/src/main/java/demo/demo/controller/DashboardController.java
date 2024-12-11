package demo.demo.controller;
import demo.demo.jsonResponse.EditRequest;
import demo.demo.jsonResponse.PasswordChangeRequest;
import demo.demo.Module.User;
import demo.demo.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class DashboardController {

    private final DashboardService dashboardService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DashboardController(DashboardService dashboardService, PasswordEncoder passwordEncoder) {
        this.dashboardService = dashboardService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/getProfile")
    public User getProfile() {
        System.out.println("customer profile opened");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails obj = (UserDetails) auth.getPrincipal();
        String pEmail = obj.getUsername();
        System.out.println(pEmail);
        User customer = dashboardService.getCustomerByEmail(pEmail);
        System.out.println(customer);
        return customer;
    }

    @PutMapping("/editProfile")
    public String editProfile(@RequestBody EditRequest er) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails obj = (UserDetails) auth.getPrincipal();
        String pEmail = obj.getUsername();
        User customer = dashboardService.getCustomerByEmail(pEmail);
        dashboardService.updateCustomerAttribute(customer.getUserID(), er.getAttributeName(), er.getAttributeValue());
        return "Profile updated";
    }

    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody PasswordChangeRequest pcr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String pEmail = userDetails.getUsername();
        User customer = dashboardService.getCustomerByEmail(pEmail);
        String nPassword = passwordEncoder.encode(pcr.getNewPassword());
        System.out.println(pcr.getNewPassword());
        System.out.println(nPassword);
        System.out.println(customer.getPassword());
        if(nPassword.equals(customer.getPassword())) {
            return ResponseEntity.badRequest().body("Current password is incorrect.");
        }
        dashboardService.updateCustomerAttribute(customer.getUserID(), "password", nPassword);
        return ResponseEntity.ok("Password changed successfully.");
    }

}
