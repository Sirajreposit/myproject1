package com.revtickets.event.repository;

import com.revtickets.event.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowId(Long showId);
    List<Seat> findByShowIdAndStatus(Long showId, Seat.SeatStatus status);
    List<Seat> findByShowIdAndSeatNumberIn(Long showId, List<String> seatNumbers);
    void deleteByShowId(Long showId);
}
