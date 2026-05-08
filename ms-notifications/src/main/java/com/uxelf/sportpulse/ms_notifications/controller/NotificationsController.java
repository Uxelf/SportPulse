package com.uxelf.sportpulse.ms_notifications.controller;

import com.uxelf.sportpulse.ms_notifications.annotation.CancelSubscriptionApiResponses;
import com.uxelf.sportpulse.ms_notifications.annotation.GetSubscriptionsApiResponses;
import com.uxelf.sportpulse.ms_notifications.annotation.SubscribeApiResponses;
import com.uxelf.sportpulse.ms_notifications.dto.subscription.CancelSubscriptionResponse;
import com.uxelf.sportpulse.ms_notifications.dto.subscription.SubscriptionListResponse;
import com.uxelf.sportpulse.ms_notifications.dto.subscription.SubscriptionRequest;
import com.uxelf.sportpulse.ms_notifications.dto.subscription.SubscriptionResponse;
import com.uxelf.sportpulse.ms_notifications.service.NotificationsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationsController {

    private final NotificationsService notificationsService;

    @PostMapping("/subscribe")
    @SubscribeApiResponses
    public ResponseEntity<SubscriptionResponse> subscribe(
            HttpServletRequest request,
            @RequestBody SubscriptionRequest subscriptionRequest
            ){
        String authHeader = request.getHeader("Authorization");

        return ResponseEntity.status(HttpStatus.CREATED).body(notificationsService.subscribe(authHeader, subscriptionRequest));
    }

    @GetMapping("/subscriptions")
    @GetSubscriptionsApiResponses
    public ResponseEntity<List<SubscriptionListResponse>> getSubscriptions(
            HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(notificationsService.getSubscriptions(authHeader));
    }

    @DeleteMapping("/subscribe/{subscriptionId}")
    @CancelSubscriptionApiResponses
    public ResponseEntity<CancelSubscriptionResponse> cancelSubscription(
            HttpServletRequest request,
            @PathVariable UUID subscriptionId) {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(notificationsService.cancelSubscription(authHeader, subscriptionId));
    }
}
