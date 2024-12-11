package demo.demo.Services;


import demo.demo.Module.Payment;
import demo.demo.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public boolean addPayment(UUID id, UUID bookingId,String PaymentMode,String TransactionId,double cost) {
        int rowsAffected = paymentRepository.addPayment(id,bookingId,PaymentMode,TransactionId,cost);
        return rowsAffected > 0;
    }
}

