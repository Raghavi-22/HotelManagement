package demo.demo.Repository;

import demo.demo.Module.HotelContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addHotelContact(HotelContact hotelContact) {
        String sql = "INSERT INTO HotelContact (Hotelno, Phone) VALUES (?, ?)";
        try {
            return jdbcTemplate.update(sql, hotelContact.getHotelNo(), hotelContact.getPhone());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<HotelContact> getAllContactsForHotel(int hotelno) {
        String sql = "SELECT * FROM HotelContact WHERE Hotelno = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(HotelContact.class), hotelno);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int deleteHotelContact(int hotelno, String phone) {
        String sql = "DELETE FROM HotelContact WHERE Hotelno = ? AND Phone = ?";
        try {
            return jdbcTemplate.update(sql, hotelno, phone);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
