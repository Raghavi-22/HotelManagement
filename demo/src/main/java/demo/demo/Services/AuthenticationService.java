package demo.demo.Services;

import demo.demo.Module.User;
import demo.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.sql.Date;

@Service
public class AuthenticationService {
    private final UserRepo customerRepository;

    @Autowired
    public AuthenticationService(UserRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    public int register(UUID userID, String fname, String mname, String lname, String flatNo, String sex, Date dob, String city, String state, String pin, String phoneNo, int score, String password, String pEmail) {
        try {
            System.out.println(userID);
            System.out.println(fname);
            System.out.println(mname);
            System.out.println(flatNo);
            System.out.println(sex);
            System.out.println(city);
            System.out.println(state);
            System.out.println(pin);

            return customerRepository.registerCustomer(userID, fname, mname, lname, flatNo, sex, dob, city, state, pin, phoneNo, score, password, pEmail);
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            return 0;
        }
    }

    public Boolean checkCustomerCredentials(String pEmail, String password) {
        User customer = customerRepository.getUserByEmail(pEmail);
        return customer.getPassword().equals(password);
    }

    public boolean customerExist(String pEmail) {
        return customerRepository.customerExist(pEmail);
    }
}
