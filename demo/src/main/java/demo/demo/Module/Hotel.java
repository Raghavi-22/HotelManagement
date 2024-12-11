package demo.demo.Module;

import java.math.BigDecimal;
import java.util.UUID;

public class Hotel {
    private int hotelID;
    private String hotelName;
    private String city;
    private String state;
    private String pin;
    private int rating;

    public Hotel(int hotelID, String hotelName, String city, String state, String pin, int   rating) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.city = city;
        this.state = state;
        this.pin = pin;
        this.rating = rating;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int   hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}


