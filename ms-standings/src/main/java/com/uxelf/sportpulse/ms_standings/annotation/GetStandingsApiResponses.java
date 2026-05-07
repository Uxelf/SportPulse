package com.uxelf.sportpulse.ms_standings.annotation;


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
                description = "Standings retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "league": {
                    "id": 140,
                    "name": "La Liga",
                    "country": "Spain",
                    "season": 2024
                  },
                  "standings": [
                    {
                      "rank": 1,
                      "team": {
                        "id": 529,
                        "name": "FC Barcelona",
                        "logo": "https://media.api-sports.io/football/teams/529.png"
                      },
                      "points": 48,
                      "played": 20,
                      "won": 15,
                      "drawn": 3,
                      "lost": 2,
                      "goalsFor": 52,
                      "goalsAgainst": 18,
                      "goalDifference": 34,
                      "form": "WWWDW",
                      "description": "Promotion - Champions League (Group Stage)"
                    },
                    {
                      "rank": 2,
                      "team": {
                        "id": 541,
                        "name": "Real Madrid",
                        "logo": "https://media.api-sports.io/football/teams/541.png"
                      },
                      "points": 44,
                      "played": 20,
                      "won": 13,
                      "drawn": 5,
                      "lost": 2,
                      "goalsFor": 44,
                      "goalsAgainst": 20,
                      "goalDifference": 24,
                      "form": "WDWWW",
                      "description": "Promotion - Champions League (Group Stage)"
                    }
                  ]
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
public @interface GetStandingsApiResponses {}