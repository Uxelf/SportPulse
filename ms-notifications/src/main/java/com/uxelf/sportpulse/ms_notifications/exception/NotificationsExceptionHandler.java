package com.uxelf.sportpulse.ms_notifications.exception;

import com.uxelf.sportpulse.shared.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class NotificationsExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        ErrorResponse body = new ErrorResponse(ex.getErrorCode(), ex.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }
}
