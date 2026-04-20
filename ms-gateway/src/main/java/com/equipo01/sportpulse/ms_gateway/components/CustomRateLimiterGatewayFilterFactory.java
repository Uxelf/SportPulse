package com.equipo01.sportpulse.ms_gateway.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.distributed.AsyncBucketProxy;
import io.github.bucket4j.distributed.proxy.AsyncProxyManager;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomRateLimiterGatewayFilterFactory
        extends AbstractGatewayFilterFactory<CustomRateLimiterGatewayFilterFactory.Config> {

    private final AsyncProxyManager<String> caffeineProxyManager;
    private final ObjectMapper objectMapper;

    public CustomRateLimiterGatewayFilterFactory(
            AsyncProxyManager<String> caffeineProxyManager,
            ObjectMapper objectMapper) {
        super(Config.class);
        this.caffeineProxyManager = caffeineProxyManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String ip = exchange.getRequest()
                    .getRemoteAddress()
                    .getAddress()
                    .getHostAddress();

            BucketConfiguration bucketConfig = BucketConfiguration.builder()
                    .addLimit(Bandwidth.builder()
                            .capacity(config.getCapacity())
                            .refillIntervally(
                                    config.getRefillTokens(),
                                    Duration.parse(config.getRefillPeriod()))
                            .build())
                    .build();

            AsyncBucketProxy bucket = caffeineProxyManager.builder()
                    .build(ip, () -> CompletableFuture.completedFuture(bucketConfig));

            return Mono.fromFuture(bucket.tryConsumeAndReturnRemaining(1))
                    .flatMap(probe -> {
                        if (probe.isConsumed()) {
                            exchange.getResponse().getHeaders()
                                    .set("X-RateLimit-Remaining",
                                            String.valueOf(probe.getRemainingTokens()));
                            return chain.filter(exchange);
                        }
                        return writeRateLimitError(exchange, probe);
                    });
        };
    }

    private Mono<Void> writeRateLimitError(ServerWebExchange exchange,
                                           ConsumptionProbe probe) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        long waitSeconds = probe.getNanosToWaitForRefill() / 1_000_000_000;
        response.getHeaders().set("Retry-After", String.valueOf(waitSeconds));

        Map<String, Object> body = Map.of(
                "error", "RATE_LIMIT_EXCEEDED",
                "message", "Demasiadas peticiones. Límite: 60 req/min",
                "retryAfter", 30,
                "timestamp", Instant.now().toString()
        );

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(body);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }

    @Data
    public static class Config {
        private long capacity = 2;
        private long refillTokens = 2;
        private String refillPeriod = "PT60S";
    }
}