package com.uxelf.sportpulse.ms_dashboard.client;

import com.uxelf.sportpulse.shared.component.FeignErrorDecoder;
import com.uxelf.sportpulse.shared.dto.validate.ValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ms-auth", url = "${auth.service.url}", configuration = FeignErrorDecoder.class)
public interface AuthClient {
    @PostMapping("/api/auth/validate")
    ValidateResponse validate(@RequestHeader("Authorization") String authHeader);
}
