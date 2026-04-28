package com.uxelf.sportpulse.shared.exception;

import lombok.Getter;

@Getter
public class RateLimitException extends RuntimeException {
    private final String errorCode;

    public RateLimitException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
