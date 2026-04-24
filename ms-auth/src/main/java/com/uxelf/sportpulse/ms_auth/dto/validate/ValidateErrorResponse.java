package com.uxelf.sportpulse.ms_auth.dto.validate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateErrorResponse {
    private boolean valid;
    private String error;
    private String message;
}
