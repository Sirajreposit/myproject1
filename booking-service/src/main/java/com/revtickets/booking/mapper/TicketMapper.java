package com.revtickets.booking.mapper;

import com.revtickets.booking.dto.TicketResponse;
import com.revtickets.booking.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponse toResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setTicketNumber(ticket.getTicketNumber());
        // Details like Event Title, Venue Name need to be fetched from Event Service
        // For now, these might be null or populated if we fetch them.
        // I will add comments or logic to handle this separation later. 
        // We will likely need to enrich this response in the service layer by calling Event Service.
        response.setSeatNumbers(ticket.getBooking().getSeatNumbers());
        response.setQrCode(ticket.getQrCode());
        return response;
    }
}
