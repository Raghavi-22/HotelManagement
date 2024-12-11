package demo.demo.jsonResponse;


import java.util.UUID;

public class Rooms {
    private UUID roomID;
    private String roomType;
    private int cost;
    private int occupancyLimit;
   private int hotelno;
    public Rooms() {
    }

    public Rooms(UUID roomID, String roomType, int cost, int occupancyLimit,int hotelno) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.cost = cost;
        this.occupancyLimit = occupancyLimit;
        this.hotelno=hotelno;
    }

    public int getHotelno() {
        return hotelno;
    }

    public void setHotelno(int hotelno) {
        this.hotelno = hotelno;
    }

    public UUID getRoomID() {
        return roomID;
    }

    public void setRoomID(UUID roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOccupancyLimit() {
        return occupancyLimit;
    }

    public void setOccupancyLimit(int occupancyLimit) {
        this.occupancyLimit = occupancyLimit;
    }
}

