package demo.demo.Repository;

import demo.demo.Module.StaffEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class StaffEmailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to the StaffEmail model
    private RowMapper<StaffEmail> staffEmailRowMapper = new RowMapper<StaffEmail>() {
        @Override
        public StaffEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
            StaffEmail staffEmail = new StaffEmail();
            staffEmail.setStaffemail(UUID.fromString(rs.getString("StaffEmail")));
            staffEmail.setPhone(rs.getString("Phone"));
            return staffEmail;
        }
    };

    // Fetch all StaffEmails
    public List<StaffEmail> getAllStaffEmails() {
        String sql = "SELECT * FROM StaffEmail";
        return jdbcTemplate.query(sql, staffEmailRowMapper);
    }

    // Fetch StaffEmails by staffemail (UUID)
    public List<StaffEmail> getStaffEmailsByStaffID(UUID staffemail) {
        String sql = "SELECT * FROM StaffEmail WHERE StaffEmail = ?";
        return jdbcTemplate.query(sql, new Object[]{staffemail.toString()}, staffEmailRowMapper);
    }

    // Insert a new StaffEmail
    public int saveStaffEmail(StaffEmail staffEmail) {
        String sql = "INSERT INTO StaffEmail (StaffEmail, Phone) VALUES (?, ?)";
        return jdbcTemplate.update(sql, staffEmail.getStaffemail().toString(), staffEmail.getPhone());
    }

    // Update an existing StaffEmail
    public int updateStaffEmail(UUID staffemail, String phone) {
        String sql = "UPDATE StaffEmail SET Phone = ? WHERE StaffEmail = ?";
        return jdbcTemplate.update(sql, phone, staffemail.toString());
    }

    // Fetch StaffEmail by staffemail (UUID)
    public StaffEmail getStaffEmailByID(UUID staffemail) {
        String sql = "SELECT * FROM StaffEmail WHERE StaffEmail = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(StaffEmail.class), staffemail.toString());
    }

    public int addStaffEmail(UUID staffID, String altEmailID) {
        String sql = "INSERT INTO StaffEmail (staffID,altEmailID ) VALUES (?, ?)";
        return jdbcTemplate.update(sql,staffID.toString(),altEmailID);
    }

}
