package demo.demo.Module;


import java.util.UUID;

public class HotelEmail {
    private int hotelNo;
    private String email;

    public HotelEmail(int    hotelNo, String email) {
        this.hotelNo = hotelNo;
        this.email = email;
    }

    // Getters and Setters
    public int getHotelNo() { return hotelNo; }
    public void setHotelNo(int   hotelNo) { this.hotelNo = hotelNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

