package com.uxelf.sportpulse.ms_notifications.repository;


import com.uxelf.sportpulse.ms_notifications.entity.Subscription;
import com.uxelf.sportpulse.ms_notifications.enums.NotificationChannel;
import com.uxelf.sportpulse.ms_notifications.enums.NotificationEvent;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionStatus;
import com.uxelf.sportpulse.ms_notifications.enums.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationsRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findById(UUID id);

    boolean existsByUserIdAndTeamIdAndChannelAndStatus(
            UUID userId, Integer teamId, NotificationChannel channel, SubscriptionStatus status);

    boolean existsByUserIdAndFixtureIdAndChannelAndStatus(
            UUID userId, Integer fixtureId, NotificationChannel channel, SubscriptionStatus status);

    List<Subscription> findByFixtureIdAndStatusAndEventsContaining(
            Integer fixtureId, SubscriptionStatus status, NotificationEvent event);

    List<Subscription> findByTeamIdInAndStatusAndEventsContaining(
            List<Integer> teamIds, SubscriptionStatus status, NotificationEvent event);

    List<Subscription> findByUserIdAndStatus(UUID userId, SubscriptionStatus status);
}
