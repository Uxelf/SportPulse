package com.uxelf.sportpulse.ms_notifications.scheduler;

import com.uxelf.sportpulse.ms_notifications.client.FixtureClient;
import com.uxelf.sportpulse.ms_notifications.dto.fixtures.FixtureEventsResponse;
import com.uxelf.sportpulse.ms_notifications.dto.fixtures.LiveFixtureResponse;
import com.uxelf.sportpulse.ms_notifications.entity.Subscription;
import com.uxelf.sportpulse.ms_notifications.enums.NotificationEvent;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionStatus;
import com.uxelf.sportpulse.ms_notifications.repository.NotificationsRepository;
import com.uxelf.sportpulse.ms_notifications.service.WebhookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
@Slf4j
public class FixturePollingScheduler {

    private final FixtureClient fixtureClient;
    private final NotificationsRepository repository;
    private final WebhookService webhookService;
    private final FixtureStateStore stateStore;

    @Scheduled(fixedDelay = 2000)
    public void checkFixtures(){
        try{
            String internalAuth = "Bearer " + getInternalToken();

            List<LiveFixtureResponse> liveFixtures = fixtureClient.getLiveFixtures(internalAuth);

            for (LiveFixtureResponse fixture : liveFixtures){
                processFixture(fixture, internalAuth);
            }

            stateStore.getTrackedFixtures().forEach(fixtureId -> {
                boolean stillLive = liveFixtures.stream()
                        .anyMatch(f -> f.getId().equals(fixtureId));
                if (!stillLive) {
                    FixtureState state = stateStore.get(fixtureId);
                    notifyEvent(fixtureId, state.getHomeTeamId(), state.getAwayTeamId(), NotificationEvent.MATCH_END);
                    stateStore.remove(fixtureId);
                }
            });
        } catch (Exception e){
            log.error("Error during fixture polling: {}", e.getMessage());
        }
    }

    private void processFixture(LiveFixtureResponse fixture, String authHeader) {
        Integer fixtureId = fixture.getId();
        Integer homeTeamId = fixture.getHomeTeam().getId();
        Integer awayTeamId = fixture.getAwayTeam().getId();
        FixtureState previousState = stateStore.get(fixtureId);

        if (previousState == null) {
            notifyEvent(fixtureId, homeTeamId, awayTeamId, NotificationEvent.MATCH_START);
            stateStore.update(fixtureId, new FixtureState(fixtureId, 0, homeTeamId, awayTeamId));
            return;
        }

        List<FixtureEventsResponse> events = fixtureClient.getFixtureEvents(fixtureId, authHeader);
        int lastKnownElapsed = previousState.getLastElapsed();

        events.stream()
                .filter(e -> e.getElapsed() > lastKnownElapsed)
                .forEach(e -> {
                    NotificationEvent notificationEvent = mapToNotificationEvent(e.getType());
                    if (notificationEvent != null) {
                        notifyEvent(fixtureId, homeTeamId, awayTeamId, notificationEvent);
                    }
                });

        stateStore.update(fixtureId, new FixtureState(fixtureId, fixture.getElapsed(), homeTeamId, awayTeamId));
    }

    private void notifyEvent(Integer fixtureId, Integer homeTeamId, Integer awayTeamId, NotificationEvent event) {
        List<Subscription> fixtureSubscriptions = repository
                .findByFixtureIdAndStatusAndEventsContaining(
                        fixtureId, SubscriptionStatus.ACTIVE, event);

        List<Subscription> teamSubscriptions = repository
                .findByTeamIdInAndStatusAndEventsContaining(
                        List.of(homeTeamId, awayTeamId), SubscriptionStatus.ACTIVE, event);

        Stream.concat(fixtureSubscriptions.stream(), teamSubscriptions.stream())
                .forEach(sub -> webhookService.send(sub, event));
    }

    private NotificationEvent mapToNotificationEvent(String type) {
        return switch (type) {
            case "Goal" -> NotificationEvent.GOAL;
            case "Card" -> NotificationEvent.CARD;
            default -> null;
        };
    }

    private String getInternalToken() {
        return System.getenv("INTERNAL_JWT_TOKEN");
    }
}
