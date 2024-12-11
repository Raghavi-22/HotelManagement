package demo.demo.Module;
import java.sql.Time;
import java.sql.Date;
import java.util.UUID;

public class Booking {
    private UUID bookingId;
    private UUID roomReqNo;
    private Time bookingTime;
    private Date date;
    private UUID discountNo; // Nullable
    private UUID serviceReqNo; // Nullable

    // Getters and Setters
    public UUID getBookingId() { return bookingId; }
    public void setBookingId(UUID bookingId) { this.bookingId = bookingId; }

    public UUID getRoomReqNo() { return roomReqNo; }
    public void setRoomReqNo(UUID roomReqNo) { this.roomReqNo = roomReqNo; }

    public Time getBookingTime() { return bookingTime; }
    public void setBookingTime(Time bookingTime) { this.bookingTime = bookingTime; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }


    public UUID getDiscountNo() { return discountNo; }
    public void setDiscountNo(UUID discountNo) { this.discountNo = discountNo; }

    public Booking(UUID bookingId, UUID roomReqNo, Time bookingTime, Date date, UUID discountNo, UUID serviceReqNo) {
        this.bookingId = bookingId;
        this.roomReqNo = roomReqNo;
        this.bookingTime = bookingTime;
        this.date = date;

        this.discountNo = discountNo;
        this.serviceReqNo = serviceReqNo;
    }

    public UUID getServiceReqNo() { return serviceReqNo; }
    public void setServiceReqNo(UUID serviceReqNo) { this.serviceReqNo = serviceReqNo; }
}
