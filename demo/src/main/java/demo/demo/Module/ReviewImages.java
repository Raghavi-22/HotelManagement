package demo.demo.Module;


import java.util.UUID;

public class ReviewImages {
    private UUID reviewNo;
    private String reviewImage;

    public ReviewImages(UUID reviewNo, String reviewImage) {
        this.reviewNo = reviewNo;
        this.reviewImage = reviewImage;
    }

    // Getters and Setters
    public UUID getReviewNo() { return reviewNo; }
    public void setReviewNo(UUID reviewNo) { this.reviewNo = reviewNo; }

    public String getReviewImage() { return reviewImage; }
    public void setReviewImage(String reviewImage) { this.reviewImage = reviewImage; }
}

