package com.uxelf.sportpulse.ms_notifications.controller;

import com.uxelf.sportpulse.ms_notifications.dto.SubscriptionRequest;
import com.uxelf.sportpulse.ms_notifications.dto.SubscriptionResponse;
import com.uxelf.sportpulse.ms_notifications.service.NotificationsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationsController {

    private final NotificationsService notificationsService;

    @PostMapping("/subscribe")
    public ResponseEntity<SubscriptionResponse> subscribe(
            HttpServletRequest request,
            @RequestBody SubscriptionRequest subscriptionRequest
            ){
        String authHeader = request.getHeader("Authorization");

        return ResponseEntity.status(HttpStatus.CREATED).body(notificationsService.subscribe(authHeader, subscriptionRequest));
    }
}
