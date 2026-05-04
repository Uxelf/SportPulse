package com.uxelf.sportpulse.shared.component;

import com.uxelf.sportpulse.shared.exception.ExternalApiException;
import com.uxelf.sportpulse.shared.exception.RateLimitException;
import com.uxelf.sportpulse.shared.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 401 -> new UnauthorizedException("INVALID_TOKEN", "Missing or invalid token");
            case 429 -> new RateLimitException("RATE_LIMIT_EXCEEDED",
                    "Demasiadas peticiones. Inténtalo de nuevo más tarde.");
            case 503 -> new ExternalApiException("SERVICE_UNAVAILABLE",
                    "Servicio no disponible temporalmente.");
            default -> new ExternalApiException("EXTERNAL_API_ERROR",
                    "Error en la llamada a servicio externo: " + response.status());
        };
    }
}
