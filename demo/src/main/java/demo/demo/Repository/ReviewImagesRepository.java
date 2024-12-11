package demo.demo.Repository;

import demo.demo.Module.ReviewImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewImagesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewImagesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addReviewImage(ReviewImages reviewImage) {
        String sql = "INSERT INTO ReviewImages (ReviewNo, ReviewImage) VALUES (?, ?)";
        try {
            return jdbcTemplate.update(sql, reviewImage.getReviewNo(), reviewImage.getReviewImage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<ReviewImages> getAllImagesForReview(int reviewNo) {
        String sql = "SELECT * FROM ReviewImages WHERE ReviewNo = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReviewImages.class), reviewNo);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int deleteReviewImage(int reviewNo, String reviewImage) {
        String sql = "DELETE FROM ReviewImages WHERE ReviewNo = ? AND ReviewImage = ?";
        try {
            return jdbcTemplate.update(sql, reviewNo, reviewImage);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
