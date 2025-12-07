package com.revtickets.event.repository;

import com.revtickets.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByIsActiveTrue();
    List<Event> findByCategoryAndIsActiveTrue(Event.Category category);
    
    @Query("SELECT e FROM Event e WHERE " +
           "(LOWER(e.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.city) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.description) LIKE LOWER(CONCAT('%', :query, '%'))) " +
           "AND e.isActive = true")
    List<Event> searchEvents(@Param("query") String query);
}
