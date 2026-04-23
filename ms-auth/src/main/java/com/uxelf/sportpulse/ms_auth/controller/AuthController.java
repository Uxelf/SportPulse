package com.uxelf.sportpulse.ms_auth.controller;

import com.uxelf.sportpulse.ms_auth.annotation.LoginApiResponses;
import com.uxelf.sportpulse.ms_auth.annotation.RegisterApiResponses;
import com.uxelf.sportpulse.ms_auth.dto.LoginRequest;
import com.uxelf.sportpulse.ms_auth.dto.LoginResponse;
import com.uxelf.sportpulse.ms_auth.dto.RegisterRequest;
import com.uxelf.sportpulse.ms_auth.dto.RegisterResponse;
import com.uxelf.sportpulse.ms_auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Register user",
            description = "Creates a new user in the system"
    )
    @RegisterApiResponses
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "User authentication, returns the JWT")
    @LoginApiResponses
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
