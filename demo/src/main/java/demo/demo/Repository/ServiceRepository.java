package demo.demo.Repository;
import demo.demo.Module.Service;
import demo.demo.jsonResponse.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ServiceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public Service getServiceByID(UUID serviceID) {
        String sql = "SELECT * FROM Service WHERE ServiceID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Service.class), serviceID.toString());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Service> getAllServices() {
        String sql = "SELECT * FROM Service";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Service.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }



    public int deleteService(UUID serviceID) {
        String sql = "DELETE FROM Service WHERE ServiceID = ?";
        try {
            return jdbcTemplate.update(sql, serviceID.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }


    public List<Service> getServicesForUser(int hotelId) {
        String sql = "SELECT s.* " +
                "FROM Service s " +
                "JOIN HotelDepartment hd ON s.Belongsto = hd.SNo " +
                "WHERE hd.Hotelno = ? AND s.AvailabilityStatus = 'Yes'";

        try {
            return jdbcTemplate.query(sql, new Object[]{hotelId}, new BeanPropertyRowMapper<>(Service.class));
        } catch (Exception e) {
            System.out.println(e);
        }
            // Return an empty list or handle as needed
            return new ArrayList<>();
    }


    public times getServicesdates(UUID serviceID) {
        String sql = "SELECT s.start, s.end ,s.Price "+
                "FROM Service s " +
                "WHERE s.ServiceID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{serviceID.toString()},
                new BeanPropertyRowMapper<>(times.class));
    }

}
