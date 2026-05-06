package com.uxelf.sportpulse.external_api.controller;

import com.uxelf.sportpulse.external_api.dto.RapidApiFixtureResponse;
import com.uxelf.sportpulse.external_api.dto.RapidApiLeagueResponse;
import com.uxelf.sportpulse.external_api.dto.RapidApiStandingsResponse;
import com.uxelf.sportpulse.external_api.dto.RapidApiTeamResponse;
import com.uxelf.sportpulse.external_api.service.FixtureService;
import com.uxelf.sportpulse.external_api.service.LeagueService;
import com.uxelf.sportpulse.external_api.service.StandingsService;
import com.uxelf.sportpulse.external_api.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class InfoController {

    LeagueService leagueService;
    TeamService teamService;
    FixtureService fixtureService;
    StandingsService standingsService;

    @GetMapping("/leagues")
    ResponseEntity<RapidApiLeagueResponse> getLeagues(){
        return ResponseEntity.ok(leagueService.getLeagues());
    }

    @GetMapping("/teams")
    ResponseEntity<RapidApiTeamResponse> getTeams(){
        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping("/fixtures")
    ResponseEntity<RapidApiFixtureResponse> getFixtures(){
        return ResponseEntity.ok(fixtureService.getFixtures());
    }

    @GetMapping("/standings")
    ResponseEntity<RapidApiStandingsResponse> getStandings(){
        return ResponseEntity.ok(standingsService.getStandings());
    }
}
