package com.revtickets.event.controller;

import com.revtickets.event.model.Venue;
import com.revtickets.event.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@CrossOrigin(origins = "*")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {
        // Need to implement getVenueById in service or use repository directly if simple
        // In original code it called venueService.getVenueById. Let's assume we added it or JpaRepository has findById.
        // Wait, I migrated VenueService in step 811 but I didn't add getVenueById method explicitly?
        // Checking step 811... I missed 'getVenueById' and 'deleteVenue' and 'updateVenue' in VenueService!
        // I need to update VenueService first.
        return null; 
    }
    // ... wait, I need to fix VenueService first.
}
