package com.uxelf.sportpulse.ms_fixtures.controller;

import com.uxelf.sportpulse.ms_fixtures.enums.FixtureStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fixtures")
public class FixturesController {

    @GetMapping("/")
    public ResponseEntity<String> test(
            @RequestParam(required = false) Integer league,
            @RequestParam(required = false) Integer team,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) FixtureStatus status){


        return ResponseEntity.ok("Test");
    }
}
