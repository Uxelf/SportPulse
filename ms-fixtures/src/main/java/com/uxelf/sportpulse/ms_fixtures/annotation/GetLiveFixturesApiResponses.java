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
                description = "Live fixtures retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                [
                  {
                    "id": 1035020,
                    "elapsed": 67,
                    "status": { "short": "2H", "long": "Second Half" },
                    "league": { "id": 39, "name": "Premier League" },
                    "homeTeam": { "id": 33, "name": "Manchester United", "goals": 2 },
                    "awayTeam": { "id": 40, "name": "Liverpool", "goals": 1 }
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
        )
})
public @interface GetLiveFixturesApiResponses {}
