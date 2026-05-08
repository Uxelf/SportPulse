package com.uxelf.sportpulse.ms_notifications.dto;

import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class WebhookPayload {
    private String event;
    private SubscriptionType subscriptionType;
    private Integer teamId;
    private Integer fixtureId;
    private Instant timestamp;
}
