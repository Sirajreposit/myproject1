package com.revtickets.event.repository;

import com.revtickets.event.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    List<Venue> findByCityAndIsActiveTrue(String city);
    List<Venue> findByIsActiveTrue();
}
