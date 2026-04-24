package com.uxelf.sportpulse.ms_leagues.service;

import com.uxelf.sportpulse.ms_leagues.client.AuthClient;
import com.uxelf.sportpulse.ms_leagues.dto.validate.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaguesService {

    private final AuthClient authClient;

    public String test(String authHeader){
        ValidateResponse auth = authClient.validate(authHeader);

        return "ok";
    }
}
