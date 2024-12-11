package demo.demo.Services;
import demo.demo.Module.User;
import demo.demo.Module.UserEmail;
import demo.demo.Module.UserContact;
import demo.demo.Repository.UserEmailRepository;

import demo.demo.Repository.UserContactRepository;
import demo.demo.Repository.UserEmailRepository;
import demo.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DashboardService {

    private final UserRepo customerRepository;
    private final UserContactRepository customerPhoneRepository;
    private final UserEmailRepository customerEmailRepository;

    @Autowired
    public DashboardService(UserRepo customerRepository,UserContactRepository  customerPhoneRepository, UserEmailRepository customerEmailRepository) {
        this.customerRepository = customerRepository;
        this.customerPhoneRepository = customerPhoneRepository;
        this.customerEmailRepository = customerEmailRepository;
    }

    public User getCustomerByEmail(String pEmail) {
        return customerRepository.getUserByEmail(pEmail);
    }

    public User getCustomerByID(UUID customerID) {
        return customerRepository.getUserByID(customerID);
    }

    public void updateCustomerAttribute(UUID customerID, String attributeName, String attributeValue) {
        customerRepository.updateCustomerAttribute(customerID, attributeName, attributeValue);
    }

    public int addAlterEmail(UUID customerID, String custEmailID) {
        return customerEmailRepository.addCustomerEmail(customerID, custEmailID);
    }

    public int addAlterPhoneNo(UUID customerID, String custPhoneNo) {
        return customerPhoneRepository.addUserPhone(customerID, custPhoneNo);
    }

    public void updateAlterEmail(UUID customerID, String custEmailID) {
        customerEmailRepository.updateCustomerEmail(customerID, custEmailID);
    }

    public void updateAlterPhone(UUID customerID, String custPhoneNo) {
        customerPhoneRepository.updateUserPhone(customerID, custPhoneNo);
    }

    public UserContact getUserPhoneByID(UUID customerID) {
        return customerPhoneRepository.getUserPhoneByID(customerID);
    }

    public UserEmail getCustomerEmailByID(UUID customerID) {
        return customerEmailRepository.getCustomerEmailByID(customerID);
    }

    public void updatescoreAttribute(UUID userID, String score, int i) {
        customerRepository.updateScoreAttribute(userID,score,i);
    }
}
