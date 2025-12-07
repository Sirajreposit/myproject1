package com.revtickets.booking.service;

import com.revtickets.booking.client.AuthClient;
import com.revtickets.booking.client.EventClient;
import com.revtickets.booking.client.NotificationClient;
import com.revtickets.booking.client.PaymentClient;
import com.revtickets.booking.client.dto.BookSeatsRequest;
import com.revtickets.booking.client.dto.NotificationRequest;
import com.revtickets.booking.client.dto.PaymentDTO;
import com.revtickets.booking.client.dto.PaymentRequest;
import com.revtickets.booking.client.dto.ShowDTO;
import com.revtickets.booking.client.dto.UserDTO;
import com.revtickets.booking.dto.BookingRequest;
import com.revtickets.booking.exception.ResourceNotFoundException;
import com.revtickets.booking.model.Booking;
import com.revtickets.booking.model.Ticket;
import com.revtickets.booking.repository.BookingRepository;
import com.revtickets.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private EventClient eventClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private TicketService ticketService;

    @Transactional
    public Booking createBooking(BookingRequest request) {
        // 1. Fetch Details
        UserDTO user = authClient.getUserById(request.getUserId());
        ShowDTO show = eventClient.getShowById(request.getShowId());

        if (user == null) throw new ResourceNotFoundException("User not found");
        if (show == null) throw new ResourceNotFoundException("Show not found");

        // 2. Create Pending Booking
        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setShowId(show.getId());
        booking.setNumberOfSeats(request.getSeatNumbers().size());
        booking.setSeatNumbers(String.join(",", request.getSeatNumbers()));
        booking.setTotalAmount(show.getEvent().getPrice() * request.getSeatNumbers().size());
        booking.setStatus(Booking.BookingStatus.PENDING);
        booking.setBookingDate(LocalDateTime.now());
        
        booking = bookingRepository.save(booking);

        // 3. Book Seats via Event Service
        try {
            BookSeatsRequest bookRequest = new BookSeatsRequest();
            bookRequest.setShowId(show.getId());
            bookRequest.setSeatNumbers(request.getSeatNumbers());
            bookRequest.setBookingId(booking.getId());
            eventClient.bookSeats(bookRequest);
        } catch (Exception e) {
            booking.setStatus(Booking.BookingStatus.CANCELLED);
            bookingRepository.save(booking);
            throw new RuntimeException("Failed to book seats: " + e.getMessage());
        }

        // 4. Process Payment (Via Payment Service)
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setBookingId(booking.getId());
        paymentRequest.setAmount(booking.getTotalAmount());
        paymentRequest.setMethod(request.getPaymentMethod());
        
        PaymentDTO paymentResponse = paymentClient.processPayment(paymentRequest);

        // 5. Confirm Booking
        booking.setPaymentId(paymentResponse.getPaymentId());
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        // 6. Generate Ticket
        Ticket ticket = ticketService.generateTicket(booking);
        booking.setQrCode(ticket.getQrCode()); // For immediate response

        // 7. Send Email (Via Notification Service)
        try {
            NotificationRequest notifRequest = new NotificationRequest();
            notifRequest.setUserId(user.getId());
            notifRequest.setTitle("Booking Confirmation");
            notifRequest.setMessage("Your booking for " + show.getEvent().getTitle() + " at " + show.getVenue().getName() + " is confirmed. Seat(s): " + booking.getSeatNumbers());
            notifRequest.setType("EMAIL");
            notificationClient.sendNotification(notifRequest);
        } catch (Exception e) {
            // Log error but don't fail transaction
            System.err.println("Failed to send notification: " + e.getMessage());
        }

        return booking;
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        Ticket ticket = ticketRepository.findByBookingId(id).orElse(null);
        if (ticket != null) {
            booking.setQrCode(ticket.getQrCode());
        }
        
        return booking;
    }
}
