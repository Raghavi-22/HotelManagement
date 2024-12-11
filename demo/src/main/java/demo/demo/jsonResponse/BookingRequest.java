package demo.demo.jsonResponse;
import java.time.LocalDate;

public class BookingRequest {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String roomType;
    private int noOfGuests;
    private int hotelId;

    public BookingRequest(LocalDate checkInDate, LocalDate checkOutDate, String roomType, int noOfGuests,int hotelId) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.noOfGuests = noOfGuests;
        this.hotelId=hotelId;
    }

    public BookingRequest() {
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}