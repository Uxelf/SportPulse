package com.uxelf.sportpulse.ms_auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Register user response")
public class RegisterResponse {

    @Schema(example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(example = "bob")
    private String username;

    @Schema(example = "bob@email.com")
    private String email;

    @Schema(example = "USER")
    private String role;

    @Schema(example = "2025-01-15T10:30:00Z")
    private Instant createdAt;
}
