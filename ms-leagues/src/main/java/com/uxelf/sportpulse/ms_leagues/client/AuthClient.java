package com.uxelf.sportpulse.ms_leagues.client;

import com.uxelf.sportpulse.ms_leagues.dto.validate.ValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ms-auth", url = "${auth.service.url}")
public interface AuthClient {
    @PostMapping("/api/auth/validate")
    ValidateResponse validate(@RequestHeader("Authorization") String authHeader);
}
