package com.uxelf.sportpulse.ms_dashboard.client;

import com.uxelf.sportpulse.ms_dashboard.dto.StandingsResponse;
import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// client/StandingsClient.java
@FeignClient(name = "ms-standings", url = "${standings.service.url}", configuration = FeignErrorDecoder.class)
public interface StandingsClient {

    @GetMapping("/api/standings")
    StandingsResponse getStandings(
            @RequestParam Integer league,
            @RequestParam Integer season,
            @RequestHeader("Authorization") String authHeader
    );
}
