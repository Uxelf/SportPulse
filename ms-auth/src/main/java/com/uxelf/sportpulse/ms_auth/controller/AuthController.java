package com.uxelf.sportpulse.ms_auth.controller;

import com.uxelf.sportpulse.ms_auth.annotation.LoginApiResponses;
import com.uxelf.sportpulse.ms_auth.annotation.RegisterApiResponses;
import com.uxelf.sportpulse.ms_auth.annotation.ValidateApiResponses;
import com.uxelf.sportpulse.ms_auth.dto.login.LoginRequest;
import com.uxelf.sportpulse.ms_auth.dto.login.LoginResponse;
import com.uxelf.sportpulse.ms_auth.dto.register.RegisterRequest;
import com.uxelf.sportpulse.ms_auth.dto.register.RegisterResponse;
import com.uxelf.sportpulse.ms_auth.dto.validate.ValidateResponse;
import com.uxelf.sportpulse.ms_auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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

    @PostMapping("/validate")
    @Operation(summary = "Validate token",
            description = "Validates a JWT and returns the user data. Internal use for other micro services.",
            security = @SecurityRequirement(name = "Bearer Auth"))
    @ValidateApiResponses
    public ResponseEntity<ValidateResponse> validate(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return ResponseEntity.ok(authService.validate(authHeader));
    }
}
