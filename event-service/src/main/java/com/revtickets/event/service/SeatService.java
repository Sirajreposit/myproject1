package com.revtickets.event.service;

import com.revtickets.event.model.Seat;
import com.revtickets.event.model.Show;
import com.revtickets.event.repository.SeatRepository;
import com.revtickets.event.util.SeatLockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.revtickets.event.exception.ResourceNotFoundException;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatLockManager seatLockManager;

    public List<Seat> getSeatsByShowId(Long showId) {
        return seatRepository.findByShowId(showId);
    }

    public List<Seat> getAvailableSeats(Long showId) {
        return seatRepository.findByShowIdAndStatus(showId, Seat.SeatStatus.AVAILABLE);
    }

    public void generateSeatsForShow(Long showId, int totalSeats) {
        Show show = new Show();
        show.setId(showId);
        generateSeatsForShow(show, totalSeats);
    }

    @Transactional
    public void generateSeatsForShow(Show show, int totalSeats) {
        seatRepository.deleteByShowId(show.getId());
        
        List<Seat> seats = new ArrayList<>();
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        int seatsPerRow = (totalSeats + rows.length - 1) / rows.length;

        int seatCount = 0;
        for (int r = 0; r < rows.length && seatCount < totalSeats; r++) {
            String category;
            if (r < 2) category = "Regular"; // A, B
            else if (r < 8) category = "Silver"; // C, D, E, F, G, H
            else if (r < 13) category = "Gold"; // I, J, K, L, M
            else category = "Premium"; // N, O
            
            for (int i = 1; i <= seatsPerRow && seatCount < totalSeats; i++) {
                Seat seat = new Seat();
                seat.setShow(show);
                seat.setSeatNumber(rows[r] + i);
                seat.setSeatType(category);
                seat.setStatus(Seat.SeatStatus.AVAILABLE);
                seats.add(seat);
                seatCount++;
            }
        }
        seatRepository.saveAll(seats);
    }

    public boolean lockSeats(Long showId, List<String> seatNumbers) {
        for (String seatNumber : seatNumbers) {
            String seatKey = showId + "-" + seatNumber;
            if (!seatLockManager.lockSeat(seatKey)) {
                return false;
            }
        }
        return true;
    }

    public void unlockSeats(Long showId, List<String> seatNumbers) {
        for (String seatNumber : seatNumbers) {
            String seatKey = showId + "-" + seatNumber;
            seatLockManager.unlockSeat(seatKey);
        }
    }

    @Transactional
    public void bookSeats(Long showId, List<String> seatNumbers, Long bookingId) {
        List<Seat> seats = seatRepository.findByShowIdAndSeatNumberIn(showId, seatNumbers);
        
        if (seats.size() != seatNumbers.size()) {
            throw new ResourceNotFoundException("Some seats not found");
        }

        for (Seat seat : seats) {
            if (seat.getStatus() != Seat.SeatStatus.AVAILABLE) {
                // Ideally check if locked by current user, but simplify for now
                throw new IllegalStateException("Seat " + seat.getSeatNumber() + " is already booked or unavailable");
            }
            seat.setStatus(Seat.SeatStatus.BOOKED);
            seat.setBookingId(bookingId);
        }
        seatRepository.saveAll(seats);
    }
}
