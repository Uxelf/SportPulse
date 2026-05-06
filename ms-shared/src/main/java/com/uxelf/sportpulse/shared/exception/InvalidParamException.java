package com.uxelf.sportpulse.shared.exception;

import lombok.Getter;

@Getter
public class InvalidParamException extends RuntimeException {
    private final String errorCode;

    public InvalidParamException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
