package demo.demo.Repository;


import demo.demo.Module.Room;
import demo.demo.jsonResponse.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addRoom(Room room) {
        String sql = "INSERT INTO Room (RoomID, ContainedIn, FloorNo, Cost, Occupancy, AvailabilityStatus, TypeID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, room.getRoomID(), room.getContainedIn(), room.getFloorNo(), room.getCost(), room.getOccupancy(), room.getAvailabilityStatus(), room.getTypeID());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Room getRoomByID(int roomID) {
        String sql = "SELECT * FROM Room WHERE RoomID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), roomID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Rooms> getAllRooms() {
        String sql = "SELECT r.RoomID, rt.typeName AS roomType, r.cost, r.occupancy as occupancyLimit,r.ContainedIn as hotelno FROM Room r, RoomType rt WHERE r.TypeID = rt.TypeID;";
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rooms.class), new Object[]{});
        }
        catch ( Exception e){
            System.out.println(e);
            return new ArrayList<Rooms>();
        }
    }

    public int updateRoom(Room room) {
        String sql = "UPDATE Room SET ContainedIn = ?, FloorNo = ?, Cost = ?, Occupancy = ?, AvailabilityStatus = ?, TypeID = ? WHERE RoomID = ?";
        try {
            return jdbcTemplate.update(sql, room.getContainedIn(), room.getFloorNo(), room.getCost(), room.getOccupancy(), room.getAvailabilityStatus(), room.getTypeID(), room.getRoomID());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteRoom(int roomID) {
        String sql = "DELETE FROM Room WHERE RoomID = ?";
        try {
            return jdbcTemplate.update(sql, roomID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}