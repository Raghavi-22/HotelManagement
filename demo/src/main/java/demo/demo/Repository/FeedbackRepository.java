package demo.demo.Repository;

import demo.demo.Module.Feedback;
import demo.demo.jsonResponse.FeedbackObjImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class FeedbackRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FeedbackRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addFeedback(Feedback feedback) {
        System.out.println("getting added");
        // Use SQL's built-in functions for current date and time
        String sql = "INSERT INTO Feedback (ReviewID, Ratings,HotelNo, UserNo, Comments, Date,Time) VALUES (?, ?, ?, ? , ? , CURRENT_DATE,CURRENT_TIME)";
        try {
            return jdbcTemplate.update(sql, feedback.getReviewId().toString(), feedback.getRatings(),feedback.getHotelNo(), feedback.getUserNo().toString(), feedback.getComments());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public List<FeedbackObjImage> getAllFeedbackbyUser(UUID userNo) {
        String sql = "SELECT f.*,r.ReviewImage as image FROM Feedback f,ReviewImages r where f.ReviewID=r.ReviewNo AND UserNo = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FeedbackObjImage.class),userNo.toString());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public Feedback getFeedbackByID(UUID reviewID) {
        String sql = "SELECT * From Feedback where ReviewID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Feedback.class), reviewID.toString());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<FeedbackObjImage> getAllFeedback() {
        String sql = "SELECT f.*,r.ReviewImage as image FROM Feedback f,ReviewImages r where f.ReviewID=r.ReviewNo ";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FeedbackObjImage.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public List<String> getFeedbackImages(int hotelNo) {
        String sql = "SELECT ri.ReviewImage FROM Feedback as f " +
                "JOIN ReviewImages as ri ON f.ReviewID = ri.ReviewNo " +
                "WHERE f.HotelNo = ? " +
                "LIMIT 5"; // Limit to top 5 results

        try {
            return jdbcTemplate.queryForList(sql, new Object[]{hotelNo}, String.class);
        } catch (Exception e) {
            System.out.println("Error fetching feedback images: " + e.getMessage());
            return Collections.emptyList();
        }
    }




    public int updateFeedback(UUID reviewID,int rating,String comments) {
        String sql = "UPDATE Feedback SET Ratings = ?, Comments = ? WHERE ReviewID = ?";
        try {
            return jdbcTemplate.update(sql,rating ,comments ,reviewID.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteFeedback(UUID reviewID) {
        String sql = "DELETE FROM Feedback WHERE ReviewID = ?";
        try {
            return jdbcTemplate.update(sql, reviewID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int uploadImage(UUID reviewId, Path filePath) {
        String sql = "INSERT INTO ReviewImages VALUES (?, ?)";
        try {
            // Convert Path to String to store in the database
            return jdbcTemplate.update(sql, reviewId.toString(), filePath.toString());
        } catch (Exception e) {
            System.out.println("Error while uploading image: " + e);
        }
        return 0;
    }

    public List<Feedback> getFeedback(int num) {
        // Correct the SQL query to include a space before WHERE
        String sql = "SELECT * FROM Feedback " +
                "WHERE HotelNo = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{num}, new BeanPropertyRowMapper<>(Feedback.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}