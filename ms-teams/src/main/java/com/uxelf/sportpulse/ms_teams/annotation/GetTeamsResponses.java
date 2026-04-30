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
                description = "Teams list retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                [
                  {
                    "id": 529,
                    "name": "FC Barcelona",
                    "country": "Spain",
                    "logo": "https://media.api-sports.io/football/teams/529.png",
                    "founded": 1899,
                    "stadium": {
                      "name": "Camp Nou",
                      "city": "Barcelona",
                      "capacity": 99354
                    }
                  },
                  {
                    "id": 541,
                    "name": "Real Madrid",
                    "country": "Spain",
                    "logo": "https://media.api-sports.io/football/teams/541.png",
                    "founded": 1902,
                    "stadium": {
                      "name": "Estadio Santiago Bernabéu",
                      "city": "Madrid",
                      "capacity": 81044
                    }
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
public @interface GetTeamsResponses {
}
