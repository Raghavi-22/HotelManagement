package demo.demo.Module;



import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public class Feedback {
    private UUID reviewId;
    private int ratings;
    private int hotelNo;
    private UUID userNo;
    private String comments;
    private Date date;
    private Time time;
    public Feedback() {
    }

    public Feedback(UUID reviewId, int ratings,int hotelNo, UUID userNo, String comments, Date date,Time time) {
        this.reviewId = reviewId;
        this.ratings = ratings;
        this.hotelNo=hotelNo;
        this.userNo = userNo;
        this.comments = comments;
        this.date = date;
        this.time=time;

    }

    // Getters and Setters
    public UUID getReviewId() { return reviewId; }
    public void setReviewId(UUID reviewId) { this.reviewId = reviewId; }

    public int getRatings() { return ratings; }
    public void setRatings(int ratings) { this.ratings = ratings; }

    public UUID getUserNo() { return userNo; }
    public void setUserNo(UUID  userNo) { this.userNo = userNo; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(int hotelNo) {
        this.hotelNo = hotelNo;
    }
}