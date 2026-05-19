package com.uxelf.sportpulse.ms_dashboard.controller;

import com.uxelf.sportpulse.ms_dashboard.annotation.GetDashboardApiResponses;
import com.uxelf.sportpulse.ms_dashboard.dto.DashboardResponse;
import com.uxelf.sportpulse.ms_dashboard.service.DashboardService;
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
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @Operation(summary = "Get dashboard", description = "Returns a summary for a league including today's matches, top 3 standings and top 3 scorers.", security = @SecurityRequirement(name = "Bearer Auth"))
    @GetDashboardApiResponses
    public ResponseEntity<DashboardResponse> getDashboard(
            HttpServletRequest request,
            @RequestParam Integer league,
            @RequestParam Integer season) {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(dashboardService.getDashboard(authHeader, league, season));
    }
}
