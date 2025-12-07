package com.revtickets.review.controller;

import com.revtickets.review.dto.ReviewRequest;
import com.revtickets.review.model.Review;
import com.revtickets.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Review>> getReviewsByEventId(@PathVariable Long eventId) {
        return ResponseEntity.ok(reviewService.getReviewsByEventId(eventId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }
}
