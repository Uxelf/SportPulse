package com.uxelf.sportpulse.shared.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final String errorCode;

    public UnauthorizedException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
