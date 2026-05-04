package com.uxelf.sportpulse.ms_teams.dto.validate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ValidateResponse {
    private boolean valid;
    private UUID userId;
    private String username;
    private String role;
}