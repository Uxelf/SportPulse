package com.uxelf.sportpulse.ms_dashboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/")
    public ResponseEntity<String> getDashboard(
            HttpServletRequest request,
            @RequestParam Integer league
    ){
        return ResponseEntity.ok("");
    }
}
