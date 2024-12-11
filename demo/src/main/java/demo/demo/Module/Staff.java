package demo.demo.Module;

import java.math.BigDecimal;
import java.util.UUID;

public class Staff {
    private UUID staffID;
    private Integer belongsto;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String position;
    private String sex;
    private String city;
    private String state;
    private String pin;
    private String aadhaarNo;
    private String accountNo;
    private String ifsccode;
    private String bankName;
    private String pEmail;
    private String password;

    public Staff() {
    }

    public Staff(UUID staffID, Integer belongsto, String firstName, String lastName, BigDecimal salary, String position, String sex, String city, String state, String pin, String aadhaarNo, String accountNo, String ifsccode, String bankName, String pEmail, String password) {
        this.staffID = staffID;
        this.belongsto = belongsto;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.position = position;
        this.sex = sex;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.aadhaarNo = aadhaarNo;
        this.accountNo = accountNo;
        this.ifsccode = ifsccode;
        this.bankName = bankName;
        this.pEmail = pEmail;
        this.password = password;
    }

    public UUID getStaffID() {
        return staffID;
    }

    public void setStaffID(UUID staffID) {
        this.staffID = staffID;
    }

    public Integer getBelongsto() {
        return belongsto;
    }

    public void setBelongsto(Integer belongsto) {
        this.belongsto = belongsto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}