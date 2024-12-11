package demo.demo.jsonResponse;

import java.util.UUID;

public class CancelRequest {
    private UUID bookingID;
    private String reason;

    public UUID getBookingID() {
        return bookingID;
    }

    public void setBookingID(UUID bookingID) {
        this.bookingID = bookingID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
