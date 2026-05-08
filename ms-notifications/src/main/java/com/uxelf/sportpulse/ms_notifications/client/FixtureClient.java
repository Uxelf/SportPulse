package com.uxelf.sportpulse.ms_notifications.client;

import com.uxelf.sportpulse.ms_notifications.dto.fixtures.FixtureEventsResponse;
import com.uxelf.sportpulse.ms_notifications.dto.fixtures.LiveFixtureResponse;
import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "ms-fixtures", url = "${fixtures.service.url}", configuration = FeignErrorDecoder.class)
public interface FixtureClient {
    @GetMapping("/api/fixtures/{fixtureId}/events")
    List<FixtureEventsResponse> getFixtureEvents(
            @PathVariable Integer fixtureId,
            @RequestHeader("Authorization") String authHeader
    );

    @GetMapping("/api/fixtures/live")
    List<LiveFixtureResponse> getLiveFixtures(
            @RequestHeader("Authorization") String authHeader
    );
}
