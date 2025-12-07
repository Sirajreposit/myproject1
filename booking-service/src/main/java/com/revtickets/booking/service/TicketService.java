package com.revtickets.booking.service;

import com.revtickets.booking.dto.TicketResponse;
import com.revtickets.booking.exception.ResourceNotFoundException;
import com.revtickets.booking.mapper.TicketMapper;
import com.revtickets.booking.model.Booking;
import com.revtickets.booking.model.Ticket;
import com.revtickets.booking.repository.TicketRepository;
import com.revtickets.booking.util.PdfGenerator;
import com.revtickets.booking.util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private TicketMapper ticketMapper;

    public Ticket generateTicket(Booking booking) {
        try {
            Ticket ticket = new Ticket();
            ticket.setTicketNumber("TKT-" + UUID.randomUUID().toString());
            ticket.setBooking(booking);
            
            String qrCode = qrCodeGenerator.generateQRCode(ticket.getTicketNumber());
            ticket.setQrCode(qrCode);
            
            return ticketRepository.save(ticket);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate ticket", e);
        }
    }

    public TicketResponse getTicketByBookingId(Long bookingId) {
        Ticket ticket = ticketRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        return ticketMapper.toResponse(ticket);
    }

    public TicketResponse getTicketByNumber(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        return ticketMapper.toResponse(ticket);
    }
}
