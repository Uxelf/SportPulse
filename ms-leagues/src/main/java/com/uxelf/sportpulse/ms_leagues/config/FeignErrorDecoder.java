package com.uxelf.sportpulse.ms_leagues.config;

import com.uxelf.sportpulse.shared.exception.NotFoundException;
import com.uxelf.sportpulse.shared.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 401 -> new UnauthorizedException("TOKEN_INVALID", "Token inválido o expirado");
            case 404 -> new NotFoundException("NOT_FOUND", "Recurso no encontrado");
            default -> new RuntimeException("Error en la llamada a servicio externo: " + response.status());
        };
    }
}
