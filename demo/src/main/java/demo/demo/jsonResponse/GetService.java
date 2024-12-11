package demo.demo.jsonResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class GetService {
    private UUID serviceID;
    private LocalTime time;
    private LocalDate date;

    // Default constructor
    public GetService() {}

    // Getters and Setters
    public UUID getServiceID() {
        return serviceID;
    }

    public void setServiceID(UUID serviceID) {
        this.serviceID = serviceID;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
