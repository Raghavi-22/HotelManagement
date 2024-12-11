package demo.demo.Module;

import java.util.UUID;

public class StaffEmail {
    private UUID staffemail;
    private String phone;
    public StaffEmail() {
        // Default constructor
    }
    public StaffEmail(UUID staffemail, String phone) {
        this.staffemail = staffemail;
        this.phone = phone;
    }

    public UUID getStaffemail() {
        return staffemail;
    }

    public void setStaffemail(UUID staffemail) {
        this.staffemail = staffemail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
