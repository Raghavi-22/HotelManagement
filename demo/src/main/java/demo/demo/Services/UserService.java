package demo.demo.Services;

import demo.demo.Module.User;
import demo.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Get user by email
    public User getUserByEmail(String pEmail) {
        return userRepository.getUserByEmail(pEmail);
    }

    // Register a new customer

}