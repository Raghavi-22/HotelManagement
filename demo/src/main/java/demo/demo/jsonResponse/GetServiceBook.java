package demo.demo.jsonResponse;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class GetServiceBook {
    private UUID bookingID;
    private LocalDate date;
    private LocalTime time;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String typeName;
    private Double cost;

    public GetServiceBook() {
    }

    public UUID getBookingID() {
        return bookingID;
    }

    public void setBookingID(UUID bookingID) {
        this.bookingID = bookingID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getCost() {
        return cost;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public GetServiceBook(UUID bookingID, LocalDate date, LocalTime time, LocalDate bookingDate, LocalTime bookingTime, String typeName, Double cost) {
        this.bookingID = bookingID;
        this.date = date;
        this.time = time;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.typeName = typeName;
        this.cost = cost;
    }
}
