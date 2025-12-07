package com.revtickets.review.client;

import com.revtickets.review.client.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {
    @GetMapping("/api/auth/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
