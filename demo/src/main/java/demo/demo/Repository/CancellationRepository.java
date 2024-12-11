package demo.demo.Repository;

import demo.demo.Module.Cancellation;
import demo.demo.jsonResponse.CancelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CancellationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CancellationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addCancellation(CancelRequest id) {
        String sql = "INSERT INTO Cancellation (CancellationID, BookingNo, Date, Time, Status, Reason) VALUES (?, ?, CURRENT_DATE, CURRENT_TIME, ?, ?)";
        try {
            return jdbcTemplate.update(sql, UUID.randomUUID().toString(), id.getBookingID().toString() , "Yes", id.getReason());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Cancellation getCancellationByID(int cancellationID) {
        String sql = "SELECT * FROM Cancellation WHERE CancellationID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cancellation.class), cancellationID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Cancellation> getAllCancellations() {
        String sql = "SELECT * FROM Cancellation";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cancellation.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updateCancellation(Cancellation cancellation) {
        String sql = "UPDATE Cancellation SET BookingNo = ?, Date = ?, Time = ?, Status = ?, Reason = ? WHERE CancellationID = ?";
        try {
            return jdbcTemplate.update(sql, cancellation.getBookingNo(), cancellation.getDate(), cancellation.getTime(), cancellation.getStatus(), cancellation.getReason(), cancellation.getCancellationId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteCancellation(int cancellationID) {
        String sql = "DELETE FROM Cancellation WHERE CancellationID = ?";
        try {
            return jdbcTemplate.update(sql, cancellationID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
