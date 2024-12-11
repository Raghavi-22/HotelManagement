package demo.demo.Module;

public class Admin {
    private String emailID;
    private String password;

    public Admin() {
    }

    public Admin(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin orElse(Admin a) {
        return null;
    }
}