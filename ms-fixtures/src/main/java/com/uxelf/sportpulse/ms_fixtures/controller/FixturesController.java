package com.uxelf.sportpulse.ms_fixtures.controller;

import com.uxelf.sportpulse.ms_fixtures.dto.FixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.enums.FixtureStatus;
import com.uxelf.sportpulse.ms_fixtures.service.FixtureService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/fixtures")
@AllArgsConstructor
public class FixturesController {

    private final FixtureService fixtureService;
    @GetMapping("/")
    public ResponseEntity<List<FixtureResponse>> test(
            HttpServletRequest request,
            @RequestParam(required = false) Integer league,
            @RequestParam(required = false) Integer team,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) FixtureStatus status){

        String authHeader = request.getHeader("Authorization");

        return ResponseEntity.ok(fixtureService.getFixtures(
                authHeader,
                league,
                team,
                date,
                status));
    }
}
