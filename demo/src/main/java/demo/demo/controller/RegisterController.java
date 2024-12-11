package demo.demo.controller;

import demo.demo.Module.User;
import demo.demo.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> submitRegister(@RequestBody User user) {
        UUID customerID = UUID.randomUUID();
        user.setUserID(customerID);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setScore(50); // Set score to 0 by default

        if (authenticationService.customerExist(user.getpEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else {
            // Log user details before registration
            System.out.println("Registering user: " + user);

            int result = authenticationService.register(
                    customerID,
                    user.getFname(),
                    user.getMname(),
                    user.getLname(),
                    user.getFlatNo(),
                    user.getSex(),
                    user.getDob(),
                    user.getCity(),
                    user.getState(),
                    user.getPin(),
                    user.getPhoneNo(),
                    user.getScore(),
                    user.getPassword(),
                    user.getpEmail()
            );

            if (result > 0) {
                // Log success message
                System.out.println("User registered successfully: " + user.getpEmail());
                return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
