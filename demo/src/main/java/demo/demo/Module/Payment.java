package demo.demo.Module;



import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public class Payment {
    private UUID paymentId;
    private UUID settlementId;
    private String paymentMode;
    private double amount;
    private Date date;
    private Time time;
    private String paymentStatus;
    private String transactionId;

    public Payment(UUID paymentId, UUID settlementId, String paymentMode, double amount, Date date, Time time, String paymentStatus, String transactionId) {
        this.paymentId = paymentId;
        this.settlementId = settlementId;
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
    }

    // Getters and Setters
    public UUID getPaymentId() { return paymentId; }
    public void setPaymentId(UUID paymentId) { this.paymentId = paymentId; }

    public UUID getSettlementId() { return settlementId; }
    public void setSettlementId(UUID settlementId) { this.settlementId = settlementId; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Time getTime() { return time; }
    public void setTime(Time time) { this.time = time; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}

