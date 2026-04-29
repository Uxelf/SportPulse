package com.uxelf.sportpulse.ms_teams.controller;

import com.uxelf.sportpulse.ms_teams.dto.team.TeamResponse;
import com.uxelf.sportpulse.ms_teams.service.TeamsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamsController {

    TeamsService teamsService;

    @GetMapping("/")
    public ResponseEntity<List<TeamResponse>> getTeams(
            HttpServletRequest request,
            @RequestParam(required = true) Integer league,
            @RequestParam(required = true) Integer season){

        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(teamsService.getTeams(authHeader, league, season));
    }
}
