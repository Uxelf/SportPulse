package com.uxelf.sportpulse.ms_auth.exception;

import lombok.Getter;

@Getter
public class UnauthorizedAuthException extends RuntimeException {
    private final String errorCode;

    public UnauthorizedAuthException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
