package com.revtickets.auth.client;

import com.revtickets.auth.client.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClient {
    @PostMapping("/api/notifications/send")
    void sendNotification(@RequestBody NotificationRequest request);
}
