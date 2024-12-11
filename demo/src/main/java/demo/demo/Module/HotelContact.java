package demo.demo.Module;


import java.util.UUID;

public class HotelContact {
    private int hotelNo;
    private String phone;

    public HotelContact(int  hotelNo, String phone) {
        this.hotelNo = hotelNo;
        this.phone = phone;
    }

    // Getters and Setters
    public int getHotelNo() { return hotelNo; }
    public void setHotelNo(int   hotelNo) { this.hotelNo = hotelNo; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

