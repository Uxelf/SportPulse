package com.uxelf.sportpulse.ms_auth.controller;

import com.uxelf.sportpulse.ms_auth.dto.RegisterRequest;
import com.uxelf.sportpulse.ms_auth.dto.RegisterResponse;
import com.uxelf.sportpulse.ms_auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hola");
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
