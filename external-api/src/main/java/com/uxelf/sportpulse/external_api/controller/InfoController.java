package com.uxelf.sportpulse.external_api.controller;

import com.uxelf.sportpulse.external_api.dto.*;
import com.uxelf.sportpulse.external_api.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class InfoController {

    LeagueService leagueService;
    TeamService teamService;
    FixtureService fixtureService;
    StandingsService standingsService;
    TopScorersService topScorersService;

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

    @GetMapping("/players/topscorers")
    public ResponseEntity<RapidApiTopScorersResponse> getTopScorers() {
        return ResponseEntity.ok(topScorersService.getTopScorers());
    }
}
