package com.uxelf.sportpulse.ms_notifications.service;

import com.uxelf.sportpulse.ms_notifications.dto.WebhookPayload;
import com.uxelf.sportpulse.ms_notifications.entity.Subscription;
import com.uxelf.sportpulse.ms_notifications.enums.NotificationEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class WebhookService {

    private final RestTemplate restTemplate;

    public void send(Subscription subscription, NotificationEvent event) {
        switch (subscription.getChannel()) {
            case WEBHOOK -> sendWebhook(subscription, event);
            case LOG -> logNotification(subscription, event);
        }
    }

    private void sendWebhook(Subscription subscription, NotificationEvent event) {
        if (subscription.getWebhookUrl() == null || subscription.getWebhookUrl().isBlank()) {
            log.warn("Subscription {} has WEBHOOK channel but no URL configured",
                    subscription.getId());
            return;
        }

        try {
            WebhookPayload payload = buildPayload(subscription, event);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<WebhookPayload> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<Void> response = restTemplate.postForEntity(
                    subscription.getWebhookUrl(), entity, Void.class);

            log.info("Webhook sent to {} for subscription {} — status: {}",
                    subscription.getWebhookUrl(),
                    subscription.getId(),
                    response.getStatusCode());

        } catch (Exception e) {
            log.error("Failed to send webhook to {} for subscription {}: {}",
                    subscription.getWebhookUrl(),
                    subscription.getId(),
                    e.getMessage());
        }
    }

    private void logNotification(Subscription subscription, NotificationEvent event) {
        log.info("Notification [LOG] — event: {}, subscriptionType: {}, teamId: {}, fixtureId: {}, timestamp: {}",
                event.name(),
                subscription.getType(),
                subscription.getTeamId(),
                subscription.getFixtureId(),
                Instant.now()
        );
    }

    private WebhookPayload buildPayload(Subscription subscription, NotificationEvent event) {
        WebhookPayload payload = new WebhookPayload();
        payload.setEvent(event.name());
        payload.setSubscriptionType(subscription.getType());
        payload.setTeamId(subscription.getTeamId());
        payload.setFixtureId(subscription.getFixtureId());
        payload.setTimestamp(Instant.now());
        return payload;
    }
}
