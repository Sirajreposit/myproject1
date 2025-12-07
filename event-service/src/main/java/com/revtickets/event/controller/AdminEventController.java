package com.revtickets.event.controller;

import com.revtickets.event.dto.AdminEventRequest;
import com.revtickets.event.model.Event;
import com.revtickets.event.model.Show;
import com.revtickets.event.model.Venue;
import com.revtickets.event.service.EventService;
import com.revtickets.event.service.ShowService;
import com.revtickets.event.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private ShowService showService;

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody AdminEventRequest request) {
        return ResponseEntity.ok(eventService.createEvent(request));
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody AdminEventRequest request) {
        return ResponseEntity.ok(eventService.updateEvent(id, request));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/venues")
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        return ResponseEntity.ok(venueService.createVenue(venue));
    }

    @PostMapping("/shows")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        return ResponseEntity.ok(showService.createShow(show));
    }
}
