package com.uxelf.sportpulse.ms_leagues.annotation;

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
                description = "League successfully obtained",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = SpecificLeagueResponses.class),
                        examples = @ExampleObject(value = """
                {
                  "id": 140,
                  "name": "La Liga",
                  "type": "League",
                  "country": "Spain",
                  "logo": "https://media.api-sports.io/football/leagues/140.png",
                  "seasons": [2021, 2022, 2023, 2024],
                  "currentSeason": {
                    "year": 2024,
                    "startDate": "2024-08-17",
                    "endDate": "2025-05-25",
                    "current": true
                  }
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
                  "message": "El token ha expirado"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "League not found",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "LEAGUE_NOT_FOUND",
                  "message": "No existe una liga con el ID proporcionado",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface SpecificLeagueResponses {
}
