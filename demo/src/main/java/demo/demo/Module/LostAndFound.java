package demo.demo.Module;



import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public class LostAndFound {
    private int sNo;
    private UUID handledBy;
    private String itemType;
    private String foundLocation;
    private String status;
    private Date date;
    private Time time;
    private String description;
    private  int hotelno;

    // Getters and Setters


    public LostAndFound(int  sNo, UUID handledBy, String itemType, String foundLocation, String status, Date date, Time time, String description,int hotelno) {
        this.sNo = sNo;
        this.handledBy = handledBy;
        this.itemType = itemType;
        this.foundLocation = foundLocation;
        this.status = status;
        this.date = date;
        this.time = time;
        this.description = description;
        this.hotelno=hotelno;
    }

    public int getHotelno() {
        return hotelno;
    }

    public void setHotelno(int hotelno) {
        this.hotelno = hotelno;
    }

    public int getsNo() {
        return sNo;
    }

    public LostAndFound() {
    }

    public void setsNo(int   sNo) {
        this.sNo = sNo;
    }

    public UUID getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(UUID handledBy) {
        this.handledBy = handledBy;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}