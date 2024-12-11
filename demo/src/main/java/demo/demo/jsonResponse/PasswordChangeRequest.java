package demo.demo.jsonResponse;

public class PasswordChangeRequest {
    private String newPassword;

    public PasswordChangeRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public PasswordChangeRequest() {
    }
}
