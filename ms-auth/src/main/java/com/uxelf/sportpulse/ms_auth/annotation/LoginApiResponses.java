package com.uxelf.sportpulse.ms_auth.annotation;

import com.uxelf.sportpulse.ms_auth.dto.LoginResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.ErrorResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Login success",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = LoginResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                  "tokenType": "Bearer",
                  "expiresIn": 3600,
                  "userId": "550e8400-e29b-41d4-a716-446655440000"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid data",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "email": "Invalid email format",
                  "password": "Password is required"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Wrong credentials",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "error": "INVALID_CREDENTIALS",
                  "message": "Wrong email or password",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface LoginApiResponses {}
