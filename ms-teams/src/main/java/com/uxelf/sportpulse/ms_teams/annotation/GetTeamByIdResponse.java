package com.uxelf.sportpulse.ms_teams.annotation;

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
                description = "Team detail retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "id": 529,
                  "name": "FC Barcelona",
                  "country": "Spain",
                  "logo": "https://media.api-sports.io/football/teams/529.png",
                  "founded": 1899,
                  "national": false,
                  "stadium": {
                    "name": "Camp Nou",
                    "address": "C/ d'Arístides Maillol, s/n",
                    "city": "Barcelona",
                    "capacity": 99354,
                    "surface": "grass"
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
                  "message": "Token has expired"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Team not found",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "TEAM_NOT_FOUND",
                  "message": "No existe un equipo con el ID proporcionado",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface GetTeamByIdResponse {
}
