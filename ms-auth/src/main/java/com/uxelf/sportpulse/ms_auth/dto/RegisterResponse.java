package com.uxelf.sportpulse.ms_auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class RegisterResponse {

    private UUID id;
    private String username;
    private String email;
    private String role;
    private Instant createdAt;
}
