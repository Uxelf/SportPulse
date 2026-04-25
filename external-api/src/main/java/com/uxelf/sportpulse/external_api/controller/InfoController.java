package com.uxelf.sportpulse.external_api.controller;

import com.uxelf.sportpulse.external_api.dto.RapidApiLeagueResponse;
import com.uxelf.sportpulse.external_api.service.LeagueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class InfoController {

    LeagueService leagueService;

    @GetMapping("/leagues")
    ResponseEntity<RapidApiLeagueResponse> getLeagues(){
        return ResponseEntity.ok(leagueService.getLeagues());
    }
}
