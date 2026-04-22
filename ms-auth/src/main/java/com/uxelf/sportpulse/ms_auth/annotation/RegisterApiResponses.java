package com.uxelf.sportpulse.ms_auth.annotation;

import com.uxelf.sportpulse.ms_auth.dto.RegisterResponse;
import com.uxelf.sportpulse.ms_auth.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "User successfully created",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = RegisterResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid data",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "username": "Username must not contain spaces",
                  "email": "Invalid email format",
                  "password": "Password must be at least 8 characters..."
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Email or username already in use",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "error": "USER_ALREADY_EXISTS",
                  "message": "Username already in use",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface RegisterApiResponses {}
