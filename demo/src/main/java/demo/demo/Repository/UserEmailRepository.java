package demo.demo.Repository;



import demo.demo.Module.UserEmail;
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
public class UserEmailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map ResultSet to the UserEmail model
    private RowMapper<UserEmail> userEmailRowMapper = new RowMapper<UserEmail>() {
        @Override
        public UserEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEmail userEmail = new UserEmail();
            userEmail.setUserno(UUID.fromString(rs.getString("Userno")));
            userEmail.setEmail(rs.getString("Email"));
            return userEmail;
        }
    };

    // Fetch all UserEmails
    public List<UserEmail> getAllUserEmails() {
        String sql = "SELECT * FROM UserEmail";
        return jdbcTemplate.query(sql, userEmailRowMapper);
    }

    // Fetch UserEmails by userno
    public List<UserEmail> getUserEmailsByUserno(String userno) {
        String sql = "SELECT * FROM UserEmail WHERE Userno = ?";
        return jdbcTemplate.query(sql, new Object[]{userno}, userEmailRowMapper);
    }

    // Insert a new UserEmail
    public int saveUserEmail(UserEmail userEmail) {
        String sql = "INSERT INTO UserEmail (Userno, Email) VALUES (?, ?)";
        return jdbcTemplate.update(sql, userEmail.getUserno(), userEmail.getEmail());
    }


    public int addCustomerEmail(UUID customerID, String custEmailID) {
        String sql = "INSERT INTO UserEmail (customerID, custEmailID) VALUES (?,?)";
        return jdbcTemplate.update(sql, customerID.toString(), custEmailID);
    }


    public int updateCustomerEmail(UUID customerID, String custEmailID) {
        String sql = "UPDATE UserEmail SET custEmailID = ? WHERE customerID = ?";
        return jdbcTemplate.update(sql, custEmailID, customerID.toString());
    }

    public UserEmail getCustomerEmailByID(UUID customerID) {
        String sql = "SELECT * FROM UserEmail WHERE customerID = ?";
        UserEmail customerEmail = jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(UserEmail.class),
                new Object[]{customerID.toString()});
        return customerEmail;
    }
}

// De
