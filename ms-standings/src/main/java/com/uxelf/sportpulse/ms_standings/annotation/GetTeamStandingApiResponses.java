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
                description = "Team standing retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "team": { "id": 529, "name": "FC Barcelona" },
                  "league": { "id": 140, "name": "La Liga" },
                  "season": 2024,
                  "rank": 1,
                  "points": 48,
                  "played": 20,
                  "form": "WWWDW",
                  "description": "Promotion - Champions League (Group Stage)"
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
                description = "Team or league not found",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "TEAM_NOT_FOUND",
                  "message": "No existe un equipo con el ID proporcionado en esa liga y temporada",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface GetTeamStandingApiResponses {}
