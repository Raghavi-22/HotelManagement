package demo.demo.Repository;

import demo.demo.Module.RoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoomRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RoomRequest> getallrooms(UUID userID) {
        String query = "SELECT * FROM RoomRequest WHERE AvailabilityStatus = 'Yes' AND UserNo = ?";

        return jdbcTemplate.query(query,
                new BeanPropertyRowMapper<>(RoomRequest.class),
                userID.toString());
    }

    public int addRoomRequest(RoomRequest roomRequest) {
        String sql = "INSERT INTO RoomRequest (RoomRequestID, RoomNo, CheckIn, CheckOut, Status, UserNo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, roomRequest.getRoomRequestId(), roomRequest.getRoomNo(), roomRequest.getCheckIn(), roomRequest.getCheckOut(), roomRequest.getStatus(), roomRequest.getUserNo());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public RoomRequest getRoomRequestByID(UUID roomRequestID) {
        String sql = """
        SELECT 
            RoomRequestID AS roomRequestId, 
            RoomID AS roomNo, 
            CheckIn AS checkIn, 
            CheckOut AS checkOut, 
            UserNo AS userNo, 
            AvailabilityStatus AS status 
        FROM RoomRequest 
        WHERE RoomRequestID = ?
    """;

        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(RoomRequest.class), roomRequestID.toString());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public List<RoomRequest> getAllRoomRequests() {
        String sql = "SELECT * FROM RoomRequest";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RoomRequest.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updateRoomAvailabilityStatus(UUID roomRequestId) {
        String sql = "UPDATE RoomRequest SET AvailabilityStatus = 'Yes' WHERE RoomRequestID = ?";
        try {
            return jdbcTemplate.update(sql, roomRequestId.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int updatestatustono(UUID roomRequestId) {
        String sql = "UPDATE RoomRequest SET AvailabilityStatus = 'No' WHERE RoomRequestID = ?";
        try {
            return jdbcTemplate.update(sql, roomRequestId.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }


    public int deleteRoomRequest(UUID roomRequestID) {
        String sql = "DELETE FROM RoomRequest WHERE RoomRequestID = ?";
        try {
            return jdbcTemplate.update(sql, roomRequestID.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
