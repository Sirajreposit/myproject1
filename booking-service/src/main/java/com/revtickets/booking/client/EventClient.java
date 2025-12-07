package com.revtickets.booking.client;

import com.revtickets.booking.client.dto.ShowDTO;
import com.revtickets.booking.client.dto.BookSeatsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "event-service")
public interface EventClient {
    @GetMapping("/api/shows/{id}")
    ShowDTO getShowById(@PathVariable("id") Long id);

    @PostMapping("/api/seats/book")
    void bookSeats(@RequestBody BookSeatsRequest request);
}
