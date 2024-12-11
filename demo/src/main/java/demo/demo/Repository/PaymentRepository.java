package demo.demo.Repository;

import demo.demo.Module.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addPayment(UUID id, UUID bookingId, String PaymentMode, String TransactionId,double cost) {
        String sql = "INSERT INTO Payment (PaymentID, SettlementID, PaymentMode, Amount, Date, Time, TransactionID) VALUES (?, ?, ?, ?, CURRENT_DATE,CURRENT_TIME, ?)";
        try {
            return jdbcTemplate.update(sql, id.toString(),bookingId.toString() ,PaymentMode, cost,TransactionId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Payment getPaymentByID(int paymentID) {
        String sql = "SELECT * FROM Payment WHERE PaymentID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Payment.class), paymentID);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM Payment";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Payment.class));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int updatePayment(Payment payment) {
        String sql = "UPDATE Payment SET SettlementID = ?, PaymentMode = ?, Amount = ?, Date = ?, Time = ?, PaymentStatus = ?, TransactionID = ? WHERE PaymentID = ?";
        try {
            return jdbcTemplate.update(sql, payment.getSettlementId(), payment.getPaymentMode(), payment.getAmount(), payment.getDate(), payment.getTime(), payment.getPaymentStatus(), payment.getTransactionId(), payment.getPaymentId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deletePayment(int paymentID) {
        String sql = "DELETE FROM Payment WHERE PaymentID = ?";
        try {
            return jdbcTemplate.update(sql, paymentID);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
