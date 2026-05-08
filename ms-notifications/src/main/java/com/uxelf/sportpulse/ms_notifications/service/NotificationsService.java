package com.uxelf.sportpulse.ms_notifications.service;

import com.uxelf.sportpulse.ms_notifications.client.AuthClient;
import com.uxelf.sportpulse.ms_notifications.dto.SubscriptionRequest;
import com.uxelf.sportpulse.ms_notifications.dto.SubscriptionResponse;
import com.uxelf.sportpulse.ms_notifications.entity.Subscription;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionStatus;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionType;
import com.uxelf.sportpulse.ms_notifications.repository.NotificationsRepository;
import com.uxelf.sportpulse.shared.dto.validate.ValidateResponse;
import com.uxelf.sportpulse.shared.exception.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class NotificationsService {

    private final AuthClient authClient;
    private final NotificationsRepository repository;

    public SubscriptionResponse subscribe(String authHeader, SubscriptionRequest subscriptionRequest){

        ValidateResponse validateResponse = authClient.validate(authHeader);

        checkDuplicateSubscription(subscriptionRequest, validateResponse.getUserId());

        Subscription subscription = createSubscription(subscriptionRequest, validateResponse);
        Subscription saved = repository.save(subscription);

        SubscriptionResponse response = new SubscriptionResponse();
        response.setSubscriptionId(saved.getId());
        response.setUserId(saved.getUserId());
        response.setType(saved.getType());
        response.setTeamId(saved.getTeamId());
        response.setEvents(saved.getEvents());
        response.setChannel(saved.getChannel());
        response.setStatus(saved.getStatus());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    private void checkDuplicateSubscription(SubscriptionRequest request, UUID userId) {
        boolean exists = switch (request.getType()) {
            case TEAM -> repository.existsByUserIdAndTeamIdAndChannelAndStatus(
                    userId, request.getTeamId(), request.getChannel(), SubscriptionStatus.ACTIVE);
            case FIXTURE -> repository.existsByUserIdAndFixtureIdAndChannelAndStatus(
                    userId, request.getFixtureId(), request.getChannel(), SubscriptionStatus.ACTIVE);
        };

        if (exists) {
            throw new ConflictException("SUBSCRIPTION_ALREADY_EXISTS",
                    "There is already an active subscription for this team or fixture in this channel");
        }
    }

    private static Subscription createSubscription(SubscriptionRequest subscriptionRequest, ValidateResponse validateResponse) {
        Subscription subscription = new Subscription();
        subscription.setUserId(validateResponse.getUserId());
        subscription.setType(subscriptionRequest.getType());
        if (subscriptionRequest.getType() == SubscriptionType.TEAM)
            subscription.setTeamId(subscriptionRequest.getTeamId());
        else if (subscriptionRequest.getType() == SubscriptionType.FIXTURE)
            subscription.setFixtureId(subscriptionRequest.getFixtureId());
        subscription.setEvents(subscriptionRequest.getEvents());
        subscription.setChannel(subscriptionRequest.getChannel());
        subscription.setWebhookUrl(subscriptionRequest.getWebhookUrl());
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        return subscription;
    }
}
