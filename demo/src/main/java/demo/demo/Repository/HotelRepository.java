package demo.demo.Repository;

import demo.demo.Module.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addHotel(Hotel hotel) {
        String sql = "INSERT INTO Hotel (HotelID, HotelName, City, State, PIN, Rating) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, hotel.getHotelID(), hotel.getHotelName(), hotel.getCity(), hotel.getState(), hotel.getPin(), hotel.getRating());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Hotel getHotelByID(int hotelID) {
        String sql = "SELECT * FROM Hotel WHERE HotelID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Hotel.class), hotelID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Hotel> getAllHotels() {
        String sql = "SELECT * FROM Hotel";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Hotel.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updateHotel(Hotel hotel) {
        String sql = "UPDATE Hotel SET HotelName = ?, City = ?, State = ?, PIN = ?, Rating = ? WHERE HotelID = ?";
        try {
            return jdbcTemplate.update(sql, hotel.getHotelName(), hotel.getCity(), hotel.getState(), hotel.getPin(), hotel.getRating(), hotel.getHotelID());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteHotel(int hotelID) {
        String sql = "DELETE FROM Hotel WHERE HotelID = ?";
        try {
            return jdbcTemplate.update(sql, hotelID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
