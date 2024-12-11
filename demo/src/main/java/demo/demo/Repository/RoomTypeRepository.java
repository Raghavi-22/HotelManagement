package demo.demo.Repository;
import demo.demo.Module.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public class RoomTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addRoomType(int typeID, String typeName, String description) {
        String sql = "INSERT INTO RoomType (TypeID, TypeName, Description) VALUES (?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, typeID, typeName, description);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public RoomType getRoomTypeByID(int typeID) {
        String sql = "SELECT * FROM RoomType WHERE TypeID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(RoomType.class), typeID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<RoomType> getAllRoomTypes() {
        String sql = "SELECT * FROM RoomType";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RoomType.class));
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public RoomType getRoomTypeByName(String typeName) {
        String sql = "SELECT * FROM RoomType WHERE TypeName = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{typeName},
                    new BeanPropertyRowMapper<>(RoomType.class));
        } catch (Exception e) {
            // Log exception or handle if TypeName does not exist
            return null;
        }
    }
}