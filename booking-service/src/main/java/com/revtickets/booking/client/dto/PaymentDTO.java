package com.revtickets.booking.client.dto;

public class PaymentDTO {
    private String paymentId;
    private String status;

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
