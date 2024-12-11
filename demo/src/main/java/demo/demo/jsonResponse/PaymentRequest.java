package demo.demo.jsonResponse;

import java.util.UUID;

public  class PaymentRequest {
    private UUID roomReqId;
    private double cost;
    private String paymentMode;
    private String transactionId;
    private int discountId;

    // Getters and setters
    public UUID getRoomReqId() {
        return roomReqId;
    }

    public void setRoomReqId(UUID roomReqId) {
        this.roomReqId = roomReqId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
