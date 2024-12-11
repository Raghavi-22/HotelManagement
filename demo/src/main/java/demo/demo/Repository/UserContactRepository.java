package demo.demo.Repository;

import demo.demo.Module.UserContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add user phone number
    public int addUserPhone(UUID userno, String phone) {
        String sql = "INSERT INTO UserContact (userno, phone) VALUES (?, ?)";
        return jdbcTemplate.update(sql, userno.toString(), phone);
    }

    // Get user phone number by user ID
    public UserContact getUserPhoneByID(UUID userno) {
        String sql = "SELECT * FROM UserContact WHERE userno = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserContact.class), userno.toString());
    }

    // Update user phone number
    public int updateUserPhone(UUID userno, String phone) {
        String sql = "UPDATE UserContact SET phone = ? WHERE userno = ?";
        return jdbcTemplate.update(sql, phone, userno.toString());
    }
}
