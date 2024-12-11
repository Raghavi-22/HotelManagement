package demo.demo.Repository;

import demo.demo.Module.HotelEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelEmailRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelEmailRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addHotelEmail(HotelEmail hotelEmail) {
        String sql = "INSERT INTO HotelEmail (Hotelno, Email) VALUES (?, ?)";
        try {
            return jdbcTemplate.update(sql, hotelEmail.getHotelNo(), hotelEmail.getEmail());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<HotelEmail> getAllEmailsForHotel(int hotelno) {
        String sql = "SELECT * FROM HotelEmail WHERE Hotelno = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(HotelEmail.class), hotelno);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int deleteHotelEmail(int hotelno, String email) {
        String sql = "DELETE FROM HotelEmail WHERE Hotelno = ? AND Email = ?";
        try {
            return jdbcTemplate.update(sql, hotelno, email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
