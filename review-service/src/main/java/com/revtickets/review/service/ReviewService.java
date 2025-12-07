package com.revtickets.review.service;

import com.revtickets.review.client.AuthClient;
import com.revtickets.review.client.dto.UserDTO;
import com.revtickets.review.dto.ReviewRequest;
import com.revtickets.review.model.Review;
import com.revtickets.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthClient authClient;

    public Review createReview(ReviewRequest request) {
        UserDTO user = authClient.getUserById(request.getUserId());
        
        Review review = new Review();
        review.setEventId(request.getEventId());
        review.setUserId(request.getUserId());
        review.setUserName(user != null ? user.getName() : "Unknown");
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByEventId(Long eventId) {
        return reviewRepository.findByEventId(eventId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
