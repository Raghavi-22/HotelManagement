package demo.demo.Module;



import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

public class RoomRequest {
    private UUID roomRequestId;
    private UUID roomNo;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private UUID userNo;
    private String status;

    public RoomRequest() {
    }

    public RoomRequest(UUID roomRequestId, UUID roomNo, LocalDate checkIn, LocalDate checkOut, String status, UUID userNo) {
        this.roomRequestId = roomRequestId;
        this.roomNo = roomNo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.userNo = userNo;
    }

    public UUID getRoomRequestId() {
        return roomRequestId;
    }

    public void setRoomRequestId(UUID roomRequestId) {
        this.roomRequestId = roomRequestId;
    }

    public UUID getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(UUID roomNo) {
        this.roomNo = roomNo;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUserNo() {
        return userNo;
    }

    public void setUserNo(UUID userNo) {
        this.userNo = userNo;
    }
}
