package com.uxelf.sportpulse.ms_standings.controller;

import com.uxelf.sportpulse.ms_standings.dto.StandingsResponse;
import com.uxelf.sportpulse.ms_standings.dto.TeamStandingResponse;
import com.uxelf.sportpulse.ms_standings.service.StandingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/team/{teamId}")
    @Operation(summary = "Get team standing", description = "Returns the standing position of a specific team in a league and season.", security = @SecurityRequirement(name = "Bearer Auth"))
    public ResponseEntity<TeamStandingResponse> getTeamStanding(
            HttpServletRequest request,
            @PathVariable Integer teamId,
            @RequestParam Integer league,
            @RequestParam Integer season) {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(standingsService.getTeamStanding(authHeader, teamId, league, season));
    }
}
