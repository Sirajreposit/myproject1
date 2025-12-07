package com.revtickets.notification.repository;

import com.revtickets.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserIdAndIsReadFalse(Long userId);
}
