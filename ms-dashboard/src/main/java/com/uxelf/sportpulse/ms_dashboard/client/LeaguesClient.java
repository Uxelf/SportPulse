package com.uxelf.sportpulse.ms_dashboard.client;

import com.uxelf.sportpulse.ms_dashboard.dto.LeagueDetailResponse;
import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

// client/LeaguesClient.java
@FeignClient(name = "ms-leagues", url = "${leagues.service.url}", configuration = FeignErrorDecoder.class)
public interface LeaguesClient {

    @GetMapping("/api/leagues/{leagueId}")
    LeagueDetailResponse getLeagueById(
            @PathVariable Integer leagueId,
            @RequestHeader("Authorization") String authHeader
    );
}
