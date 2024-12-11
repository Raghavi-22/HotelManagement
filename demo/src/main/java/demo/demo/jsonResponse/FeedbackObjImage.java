package demo.demo.jsonResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

public class FeedbackObjImage {
    private UUID reviewId;
    private int ratings;
    private int hotelNo;
    private UUID userNo;
    private String comments;
    private Date date;
    private Time time;
    private List<String> image;

    public FeedbackObjImage() {
    }

    public FeedbackObjImage(UUID reviewId, int ratings, int hotelNo, UUID userNo, String comments, Date date, Time time, List<String> image) {
        this.reviewId = reviewId;
        this.ratings = ratings;
        this.hotelNo = hotelNo;
        this.userNo = userNo;
        this.comments = comments;
        this.date = date;
        this.time = time;
        this.image = image;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public int getHotelNo() {
        return hotelNo;
    }

    public void setHotelNo(int hotelNo) {
        this.hotelNo = hotelNo;
    }

    public UUID getUserNo() {
        return userNo;
    }

    public void setUserNo(UUID userNo) {
        this.userNo = userNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
}
