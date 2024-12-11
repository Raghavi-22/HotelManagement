package demo.demo.controller;

import demo.demo.Module.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() { return "home"; }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminLoginPage() { return "admin_portal"; }
    @GetMapping("/staff-login")
    public String staffloginpage() { return "staff-login"; }
    @GetMapping("/adminLoginPage")
    public String adminviewpage() { return "admin_login"; }

    @GetMapping("/register")
    public String staffage() { return "register"; }
    @GetMapping("/dashboard")
    public String dashboard(){return "dashboard";}
    @GetMapping("/dashboard/profile")
    public String profile() { return "profile"; }
    @GetMapping("/Hotel1")
    public String Hotel1()
    {
        return "Hotel1";
    }
    @GetMapping("/Hotel2")
    public String Hotel2()
    {
        return "Hotel2";
    }
    @GetMapping("/staffdashboard")
    public String staffprole() { return "staffprofile"; }


    @GetMapping("/admin/customers")
    public String adminCustomer() { return "customers"; }

    @GetMapping("/admin/deleteEmp")
    public String adminDeleteEmp() { return "delete_employee"; }

    @GetMapping("/admin/empForm")
    public String adminEmpForm() { return "employee_form"; }

    @GetMapping("/admin/empManagement")
    public String empManagement() { return "employee_management"; }

    @GetMapping("/admin/roomManagement")
    public String roomManagement() { return "room_management"; }


    @GetMapping("/admin/allEmployees")
    public String allEmployees() { return "all_emps"; }
    @GetMapping("/admin/allBookings")
    public String allBookings() { return "all_bookings"; }


    @RestController
    public class TestController {

        @GetMapping("/test-role")
        public String getUserRole(Principal principal) {
            return "Logged in user: " + principal.getName();
        }

    }
    @GetMapping("/feedback")
    public String feedbackForm() {
        // Display the feedback form page
        return "feedback_list";
    }
    @GetMapping("/feedback_form")
    public String feedbacForm() {
        // Display the feedback form page
        return "feedback_form";
    }
    @GetMapping("/feedbackId")
    public String feedbackById() {
        // Display the feedback form page
        return "feedbackById";
    }
    @GetMapping("/rooms")
    public String rooms()
    {
        return "rooms";
    }
    @GetMapping("/payment")
    public String payment()
    {
        return "payment";
    }
    @GetMapping("/Bookings")
    public String bookings()
    {
        return "bill";
    }
    @GetMapping("/cancellation")
    public String can()
    {
        return "cancellation";
    }
    @GetMapping("/service")
    public String cn()
    {
        return "service";
    }
    @GetMapping("/servicepayment")
    public String canu()
    {
        return "servicepayment";
    }
    @GetMapping("/feedbackimages")
    public String images()
    {
        return "feedbackimages";
    }
    @GetMapping("/experiences/1")
    public String experiences()
    {
        return "experience1";
    }
    @GetMapping("/experiences/2")
    public String experiences2()
    {
        return "experience2";
    }

    @GetMapping("/services")
    public String services()
    {
        return "services";
    }
    @GetMapping("/LostAndFound")
    public String addingtems()
    {
        return "LostAndFound_list";
    }
    @GetMapping("/LostAndFound_add")
    public String addiitems()
    {
        return "LostAndFound";
    }

    @GetMapping("/LostAndFoundbyid")
    public String aditems()
    {
        return "LostAndFoundByID";
    }
    @GetMapping("/status")
    public String status()
    {
        return "status";
    }






}