package demo.demo.Services;

import demo.demo.Module.Staff;
import demo.demo.Repository.AdminRepository;
import demo.demo.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StaffService {

    @Autowired
    private StaffRepository StaffRepository;

    public Staff getStaffByEmail(String emailID) {
        return StaffRepository.getStaffByEmail(emailID);
    }
    public void updateCustomerAttribute(UUID customerID, String attributeName, String attributeValue) {
        StaffRepository.updateStaffAttribute(customerID, attributeName, attributeValue);
    }

}