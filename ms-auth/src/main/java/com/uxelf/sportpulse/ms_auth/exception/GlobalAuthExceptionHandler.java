package com.uxelf.sportpulse.ms_auth.exception;

import com.uxelf.sportpulse.ms_auth.dto.validate.ValidateErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAuthExceptionHandler {

    @ExceptionHandler(UnauthorizedAuthException.class)
    public ResponseEntity<ValidateErrorResponse> handleUnauthorized(UnauthorizedAuthException ex) {
        ValidateErrorResponse body = new ValidateErrorResponse(false, ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
