package com.uxelf.sportpulse.ms_teams.controller;

import com.uxelf.sportpulse.ms_teams.annotation.GetTeamByIdResponse;
import com.uxelf.sportpulse.ms_teams.annotation.GetTeamsResponses;
import com.uxelf.sportpulse.ms_teams.dto.team.SpecificTeamResponse;
import com.uxelf.sportpulse.ms_teams.dto.team.TeamResponse;
import com.uxelf.sportpulse.ms_teams.service.TeamsService;
import com.uxelf.sportpulse.shared.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamsController {

    TeamsService teamsService;

    @GetMapping("/")
    @Operation(
            summary = "List of teams",
            description = "List of teams filtered by league and season.",
            security = @SecurityRequirement(name = "Bearer Auth"))
    @GetTeamsResponses
    public ResponseEntity<List<TeamResponse>> getTeams(
            HttpServletRequest request,
            @RequestParam(required = true) Integer league,
            @RequestParam(required = true) Integer season){

        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(teamsService.getTeams(authHeader, league, season));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get team by ID",
            description = "Get a team details by ID.",
            security = @SecurityRequirement(name = "Bearer Auth"))
    @GetTeamByIdResponse
    public ResponseEntity<SpecificTeamResponse> getTeamById(
            HttpServletRequest request,
            @PathVariable Integer id
    ){
        String authHeader = request.getHeader("Authorization");
        SpecificTeamResponse response = teamsService.getTeamById(authHeader, id);
        if (response == null)
            throw new NotFoundException("TEAM_NOT_FOUND", "No existe un equipo con el ID proporcionado");
        return ResponseEntity.ok(response);
    }
}
