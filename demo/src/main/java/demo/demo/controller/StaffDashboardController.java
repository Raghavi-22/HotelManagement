package demo.demo.controller;

import demo.demo.Module.LostAndFound;
import demo.demo.Module.Staff;
import demo.demo.Module.User;
import demo.demo.Services.LostAndFoundService;
import demo.demo.Services.StaffDashboardService;
import demo.demo.Services.StaffService;
import demo.demo.jsonResponse.EditRequest;
import demo.demo.jsonResponse.PasswordChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StaffDashboardController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private LostAndFoundService lostAndFoundService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    // Endpoint to get lost and found items
//    @GetMapping("/lostandfound")
//    public List<LostAndFound> getLostAndFoundItems() {
//        System.out.println("Fetching all lost and found items...");
//
//        // Fetch all lost and found items
//        List<LostAndFound> lostAndFoundItems = lostAndFoundService.getAllLostItems();
//        System.out.println("Total lost and found items: " + lostAndFoundItems.size());
//
//        return lostAndFoundItems;
//    }


    // Endpoint to get staff profile details
    @GetMapping("/getStaffProfile")
    public Staff getProfile() {
        System.out.println("Fetching staff profile...");

        // Get the currently authenticated staff
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String pEmail = userDetails.getUsername();
        System.out.println("Email: " + pEmail);

        // Fetch staff details by email
        Staff staff = staffService.getStaffByEmail(pEmail);

        if (staff == null) {
            System.out.println("Staff not found.");
            return null;  // Handle this appropriately
        }

        System.out.println("Staff profile: " + staff);
        return staff;
    }
    @PutMapping("/editStaffProfile")
    public String editProfile(@RequestBody EditRequest er) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails obj = (UserDetails) auth.getPrincipal();
        String pEmail = obj.getUsername();
        Staff customer = staffService.getStaffByEmail(pEmail);
        staffService.updateCustomerAttribute(customer.getStaffID(), er.getAttributeName(), er.getAttributeValue());
        return "Profile updated";
    }

    @PutMapping("/changeStaffPassword")
    public ResponseEntity changePassword(@RequestBody PasswordChangeRequest pcr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String pEmail = userDetails.getUsername();
        Staff customer = staffService.getStaffByEmail(pEmail);
        String nPassword = passwordEncoder.encode(pcr.getNewPassword());
        System.out.println(pcr.getNewPassword());
        System.out.println(nPassword);
        System.out.println(customer.getPassword());
        if(nPassword.equals(customer.getPassword())) {
            return ResponseEntity.badRequest().body("Current password is incorrect.");
        }
        staffService.updateCustomerAttribute(customer.getStaffID(), "password", nPassword);
        return ResponseEntity.ok("Password changed successfully.");
    }
}