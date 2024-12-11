package demo.demo.Module;

import java.util.UUID;

public class Room {
    private UUID roomID;
    private int containedIn;
    private int roomNo;
    private double cost;
    private int occupancy;
    private String availabilityStatus;
    private UUID typeID;

//    public Room() {
//    }

    // Getters and Setters
    public UUID getRoomID() {
        return roomID;
    }

    public void setRoomID(UUID roomID) {
        this.roomID = roomID;
    }

    public int getContainedIn() {
        return containedIn;
    }

    public void setContainedIn(int containedIn) {
        this.containedIn = containedIn;
    }

//    public Room(UUID roomID, int containedIn, int floorNo, double cost, int occupancy, String availabilityStatus, int typeID) {
//        this.roomID = roomID;
//        this.containedIn = containedIn;
//        this.roomNo = floorNo;
//        this.cost = cost;
//        this.occupancy = occupancy;
//        this.availabilityStatus = availabilityStatus;
//        this.typeID = typeID;
//    }

    public int getFloorNo() {
        return roomNo;
    }

    public void setFloorNo(int floorNo) {
        this.roomNo = floorNo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public UUID getTypeID() {
        return typeID;
    }

    public void setTypeID(UUID typeID) {
        this.typeID = typeID;
    }
}