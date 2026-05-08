package com.uxelf.sportpulse.ms_notifications.dto.subscription;

import com.uxelf.sportpulse.ms_notifications.enums.NotificationChannel;
import com.uxelf.sportpulse.ms_notifications.enums.NotificationEvent;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionStatus;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SubscriptionResponse {
    private UUID subscriptionId;
    private UUID userId;
    private SubscriptionType type;
    private Integer teamId;
    private List<NotificationEvent> events;
    private NotificationChannel channel;
    private SubscriptionStatus status;
    private Instant createdAt;
}
