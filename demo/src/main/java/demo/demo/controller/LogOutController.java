package demo.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LogOutController {

    @GetMapping("/logout")
    public ResponseEntity<String> userLogout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        System.out.println("MY LIFE");
        System.out.println( SecurityContextHolder.getContext());
        return ResponseEntity.ok("You have been logged out successfully");
    }
}