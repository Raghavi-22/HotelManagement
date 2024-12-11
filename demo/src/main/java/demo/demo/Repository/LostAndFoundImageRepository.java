package demo.demo.Repository;

import demo.demo.Module.LostAndFoundImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LostAndFoundImageRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LostAndFoundImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addLostAndFoundImage(LostAndFoundImage lostAndFoundImage) {
        String sql = "INSERT INTO LostAndFoundImage (SNo, Image) VALUES (?, ?)";
        try {
            return jdbcTemplate.update(sql, lostAndFoundImage.getsNo(), lostAndFoundImage.getImage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<LostAndFoundImage> getAllImagesForLostAndFound(int SNo) {
        String sql = "SELECT * FROM LostAndFoundImage WHERE SNo = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LostAndFoundImage.class), SNo);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int deleteLostAndFoundImage(int SNo, String image) {
        String sql = "DELETE FROM LostAndFoundImage WHERE SNo = ? AND Image = ?";
        try {
            return jdbcTemplate.update(sql, SNo, image);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
