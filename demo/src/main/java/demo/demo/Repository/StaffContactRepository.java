package demo.demo.Repository;

import demo.demo.Module.StaffContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StaffContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StaffContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add staff phone number
    public int addStaffPhone(UUID staffId, String phone) {
        String sql = "INSERT INTO StaffContact (staffId, phone) VALUES (?, ?)";
        return jdbcTemplate.update(sql, staffId.toString(), phone);
    }

    // Get staff phone number by staff ID
    public StaffContact getStaffPhoneByID(UUID staffId) {
        String sql = "SELECT * FROM StaffContact WHERE staffId = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(StaffContact.class), staffId.toString());
    }

    // Update staff phone number
    public int updateStaffPhone(UUID staffId, String phone) {
        String sql = "UPDATE StaffContact SET phone = ? WHERE staffId = ?";
        return jdbcTemplate.update(sql, phone, staffId.toString());
    }
}
