package com.uxelf.sportpulse.ms_leagues.controller;

import com.uxelf.sportpulse.ms_leagues.annotation.GetLeaguesApiResponse;
import com.uxelf.sportpulse.ms_leagues.annotation.SpecificLeagueResponses;
import com.uxelf.sportpulse.ms_leagues.dto.league.LeagueResponse;
import com.uxelf.sportpulse.ms_leagues.dto.league.SpecificLeagueResponse;
import com.uxelf.sportpulse.ms_leagues.service.LeaguesService;
import com.uxelf.sportpulse.shared.exception.ConflictException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
@AllArgsConstructor
public class LeaguesController {

    private final LeaguesService leaguesService;

    @GetMapping("/")
    @Operation(summary = "Get leagues, can be filtered by country and/or season", security = @SecurityRequirement(name = "Bearer Auth"))
    @GetLeaguesApiResponse
    ResponseEntity<List<LeagueResponse>> getLeagues(
            HttpServletRequest request,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer season)
    {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(leaguesService.getLeagues(authHeader, country, season));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific league info", security = @SecurityRequirement(name = "Bearer Auth"))
    @SpecificLeagueResponses
    ResponseEntity<SpecificLeagueResponse> getLeagueById(
            HttpServletRequest request,
            @PathVariable int id)
    {
        String authHeader = request.getHeader("Authorization");
        SpecificLeagueResponse response = leaguesService.getLeagueById(authHeader, id);
        if (response == null){
            throw new ConflictException("LEAGUE_NOT_FOUND", "No existe una liga con el ID proporcionado");
        }
        return ResponseEntity.ok(response);
    }
}
