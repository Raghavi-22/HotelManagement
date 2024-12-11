package demo.demo.Module;


import java.util.UUID;

public class LostAndFoundImage {
    private int sNo;
    private String image;

    public LostAndFoundImage(int  sNo, String image) {
        this.sNo = sNo;
        this.image = image;
    }

    // Getters and Setters
    public int getsNo() { return sNo; }
    public void setsNo(int   sNo) { this.sNo = sNo; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
