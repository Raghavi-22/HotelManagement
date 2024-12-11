package demo.demo.Repository;
import demo.demo.Module.Booking;
import demo.demo.Module.Room;
import demo.demo.jsonResponse.GetBooking;
import demo.demo.jsonResponse.GetServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

@Repository
public class BookingRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RoomRepository RoomRepository;
    private final RoomRequestRepository roomRequestRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceRequestRepository serviceRequestRepository;


    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate, demo.demo.Repository.RoomRepository roomRepository, RoomRequestRepository roomRequestRepository, RoomTypeRepository roomTypeRepository, ServiceRepository serviceRepository, ServiceRequestRepository serviceRequestRepository) {

        this.jdbcTemplate = jdbcTemplate;
        RoomRepository = roomRepository;
        this.roomRequestRepository = roomRequestRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.serviceRepository = serviceRepository;
        this.serviceRequestRepository = serviceRequestRepository;
    }

    public int getDeluxe(int hotelid, LocalDate in, LocalDate out) {
        String sql = "SELECT count(*) FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Deluxe') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{hotelid, in, out});
        } catch (Exception e) {
            System.out.println(e);
        }
        return 3;
    }

    public int getSuperDeluxe(int hotelid, LocalDate in, LocalDate out) {
        String sql = "SELECT count(*) FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Super Deluxe') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{hotelid, in, out});
        } catch (Exception e) {
            System.out.println(e);
        }
        return 3;
    }

    public int getCottage(int hotelid, LocalDate in, LocalDate out) {
        String sql = "SELECT count(*) FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Cottage') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{hotelid, in, out});
        } catch (Exception e) {
            System.out.println(e);
        }
        return 3;
    }

    public int getImperial(int hotelid, LocalDate in, LocalDate out) {
        System.out.println(out);
        String sql = "SELECT count(*) FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Imperial') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{hotelid, in, out});
        } catch (Exception e) {
            System.out.println(e);
        }
        return 3;
    }

    public int getClubroomsWithJharokha(int hotelid, LocalDate in, LocalDate out) {
        String sql = "SELECT count(*) FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Club rooms with jharokha') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{hotelid, in, out});
        } catch (Exception e) {
            System.out.println(e);
        }
        return 3;
    }

    public List<Room> getRoomDeluxe(int hotelId, LocalDate in, LocalDate out) {
        String sql = "SELECT * FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Deluxe') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";

        // Print the SQL query and the parameter values
        System.out.println("SQL Query: " + sql);
        System.out.println("Parameters: hotelId=" + hotelId + ", in=" + in + ", out=" + out);

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), hotelId, in, out);
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }


    public List<Room> getRoomSuperDeluxe(int hotelId, LocalDate in, LocalDate out) {
        String sql = "SELECT * FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Super Deluxe') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), new Object[]{hotelId, in, out});
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<Room> getRoomCottage(int hotelId, LocalDate in, LocalDate out) {
        String sql = "SELECT * FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Cottage') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), new Object[]{hotelId, in, out});
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<Room> getRoomImperial(int hotelId, LocalDate in, LocalDate out) {
        String sql = "SELECT * FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Imperial') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), new Object[]{hotelId, in, out});
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public List<Room> getRoomClubroomsWithJharokha(int hotelId, LocalDate in, LocalDate out) {
        String sql = "SELECT * FROM Room WHERE ContainedIn = ? " +
                "AND TypeID IN (SELECT TypeID FROM RoomType WHERE TypeName = 'Club rooms with jharokha') " +
                "AND RoomID NOT IN " +
                "(SELECT rr.RoomID FROM RoomRequest rr " +
                "WHERE rr.AvailabilityStatus = 'Yes' " + // Filter for rooms with status 'Yes'
                "AND rr.CheckOut >= ? AND rr.CheckIn <= ?)";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class), new Object[]{hotelId, in, out});
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public int book(UUID bookingID, UUID roomReqNo, int discountNo, UUID serviceReqNo) {
        String sql = "INSERT INTO Booking (BookingID, RoomReqNo, BookingTime, Date, DiscountNo, ServiceReqNo) VALUES (?, ?, CURRENT_TIME, CURRENT_DATE, ?, ?)";

        // Use a conditional to determine if roomReqNo and serviceReqNo are null
        return jdbcTemplate.update(sql,
                bookingID.toString(),
                roomReqNo != null ? roomReqNo.toString() : null,  // Handle roomReqNo being null
                discountNo,
                serviceReqNo != null ? serviceReqNo.toString() : null); // Handle serviceReqNo being null
    }


    public List<Booking> getBookings(UUID customerID) {
        String sql = "SELECT * FROM Booking WHERE BookingID IN (SELECT bookingID FROM Book_Room WHERE UserId = ?)";
        List<Booking> bookings = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class), customerID.toString());
        return bookings;
    }


    public int numDeluxe(UUID bookingID) {
        String sql = "Select count(*) from Room where roomID in (Select roomID from book_room where bookingID=?) and roomCodeID IN" +
                "(select roomCodeID from Room_Type where typeName='Deluxe')";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{bookingID.toString()});

    }

    public int numSuperDeluxe(UUID bookingID) {
        String sql = "Select count(*) from Room where roomID in (Select roomID from Book_Room where bookingID=?) and roomCodeID IN" +
                "(select roomCodeID from Room_Type where typeName='Super Deluxe')";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{bookingID.toString()});

    }

    public int numCottage(UUID bookingID) {
        String sql = "Select count(*) from Room where roomID in (Select roomID from Book_Room where bookingID=?) and roomCodeID IN" +
                "(select roomCodeID from Room_Type where typeName='Cottage')";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{bookingID.toString()});

    }

    public int numImperial(UUID bookingID) {
        String sql = "Select count(*) from Room where roomID in (Select roomID from Book_Room where bookingID=?) and roomCodeID IN" +
                "(select roomCodeID from Room_Type where typeName='Imperial')";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{bookingID.toString()});

    }

    public int numClubroomsWithJharokha(UUID bookingID) {
        String sql = "Select count(*) from Room where roomID in (Select roomID from Book_Room where bookingID=?) and roomCodeID IN" +
                "(select roomCodeID from Room_Type where typeName='Club rooms with jharokha')";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{bookingID.toString()});

    }

    public void addRoomRequest(UUID roomRequestID, UUID roomID, LocalDate checkInDate, LocalDate checkOutDate, UUID userID) {
        String sql = "INSERT INTO RoomRequest (RoomRequestID, RoomID, CheckIn, CheckOut, UserNo) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, roomRequestID.toString(), roomID.toString(), checkInDate, checkOutDate, userID.toString());
    }


    public int check(UUID roomNo, LocalDate checkIn, LocalDate checkOut) {
        String sql = """
                    SELECT COUNT(*)
                    FROM Room r
                    WHERE r.RoomID NOT IN (
                        SELECT rr.RoomID
                        FROM RoomRequest rr
                        WHERE rr.AvailabilityStatus = 'Yes'
                        AND rr.RoomID = ?
                        AND rr.CheckOut >= ? 
                        AND rr.CheckIn <= ?
                    )
                """;

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{roomNo.toString(), checkIn, checkOut}, Integer.class);
        } catch (Exception e) {
            System.out.println("Error fetching available rooms: " + e.getMessage());
            return 0;
        }
    }


    //    public List<GetBooking> getGetBooking(UUID customerID){
