package com.revtickets.event.service;

import com.revtickets.event.model.Show;
import com.revtickets.event.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public List<Show> getAllShows() {
        return showRepository.findByIsActiveTrue();
    }

    public List<Show> getShowsByEvent(Long eventId) {
        return showRepository.findByEventIdAndIsActiveTrue(eventId);
    }

    public List<Show> getShowsByVenue(Long venueId) {
        return showRepository.findByVenueIdAndIsActiveTrue(venueId);
    }

    public Show createShow(Show show) {
        return showRepository.save(show);
    }
    
    public Show getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }
}
