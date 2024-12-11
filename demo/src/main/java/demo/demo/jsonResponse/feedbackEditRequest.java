package demo.demo.jsonResponse;

import java.util.UUID;

public class feedbackEditRequest {
    private UUID reviewId;   // ID of the feedback entry to edit
    private String comments;  // Name of the field being edited (ratings, comments, etc.)
    private  int ratings; // New value for that field

    public feedbackEditRequest(UUID reviewId, String comments, int ratings) {
        this.reviewId = reviewId;
        this.comments = comments;
        this.ratings = ratings;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
