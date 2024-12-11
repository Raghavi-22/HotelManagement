package demo.demo.Module;


import java.util.UUID;

public class Discount {
    private int discountId;
    private String discountName;
    private double value;
    private String status;
    private int scoreRequired;
    private String description;


    public Discount(int discountId, double value, String status, int scoreRequired, String description, String discountName) {
        this.discountId = discountId;
        this.value = value;
        this.status = status;
        this.scoreRequired = scoreRequired;
        this.description = description;
        this.discountName = discountName;
    }

    public Discount() {

    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int   discountId) {
        this.discountId = discountId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScoreRequired() {
        return scoreRequired;
    }

    public void setScoreRequired(int scoreRequired) {
        this.scoreRequired = scoreRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }
}

