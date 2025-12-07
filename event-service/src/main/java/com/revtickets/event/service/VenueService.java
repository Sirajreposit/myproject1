package com.revtickets.event.service;

import com.revtickets.event.model.Venue;
import com.revtickets.event.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VenueService {
    
    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findByIsActiveTrue();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new com.revtickets.event.exception.ResourceNotFoundException("Venue not found"));
    }

    public List<Venue> getVenuesByCity(String city) {
        return venueRepository.findByCityAndIsActiveTrue(city);
    }
    
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Long id, Venue venue) {
        Venue existingVenue = getVenueById(id);
        existingVenue.setName(venue.getName());
        existingVenue.setCity(venue.getCity());
        existingVenue.setAddress(venue.getAddress());
        existingVenue.setTotalSeats(venue.getTotalSeats());
        existingVenue.setType(venue.getType());
        return venueRepository.save(existingVenue);
    }

    public void deleteVenue(Long id) {
        Venue venue = getVenueById(id);
        venue.setIsActive(false);
        venueRepository.save(venue);
    }
}
