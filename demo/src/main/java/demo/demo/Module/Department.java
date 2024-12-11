package demo.demo.Module;

import java.util.UUID;

public class Department {
    private int deptID;
    private int houseIn;
    private String deptName;
    private String description;

    public Department(int deptID, int    houseIn, String deptName, String description) {
        this.deptID = deptID;
        this.houseIn = houseIn;
        this.deptName = deptName;
        this.description = description;
    }

    // Getters and Setters
    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int   deptID) {
        this.deptID = deptID;
    }

    public int getHouseIn() {
        return houseIn;
    }

    public void setHouseIn(int houseIn) {
        this.houseIn = houseIn;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}