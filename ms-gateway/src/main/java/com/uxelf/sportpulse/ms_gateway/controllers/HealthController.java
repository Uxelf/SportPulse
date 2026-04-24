package com.uxelf.sportpulse.ms_gateway.controllers;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HealthController {

    private final WebClient.Builder webClientBuilder;
    private final RouteLocator routeLocator;

    public HealthController(WebClient.Builder webClientBuilder, RouteLocator routeLocator){
        this.webClientBuilder = webClientBuilder;
        this.routeLocator = routeLocator;
    }

    @GetMapping("/health")
    public Mono<Map<String, Object>> status(){
        return routeLocator.getRoutes()
                .flatMap(route -> checkHealth(route.getId(), route.getUri().toString()))
                .collectMap(
                        entry -> "ms-" + entry.get("service"),
                        entry -> entry.get("status")
                )
                .map(services -> {
                    Map<String, Object> response = new LinkedHashMap<>();
                    response.put("gateway", "UP");
                    response.put("timestamp", Instant.now().toString());
                    response.put("services", services);
                    return response;
                });
    }

    private Mono<Map<String, Object>> checkHealth(String serviceId, String baseUrl) {
        return webClientBuilder
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri("/actuator/health")
                .retrieve()
                .bodyToMono(String.class)
                .map(r -> Map.<String, Object>of("service", serviceId, "status", "UP"))
                .onErrorResume(e -> Mono.just(
                        Map.<String, Object>of("service", serviceId, "status", "DOWN")));
    }
}
