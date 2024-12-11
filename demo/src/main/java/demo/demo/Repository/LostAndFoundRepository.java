package demo.demo.Repository;

import demo.demo.Module.LostAndFound;
import demo.demo.jsonResponse.LostAndFoundObjImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Repository
public class LostAndFoundRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LostAndFoundRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addItem(LostAndFound item) {
        String sql = "INSERT INTO LostAndFound (handledBy, itemType, foundLocation, status, date, time, description, hotelno) VALUES (?, ?, ?, ?, CURRENT_DATE, CURRENT_TIME, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // To hold the generated key

        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getHandledBy().toString());
            ps.setString(2, item.getItemType());
            ps.setString(3, item.getFoundLocation());
            ps.setString(4, item.getStatus());
            ps.setString(5, item.getDescription());
            ps.setInt(6, item.getHotelno());
            return ps;
        }, keyHolder);

        // Now you can retrieve the generated key (auto-incremented value)
        if (affectedRows > 0) {
            return keyHolder.getKey().intValue(); // Return the generated key
        } else {
            throw new RuntimeException("Insertion failed, no rows affected.");
        }
    }


    public List<LostAndFoundObjImage> getAllItems() {
        System.out.println("Listing function is getting called from database");
        String sql = "SELECT l.SNo, l.HandledBy, l.ItemType, l.FoundLocation, l.Status, " +
                "l.Date, l.Time, l.Description, l.hotelno, li.Image " +
                "FROM LostAndFound l " +
                "LEFT JOIN LostAndFoundImage li ON l.SNo = li.SNo";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LostAndFoundObjImage.class));
    }

    public int updateItem( LostAndFound item) {
        String sql = "UPDATE LostAndFound SET itemType = ?, foundLocation = ?, status = ?, description = ? WHERE sNo = ?";
        return jdbcTemplate.update(sql, item.getItemType(), item.getFoundLocation(), item.getStatus(), item.getDescription(), item.getsNo());
    }

    public int deleteItem(int sNo) {
        String sql = "DELETE FROM LostAndFound WHERE sNo = ?";
        return jdbcTemplate.update(sql, sNo);
    }

    public int addimage(int reviewId, String filePath) {
        String sql = "INSERT INTO LostAndFoundImage VALUES (?, ?)";
        try {
            // Convert Path to String to store in the database
            return jdbcTemplate.update(sql, reviewId, filePath);
        } catch (Exception e) {
            System.out.println("Error while uploading image: " + e);
        }
        return 0;
    }

    public List<LostAndFoundObjImage> getAllItemsbyid(UUID staffID) {
        String sql = "SELECT l.SNo, l.HandledBy, l.ItemType, l.FoundLocation, l.Status, " +
                "l.Date, l.Time, l.Description, l.hotelno, li.Image " +
                "FROM LostAndFound l " +
                "LEFT JOIN LostAndFoundImage li ON l.SNo = li.SNo " + // Added a space before WHERE
                "WHERE l.HandledBy = ?"; // Corrected the parameter name to match the method parameter

        return jdbcTemplate.query(sql, new Object[]{staffID.toString()}, new BeanPropertyRowMapper<>(LostAndFoundObjImage.class)); // Fixed the new object array syntax
    }

    public List<LostAndFoundObjImage> getitem(int sno) {
        String sql = "SELECT l.SNo, l.HandledBy, l.ItemType, l.FoundLocation, l.Status, " +
                "l.Date, l.Time, l.Description, l.hotelno, li.Image " +
                "FROM LostAndFound l " +
                "LEFT JOIN LostAndFoundImage li ON l.SNo = li.SNo " + // Added a space before WHERE
                "WHERE l.SNo = ?"; // Corrected the parameter name to match the method parameter

        return jdbcTemplate.query(sql, new Object[]{sno}, new BeanPropertyRowMapper<>(LostAndFoundObjImage.class)); // Fixed the new object array syntax

    }
}