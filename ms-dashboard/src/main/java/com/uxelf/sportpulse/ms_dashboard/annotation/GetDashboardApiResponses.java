package com.uxelf.sportpulse.ms_dashboard.annotation;


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
                description = "Dashboard retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "league": {
                    "id": 140,
                    "name": "La Liga",
                    "country": "Spain"
                  },
                  "today": "2025-01-20",
                  "matchesToday": [
                    {
                      "id": 1035065,
                      "date": "2025-01-20T21:00:00+01:00",
                      "homeTeam": { "name": "FC Barcelona", "logo": "https://media.api-sports.io/football/teams/529.png" },
                      "awayTeam": { "name": "Real Madrid", "logo": "https://media.api-sports.io/football/teams/541.png" },
                      "status": "NS"
                    }
                  ],
                  "standingsPreview": [
                    { "rank": 1, "team": "FC Barcelona", "points": 48, "played": 20 },
                    { "rank": 2, "team": "Real Madrid", "points": 44, "played": 20 },
                    { "rank": 3, "team": "Atlético Madrid", "points": 38, "played": 20 }
                  ],
                  "topScorers": [
                    { "rank": 1, "name": "Harry Kane", "team": "Bayern Munich", "goals": 20 },
                    { "rank": 2, "name": "Kylian Mbappé", "team": "Real Madrid", "goals": 18 },
                    { "rank": 3, "name": "Lautaro Martínez", "team": "Inter Milan", "goals": 16 }
                  ],
                  "lastUpdated": "2025-01-20T09:00:00Z"
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
                responseCode = "502",
                description = "Error retrieving data from an internal service",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "EXTERNAL_API_ERROR",
                  "message": "Error en la llamada a servicio externo: 404",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface GetDashboardApiResponses {}
