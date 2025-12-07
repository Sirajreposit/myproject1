package com.revtickets.booking.client.dto;

import java.time.LocalDateTime;

public class ShowDTO {
    private Long id;
    private LocalDateTime showTime;
    private EventDTO event;
    private VenueDTO venue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getShowTime() { return showTime; }
    public void setShowTime(LocalDateTime showTime) { this.showTime = showTime; }

    public EventDTO getEvent() { return event; }
    public void setEvent(EventDTO event) { this.event = event; }

    public VenueDTO getVenue() { return venue; }
    public void setVenue(VenueDTO venue) { this.venue = venue; }
}
