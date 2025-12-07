package com.revtickets.booking.client;

import com.revtickets.booking.client.dto.PaymentDTO;
import com.revtickets.booking.client.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/api/payments/process")
    PaymentDTO processPayment(@RequestBody PaymentRequest request);
}
