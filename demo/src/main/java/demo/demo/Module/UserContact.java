package demo.demo.Module;

import java.util.UUID;

public class UserContact {
    private UUID userno;
    private String phone;

    public UserContact() {

    }

    // Getters and setters
    public UUID getuserno() {
        return userno;
    }

    public void setUserno(UUID userno) {
        this.userno = userno;
    }

    public UserContact(UUID userno, String phone) {
        this.userno = userno;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
