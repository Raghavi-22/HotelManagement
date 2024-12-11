package demo.demo.Repository;

import demo.demo.Module.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Admin getAdmin(String emailID) {
        String sql = "SELECT * FROM Admin WHERE emailID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), emailID);
        } catch (EmptyResultDataAccessException e) {
            return null; // Handle case where no admin is found
        }
    }
}
