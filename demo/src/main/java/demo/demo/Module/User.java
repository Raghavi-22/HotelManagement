package demo.demo.Module;

import java.util.Date;
import java.util.UUID;

public class User {

    private UUID userID;
    private String fname;
    private String mname;
    private String lname;
    private String flatNo;
    private String sex;
    private Date dob;
    private String city;
    private String state;
    private String pin;
    private String phoneNo;
    private int score;
    private String password;
    private String pEmail;

    // Getters and Setters
    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
;


    public java.sql.Date getDob() {
        // Convert java.util.Date to java.sql.Date
        if (dob != null) {
            return new java.sql.Date(dob.getTime());
        } else {
            return null;  // Handle null if dob is not set
        }
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User orElse(User o) {
        return null;
    }
}

