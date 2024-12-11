package demo.demo.Module;


import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public class Cancellation {
    private UUID cancellationId;
    private UUID bookingNo;
    private Date date;
    private Time time;
    private String status;
    private String reason;

    // Getters and Setters
    public UUID getCancellationId() { return cancellationId; }
    public void setCancellationId(UUID cancellationId) { this.cancellationId = cancellationId; }

    public Cancellation(UUID cancellationId, UUID bookingNo, Date date, Time time, String status, String reason) {
        this.cancellationId = cancellationId;
        this.bookingNo = bookingNo;
        this.date = date;
        this.time = time;

        this.status = status;
        this.reason = reason;
    }

    public UUID getBookingNo() { return bookingNo; }
    public void setBookingNo(UUID bookingNo) { this.bookingNo = bookingNo; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Time getTime() { return time; }
    public void setTime(Time time) { this.time = time; }


    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
