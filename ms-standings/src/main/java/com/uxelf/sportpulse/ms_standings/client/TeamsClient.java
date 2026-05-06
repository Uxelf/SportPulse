package com.uxelf.sportpulse.ms_standings.client;


import com.uxelf.sportpulse.ms_standings.dto.TeamDetailResponse;
import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ms-teams", url = "${teams.service.url}", configuration = FeignErrorDecoder.class)
public interface TeamsClient {

    @GetMapping("/api/teams/{teamId}")
    TeamDetailResponse getTeamById(
            @PathVariable Integer teamId,
            @RequestHeader("Authorization") String authHeader
    );
}
