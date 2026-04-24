package com.uxelf.sportpulse.ms_leagues.controller;

import com.uxelf.sportpulse.ms_leagues.service.LeaguesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leagues")
@AllArgsConstructor
public class LeaguesController {

    private final LeaguesService leaguesService;

    @GetMapping("/")
    @Operation(summary = "Test", security = @SecurityRequirement(name = "Bearer Auth"))
    ResponseEntity<String> test(
            HttpServletRequest request,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer season)
    {

        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(leaguesService.test(authHeader));
    }
}
