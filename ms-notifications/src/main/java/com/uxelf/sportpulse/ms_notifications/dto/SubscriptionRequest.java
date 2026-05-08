package com.uxelf.sportpulse.ms_notifications.dto;

import com.uxelf.sportpulse.ms_notifications.enums.NotificationChannel;
import com.uxelf.sportpulse.ms_notifications.enums.NotificationEvent;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionType;
import lombok.Getter;

import java.util.List;

@Getter
public class SubscriptionRequest {
    private SubscriptionType type;
    private Integer teamId;
    private Integer fixtureId;
    private List<NotificationEvent> events;
    private NotificationChannel channel;
    private String webhookUrl;
}
