package com.uxelf.sportpulse.ms_standings.client;

import com.uxelf.sportpulse.ms_standings.dto.RapidApiStandingsResponse;
import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient ( name = "external-api", url = "${external-api.url}", configuration = FeignErrorDecoder.class)
public interface ExternalApiClient {
    @GetMapping("/standings")
    RapidApiStandingsResponse getStandings();
}

