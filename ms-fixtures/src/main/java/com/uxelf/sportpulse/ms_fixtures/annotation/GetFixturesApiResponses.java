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
                description = "Fixtures list retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                [
                  {
                    "id": 1035065,
                    "date": "2025-01-20T21:00:00+01:00",
                    "status": { "short": "NS", "long": "Not Started" },
                    "league": { "id": 140, "name": "La Liga", "round": "Jornada 21" },
                    "homeTeam": {
                      "id": 529,
                      "name": "FC Barcelona",
                      "logo": "https://media.api-sports.io/football/teams/529.png",
                      "goals": null
                    },
                    "awayTeam": {
                      "id": 541,
                      "name": "Real Madrid",
                      "logo": "https://media.api-sports.io/football/teams/541.png",
                      "goals": null
                    },
                    "venue": { "name": "Camp Nou", "city": "Barcelona" }
                  }
                ]
            """)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid date format",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "INVALID_DATE_FORMAT",
                  "message": "Invalid date format. Expected: YYYY-MM-DD",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
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
public @interface GetFixturesApiResponses {}
