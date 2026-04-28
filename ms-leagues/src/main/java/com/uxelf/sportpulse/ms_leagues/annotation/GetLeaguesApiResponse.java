package com.uxelf.sportpulse.ms_leagues.annotation;

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
                description = "Leagues list successfully obtained",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                [
                  {
                    "id": 140,
                    "name": "La Liga",
                    "type": "League",
                    "country": "Spain",
                    "logo": "https://media.api-sports.io/football/leagues/140.png",
                    "currentSeason": 2024,
                    "startDate": "2024-08-17",
                    "endDate": "2025-05-25"
                  },
                  {
                    "id": 39,
                    "name": "Premier League",
                    "type": "League",
                    "country": "England",
                    "logo": "https://media.api-sports.io/football/leagues/39.png",
                    "currentSeason": 2024,
                    "startDate": "2024-08-16",
                    "endDate": "2025-05-25"
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
                  "message": "El token ha expirado"
                }
            """)
                )
        )
})
public @interface GetLeaguesApiResponse {
}
