package demo.demo.Module;

import java.util.UUID;

public class UserEmail {
    private UUID userno;
    private String email;

    // Getters and setters
    public UUID getUserno() {
        return userno;
    }

    public void setUserno(UUID userno) {
        this.userno = userno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
