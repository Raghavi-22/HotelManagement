package demo.demo.Repository;

import demo.demo.Module.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public class ServiceRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addServiceRequest(String requestid, UUID serviceid, UUID userid, LocalDate date, LocalTime time) {
        String sql = "INSERT INTO ServiceRequest (RequestServiceID, ServiceNo, Date, Time, ServiceRequestedBy) VALUES (?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, requestid, serviceid.toString(),date,time,userid.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public ServiceRequest getServiceRequestByID(UUID requestServiceID) {
        String sql = "SELECT * FROM ServiceRequest WHERE RequestServiceID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ServiceRequest.class), requestServiceID.toString());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<ServiceRequest> getAllServiceRequests() {
        String sql = "SELECT * FROM ServiceRequest";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceRequest.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updateServiceRequest(ServiceRequest serviceRequest) {
        String sql = "UPDATE ServiceRequest SET ServiceNo = ?, Date = ?, Time = ?, Status = ?, ServiceRequestedBy = ? WHERE RequestServiceID = ?";
        try {
            return jdbcTemplate.update(sql, serviceRequest.getServiceNo(), serviceRequest.getDate(), serviceRequest.getTime(), serviceRequest.getAvailabilityStatus(), serviceRequest.getServiceRequestedBy(),serviceRequest.getRequestServiceId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteServiceRequest(UUID requestServiceID) {
        String sql = "DELETE FROM ServiceRequest WHERE RequestServiceID = ?";
        try {
            return jdbcTemplate.update(sql, requestServiceID.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int updateservicestatustono(UUID roomRequestId) {
        String sql = "UPDATE ServiceRequest SET AvailabilityStatus = 'No' WHERE RequestServiceID = ?";
        try {
            return jdbcTemplate.update(sql, roomRequestId.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int updateservicestatustoyes(UUID roomRequestId) {
        String sql = "UPDATE ServiceRequest SET AvailabilityStatus = 'Yes' WHERE RequestServiceID = ?";
        try {
            return jdbcTemplate.update(sql, roomRequestId.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int checkuser(UUID userId) {
        String sql = """
        SELECT COUNT(*)
                 FROM Booking b
                                       JOIN ServiceRequest s ON b.ServiceReqNo = s.RequestServiceID
                                                       WHERE s.ServiceRequestedBy = ?
                                                         AND s.AvailabilityStatus = 'Yes';
    """;
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId.toString()}, Integer.class);
        } catch (Exception e) {
            System.out.println("Error fetching available rooms: " + e.getMessage());
            return 0;
        }
    }
}
