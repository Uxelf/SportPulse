package com.uxelf.sportpulse.ms_fixtures.annotation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
                description = "Fixture events retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                [
                  {
                    "elapsed": 23,
                    "type": "Goal",
                    "detail": "Normal Goal",
                    "team": { "id": 529, "name": "FC Barcelona" },
                    "player": { "id": 1100, "name": "Robert Lewandowski" },
                    "assist": { "id": 284, "name": "Pedri" }
                  },
                  {
                    "elapsed": 55,
                    "type": "Card",
                    "detail": "Yellow Card",
                    "team": { "id": 541, "name": "Real Madrid" },
                    "player": { "id": 276, "name": "Kylian Mbappé" },
                    "assist": null
                  }
                ]
            """)
                )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Invalid or missing token",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "valid": false,
                  "error": "TOKEN_EXPIRED",
                  "message": "Token has expired"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Fixture not found",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "FIXTURE_NOT_FOUND",
                  "message": "No existe un partido con el ID proporcionado",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface GetFixtureEventsApiResponses {}
