package demo.demo.Repository;

import demo.demo.Module.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DiscountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addDiscount(Discount discount) {
        String sql = "INSERT INTO Discount (DiscountID, Value, Status, ScoreRequired) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, discount.getDiscountId(), discount.getValue(), discount.getStatus(), discount.getScoreRequired());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public List<Discount> findDiscountsByScore(int score) {
        String sql = "SELECT * FROM Discount WHERE ScoreRequired <= ? AND ScoreRequired>0 AND Status='Yes'";
        return jdbcTemplate.query(sql, new Object[]{score}, new BeanPropertyRowMapper<>(Discount.class));
    }
    public Discount getDiscountByID(int discountID) {
        String sql = "SELECT * FROM Discount WHERE DiscountID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Discount.class), discountID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Discount> getAllDiscounts() {
        String sql = "SELECT * FROM Discount";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Discount.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updateDiscount(Discount discount) {
        String sql = "UPDATE Discount SET Value = ?, Status = ?, ScoreRequired = ? WHERE DiscountID = ?";
        try {
            return jdbcTemplate.update(sql, discount.getValue(), discount.getStatus(), discount.getScoreRequired(), discount.getDiscountId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int getScoreRequiredByDiscountId(int discountId) {
        String sql = "SELECT ScoreRequired FROM Discount WHERE DiscountID = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, discountId);
    }

    public int deleteDiscount(int discountID) {
        String sql = "DELETE FROM Discount WHERE DiscountID = ?";
        try {
            return jdbcTemplate.update(sql, discountID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
