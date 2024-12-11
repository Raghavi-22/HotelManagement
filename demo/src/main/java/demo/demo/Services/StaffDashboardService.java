package demo.demo.Services;

import demo.demo.Module.Staff;
import demo.demo.Module.StaffEmail;
import demo.demo.Module.StaffContact;
import demo.demo.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StaffDashboardService {

    private final StaffRepository staffRepository;
    private final StaffContactRepository staffContactRepository;
    private final StaffEmailRepository staffEmailRepository;

    @Autowired
    public StaffDashboardService(StaffRepository staffRepository, StaffContactRepository staffContactRepository, StaffEmailRepository staffEmailRepository) {
        this.staffRepository = staffRepository;
        this.staffContactRepository = staffContactRepository;
        this.staffEmailRepository = staffEmailRepository;
    }

    public Staff getStaffByEmail(String pEmail) {
        return staffRepository.getStaffByEmail(pEmail);
    }

    public Staff getStaffByID(UUID staffID) {
        return staffRepository.getStaffByID(staffID);
    }

    public void updateStaffAttribute(UUID staffID, String attributeName, String attributeValue) {
        staffRepository.updateStaffAttribute(staffID, attributeName, attributeValue);
    }

    public int addAlternateEmail(UUID staffID, String altEmailID) {
        return staffEmailRepository.addStaffEmail(staffID, altEmailID);
    }

    public int addAlternatePhone(UUID staffID, String altPhoneNo) {
        return staffContactRepository.addStaffPhone(staffID, altPhoneNo);
    }

    public void updateAlternateEmail(UUID staffID, String altEmailID) {
        staffEmailRepository.updateStaffEmail(staffID, altEmailID);
    }

    public void updateAlternatePhone(UUID staffID, String altPhoneNo) {
        staffContactRepository.updateStaffPhone(staffID, altPhoneNo);
    }

    public StaffContact getStaffPhoneByID(UUID staffID) {
        return staffContactRepository.getStaffPhoneByID(staffID);
    }

    public StaffEmail getStaffEmailByID(UUID staffID) {
        return staffEmailRepository.getStaffEmailByID(staffID);
    }
}
