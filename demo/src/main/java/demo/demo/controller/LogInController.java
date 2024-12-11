package demo.demo.controller;



import demo.demo.Module.Admin;
import demo.demo.Module.Staff;
import demo.demo.Module.User;
import demo.demo.Services.*;
import demo.demo.securityConfiguration.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LogInController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final AdminService adminService;
    private final StaffService staffService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final CustomerDetailsServiceImpl customerDetailsService;
    private final StaffDetailsServiceImpl staffDetailsService;
    private final AdminDetailsServiceImpl adminDetailsService;

    @Autowired
    public LogInController(AuthenticationManager authenticationManager, AuthenticationService authenticationService,
                           JwtUtil jwtUtil, AdminService adminService, StaffService staffService, UserService userService, CustomerDetailsServiceImpl customerDetailsService,StaffDetailsServiceImpl staffDetailsService, AdminDetailsServiceImpl adminDetailsService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
        this.adminService = adminService;
        this.staffService = staffService;
        this.userService = userService;
        this.customerDetailsService = customerDetailsService;
        this.staffDetailsService=staffDetailsService;
        this.adminDetailsService=adminDetailsService;

    }
    @PostMapping("/login")
    public ResponseEntity<?> customerLogIn(@RequestBody User customer) {
        String pEmail = customer.getpEmail();
        String password = customer.getPassword();

        try {
            // Authenticate user (ensure UserDetails is used as the principal)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            customerDetailsService.loadUserByUsername(pEmail), password
                    )
            );
            // Set the authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            List<String> roles= Arrays.asList("ROLE_USER");
            // Generate JWT Token
            String token = jwtUtil.createToken(authentication,roles);

            // Return the token as a response
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("token", token);
            return ResponseEntity.ok(jsonResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
        }
    }
    // Admin login

    @PostMapping("/admin_login")
    public ResponseEntity<?> adminLogin(@RequestBody Admin admin) {
        String emailID = admin.getEmailID();
        String password = admin.getPassword();

        try {
            // Authenticate the admin using Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            adminDetailsService.loadUserByUsername(emailID), password
                    )
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Assuming the admin has a role like ROLE_ADMIN
            List<String> roles = Arrays.asList("ROLE_ADMIN");

            // Generate JWT Token
            String token = jwtUtil.createToken(authentication, roles);

            // Return the token as a response
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("token", token);
            return ResponseEntity.ok(jsonResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
        }
    }


    // Staff login
    @PostMapping("/staff-login")
    public ResponseEntity<?> staffLogin(@RequestBody Staff employee) {
        String pEmail = employee.getpEmail();
        String password = employee.getPassword();
        try {
            // Authenticate user (ensure UserDetails is used as the principal)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            staffDetailsService.loadUserByUsername(pEmail), password
                    )
            );
            // Set the authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            List<String> roles = Arrays.asList("ROLE_STAFF");
            // Generate JWT Token
            String token = jwtUtil.createToken(authentication, roles);

            // Return the token as a response
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("token", token);
            return ResponseEntity.ok(jsonResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
        }
    }
}
