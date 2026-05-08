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
                description = "Subscription cancelled successfully",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "subscriptionId": "770e8400-e29b-41d4-a716-446655440010",
                  "status": "CANCELLED",
                  "cancelledAt": "2025-01-20T10:30:00Z"
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
                responseCode = "403",
                description = "You do not have permission to cancel this subscription",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "FORBIDDEN",
                  "message": "You do not have permission to cancel this subscription",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Subscription not found",
                content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                {
                  "error": "SUBSCRIPTION_NOT_FOUND",
                  "message": "No existe una suscripción con el ID proporcionado",
                  "timestamp": "2025-01-15T10:30:00Z"
                }
            """)
                )
        )
})
public @interface CancelSubscriptionApiResponses {}
