package com.revtickets.notification.service;

import com.revtickets.notification.dto.NotificationRequest;
import com.revtickets.notification.model.Notification;
import com.revtickets.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setCreatedAt(LocalDateTime.now());
        
        // In a real app, this would trigger EmailService
        String target = request.getEmail() != null ? request.getEmail() : "USER ID: " + request.getUserId();
        System.out.println("SENDING NOTIFICATION (" + request.getType() + "): " + request.getMessage() + " TO " + target);
        
        return notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }
}
