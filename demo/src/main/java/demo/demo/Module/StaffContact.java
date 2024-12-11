package demo.demo.Module;

import java.util.UUID;

public class StaffContact {
    private UUID staffno;
    private String phone;

    public StaffContact(UUID staffno, String phone) {
        this.staffno = staffno;
        this.phone = phone;
    }

    public UUID getStaffno() {
        return staffno;
    }

    public void setStaffno(UUID staffno) {
        this.staffno = staffno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
