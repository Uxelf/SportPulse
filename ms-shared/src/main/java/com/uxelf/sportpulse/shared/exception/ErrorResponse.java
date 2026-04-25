package com.uxelf.sportpulse.shared.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Schema(description = "Error response")
public class ErrorResponse {

    @Schema(example = "ERROR_CODE")
    private String error;

    @Schema(example = "Error description")
    private String message;

    @Schema(example = "2025-01-15T10:30:00Z")
    private Instant timestamp;
}

