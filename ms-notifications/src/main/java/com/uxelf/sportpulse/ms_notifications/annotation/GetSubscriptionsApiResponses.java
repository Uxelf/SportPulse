package com.uxelf.sportpulse.ms_notifications.annotation;

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
                description = "Subscriptions retrieved successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                [
                  {
                    "subscriptionId": "770e8400-e29b-41d4-a716-446655440010",
                    "type": "TEAM",
                    "teamId": 529,
                    "events": ["MATCH_START", "GOAL", "MATCH_END"],
                    "channel": "WEBHOOK",
                    "webhookUrl": "https://miapp.com/webhook/futbol",
                    "status": "ACTIVE"
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
public @interface GetSubscriptionsApiResponses {}
