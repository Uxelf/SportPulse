package com.uxelf.sportpulse.ms_standings.controller;

import com.uxelf.sportpulse.ms_standings.dto.StandingsResponse;
import com.uxelf.sportpulse.ms_standings.service.StandingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// controller/StandingsController.java
@RestController
@RequestMapping("/api/standings")
@AllArgsConstructor
public class StandingsController {

    private final StandingsService standingsService;

    @GetMapping
    @Operation(
            summary = "Get standings",
            description = "Returns full standings for a league and season.",
            security = @SecurityRequirement(name = "Bearer Auth"))
    public ResponseEntity<StandingsResponse> getStandings(
            HttpServletRequest request,
            @RequestParam Integer league,
            @RequestParam Integer season) {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(standingsService.getStandings(authHeader, league, season));
    }
}
