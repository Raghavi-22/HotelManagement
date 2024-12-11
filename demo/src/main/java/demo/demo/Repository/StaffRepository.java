package demo.demo.Repository;

import demo.demo.Module.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public class StaffRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StaffRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add Staff
    // Add Staff by individual parameters
    public int addStaff(UUID staffID, int belongsto, String firstName, String lastName, BigDecimal salary, String position, String sex, String city, String state, String pin, String aadhaarNo, String accountNo, String ifscCode, String bankName, String pEmail, String password) {
        String sql = "INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            return jdbcTemplate.update(sql, staffID.toString(), belongsto, firstName, lastName, salary, position, sex, city, state, pin, aadhaarNo, accountNo, ifscCode, bankName, pEmail, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int deleteStaffByEmail(String pEmail) {
        String sql = "DELETE FROM Staff WHERE PEmail = ?";
        try {
            return jdbcTemplate.update(sql, pEmail);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // Get Staff by ID
    public Staff getStaffByID(UUID staffID) {
        String sql = "SELECT * FROM Staff WHERE StaffID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Staff.class), staffID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Get Staff by Email
    public Staff getStaffByEmail(String email) {
        String sql = "SELECT * FROM Staff WHERE PEmail = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Staff.class), email);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Get All Staff
    public List<Staff> getAllStaff() {
        String sql = "SELECT * FROM Staff";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Staff.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Update Staff
    public int updateStaff(Staff staff) {
        String sql = "UPDATE Staff SET Belongsto = ?, FirstName = ?, LastName = ?, Salary = ?, Position = ?, Sex = ?, City = ?, State = ?, PIN = ?, AadhaarNo = ?1, AccountNo = ?, IFSCCode = ?, BankName = ?, PEmail = ?, Password = ? WHERE StaffID = ?";
        try {
            return jdbcTemplate.update(sql,
                    staff.getBelongsto(),
                    staff.getFirstName(),
                    staff.getLastName(),
                    staff.getSalary(),
                    staff.getPosition(),
                    staff.getSex(),
                    staff.getCity(),
                    staff.getState(),
                    staff.getPin(),
                    staff.getAadhaarNo(),
                    staff.getAccountNo(),
                    staff.getIfsccode(),
                    staff.getBankName(),
                    staff.getpEmail(),
                    staff.getPassword(),
                    staff.getStaffID()
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // Delete Staff by ID
    public int deleteStaff(int staffID) {
        String sql = "DELETE FROM Staff WHERE StaffID = ?";
        try {
            return jdbcTemplate.update(sql, staffID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void updateStaffAttribute(UUID staffID, String attributeName, String attributeValue) {
        String sql = "UPDATE Staff SET " + attributeName + " = ? WHERE StaffID = ?";
        jdbcTemplate.update(sql, attributeValue, staffID.toString());
    }



}