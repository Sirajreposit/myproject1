package com.revtickets.payment.service;

import com.revtickets.payment.dto.PaymentRequest;
import com.revtickets.payment.model.Payment;
import com.revtickets.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setPaymentId("PAY-" + UUID.randomUUID().toString());
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setMethod(Payment.PaymentMethod.valueOf(request.getMethod().toUpperCase())); // Simplification
        payment.setStatus(Payment.PaymentStatus.SUCCESS); // Mocking success

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId).orElse(null);
    }
}
