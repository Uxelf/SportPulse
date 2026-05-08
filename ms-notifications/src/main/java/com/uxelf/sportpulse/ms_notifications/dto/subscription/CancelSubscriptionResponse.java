package com.uxelf.sportpulse.ms_notifications.dto.subscription;

import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
public class CancelSubscriptionResponse {
    private UUID subscriptionId;
    private SubscriptionStatus status;
    private Instant cancelledAt;
}
