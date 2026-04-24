package com.uxelf.sportpulse.ms_auth.annotation;

import com.uxelf.sportpulse.ms_auth.dto.validate.ValidateResponse;
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
                responseCode = "200",
                description = "Valid token",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ValidateResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "valid": true,
                  "userId": "550e8400-e29b-41d4-a716-446655440000",
                  "username": "Bob",
                  "role": "USER"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Invalid or expired token",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "valid": false,
                  "error": "TOKEN_EXPIRED",
                  "message": "The token expired"
                }
            """)
                )
        )
})
public @interface ValidateApiResponses {}
