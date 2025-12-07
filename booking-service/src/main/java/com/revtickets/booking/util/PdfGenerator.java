package com.revtickets.booking.util;

import org.springframework.stereotype.Component;

@Component
public class PdfGenerator {

    public String generateTicketPdf(String ticketNumber, String eventDetails) {
        // PDF generation logic stub
        return "/static/tickets/" + ticketNumber + ".pdf";
    }
}
