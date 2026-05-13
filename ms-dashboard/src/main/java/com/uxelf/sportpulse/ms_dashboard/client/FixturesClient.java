package com.uxelf.sportpulse.ms_dashboard.client;

import com.uxelf.sportpulse.ms_dashboard.dto.FixtureResponse;
import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// client/FixturesClient.java
@FeignClient(name = "ms-fixtures", url = "${fixtures.service.url}", configuration = FeignErrorDecoder.class)
public interface FixturesClient {

    @GetMapping("/api/fixtures/")
    List<FixtureResponse> getFixtures(
            @RequestParam Integer league,
            @RequestParam String date,
            @RequestHeader("Authorization") String authHeader
    );
}