//        List<Booking> bookings = getBookings(customerID);
//        System.out.println(customerID);
//        List<GetBooking> gb = new ArrayList<>();
//        for(int i=0; i<bookings.size(); i++){
//            System.out.println("d");
//            System.out.println(bookings.get(i).getCheckInDate());
//            System.out.println(bookings.get(i).getBookingID());
//            System.out.println(bookings.get(i).getCheckOutDate());
//            BookRoom bk = bookRoomRepository.getBookRoomByBookingID(bookings.get(i).getBookingID());
//            Room room = roomRepository.getRoomByID(bk.getRoomID());
//            System.out.println(room.getRoomID());
//            RoomType rt = roomTypeRepository.getRoomTypeByID(room.getRoomCodeID());
//            int cost = room.getCost();
//            String roomNo = room.getRoomNo();
//            GetBooking getBook = new GetBooking(bookings.get(i).getBookingID(), bookings.get(i).getCheckInDate(), bookings.get(i).getCheckOutDate(), bookings.get(i).getNoOfGuests(), new ArrayList<>(), cost, roomNo, rt.getTypeName());
//            gb.add(getBook);
//        }
//        return gb;
//    }
//
    public List<GetBooking> getAllBookings() {
        String sql = """
    SELECT b.BookingID AS bookingID, 
           rr.CheckIn AS checkInDate, 
           rr.CheckOut AS checkOutDate, 
           b.Date AS date, 
           b.BookingTime AS time, 
           rt.TypeName AS typeName, 
           r.RoomNo AS roomNo, 
           p.Amount AS cost  -- Assuming 'Cost' exists in the Payment table
    FROM RoomRequest rr
    JOIN Room r ON rr.RoomID = r.RoomID
    JOIN Booking b ON b.RoomReqNo = rr.RoomRequestID
    JOIN RoomType rt ON r.TypeID = rt.TypeID
    JOIN Payment p ON b.BookingID = p.SettlementID  -- Joining with Payment table
    WHERE rr.AvailabilityStatus = 'Yes'
    """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetBooking.class));
    }

    public List<GetBooking> getBooking(UUID userID) {
        String sql = """
        SELECT b.BookingID AS bookingID, 
               rr.CheckIn AS checkInDate, 
               rr.CheckOut AS checkOutDate, 
               b.Date AS date, 
               b.BookingTime AS time, 
               rt.TypeName AS typeName, 
               r.RoomNo AS roomNo, 
               p.Amount AS cost  -- Assuming 'Cost' exists in the Payment table
        FROM RoomRequest rr
        JOIN Room r ON rr.RoomID = r.RoomID
        JOIN Booking b ON b.RoomReqNo = rr.RoomRequestID
        JOIN RoomType rt ON r.TypeID = rt.TypeID
        JOIN Payment p ON b.BookingID = p.SettlementID  -- Joining with Payment table
        WHERE rr.AvailabilityStatus = 'Yes' AND rr.UserNo = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetBooking.class), userID.toString());
    }

    public Optional<UUID> getRoomRequestIdByBookingId(UUID bookingID) {
        String sql = "SELECT RoomReqNo AS RoomReqNo FROM Booking WHERE BookingID = ?";

        try {
            // Return Optional<UUID>
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, UUID.class, bookingID.toString())
            );
        } catch (EmptyResultDataAccessException e) {
            // No matching BookingID found
            return Optional.empty();
        }
    }
    public Optional<UUID> getserviceRequestIdByBookingId(UUID bookingID) {
        String sql = "SELECT ServiceReqNo AS RoomReqNo FROM Booking WHERE BookingID = ?";
        try {
            // Return Optional<UUID>
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, UUID.class, bookingID.toString())
            );
        } catch (EmptyResultDataAccessException e) {
            // No matching BookingID found
            return Optional.empty();
        }
    }
    public int update(UUID bookingID) {
        String sql = "UPDATE Booking SET Status = 'No' WHERE BookingID = ?";
        return jdbcTemplate.update(sql, bookingID.toString());
    }

    public int countofroombook(UUID userID) {
            return 0;
    }

    public List<GetServiceBook> getGetserviceBookings(UUID userID) {
        String sql = """
        SELECT b.BookingID AS bookingID, 
               sr.Date AS date, 
               sr.time AS time, 
               b.Date AS bookingDate,
                b.BookingTime AS bookingTime,
               s.ServiceName AS typeName, 
               p.Amount AS cost  -- Assuming 'Cost' exists in the Payment table
        FROM ServiceRequest sr
        JOIN Service s ON sr.ServiceNo = s.ServiceID
        JOIN Booking b ON b.ServiceReqNo = sr.RequestServiceID
        JOIN Payment p ON b.BookingID = p.SettlementID  -- Joining with Payment table
        WHERE sr.AvailabilityStatus = 'Yes' AND sr.ServiceRequestedBy = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetServiceBook.class), userID.toString());
    }

    public int checkuser(UUID userId) {
        String sql = """
        SELECT COUNT(*)
         FROM RoomRequest r
         JOIN Booking b ON r.RoomRequestID = b.RoomReqNo
         WHERE r.UserNo = ?
           AND r.AvailabilityStatus = 'Yes'
    """;
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId.toString()}, Integer.class);
        } catch (Exception e) {
            System.out.println("Error fetching available rooms: " + e.getMessage());
            return 0;
        }

    }
}
//
//    public int deleteBooking(UUID bookingID){
//        String sql = "DELETE FROM booking where bookingID = ?";
//        try {
//            return jdbcTemplate.update(sql, bookingID.toString());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return 0;
//    }
//
//    public List<Booking> getAllBookings() {
//        String sql = "SELECT * FROM Booking";
//        List<Booking> e = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Booking.class));
//        return e;
//    }

