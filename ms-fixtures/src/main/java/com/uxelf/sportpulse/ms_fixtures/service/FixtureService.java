package com.uxelf.sportpulse.ms_fixtures.service;

import com.uxelf.sportpulse.ms_fixtures.client.AuthClient;
import com.uxelf.sportpulse.ms_fixtures.client.ExternalApiClient;
import com.uxelf.sportpulse.ms_fixtures.dto.FixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.RapidApiFixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.enums.FixtureStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FixtureService {

    private final AuthClient authClient;
    private final ExternalApiClient externalApiClient;

    public List<FixtureResponse> getFixtures(
            String authHeader,
            Integer league,
            Integer team,
            String date,
            FixtureStatus status){

        authClient.validate(authHeader);

        return externalApiClient.getFixtures().getResponse().stream()
                .filter(wrapper -> league == null
                        || wrapper.getLeague().getId().equals(league))
                .filter(wrapper -> team == null
                        || wrapper.getTeams().getHome().getId().equals(team)
                        || wrapper.getTeams().getAway().getId().equals(team))
                .filter(wrapper -> date == null
                        || wrapper.getFixture().getDate().startsWith(date))
                .filter(wrapper -> status == null
                        || wrapper.getFixture().getStatus().getShortStatus()
                        .equals(status.toString()))
                .map(this::toFixtureResponse)
                .toList();
    }

    private FixtureResponse toFixtureResponse(RapidApiFixtureResponse.ApiFixtureWrapper wrapper) {
        FixtureResponse response = new FixtureResponse();

        response.setId(wrapper.getFixture().getId());
        response.setDate(Instant.parse(wrapper.getFixture().getDate()));

        FixtureResponse.StatusInfo status = new FixtureResponse.StatusInfo();
        status.setShortStatus(wrapper.getFixture().getStatus().getShortStatus());
        status.setLongStatus(wrapper.getFixture().getStatus().getLongStatus());
        response.setStatus(status);

        FixtureResponse.LeagueInfo league = new FixtureResponse.LeagueInfo();
        league.setId(wrapper.getLeague().getId());
        league.setName(wrapper.getLeague().getName());
        league.setRound(wrapper.getLeague().getRound());
        response.setLeague(league);

        FixtureResponse.TeamInfo home = new FixtureResponse.TeamInfo();
        home.setId(wrapper.getTeams().getHome().getId());
        home.setName(wrapper.getTeams().getHome().getName());
        home.setLogo(wrapper.getTeams().getHome().getLogo());
        home.setGoals(wrapper.getTeams().getHome().getGoals());
        response.setHomeTeam(home);

        FixtureResponse.TeamInfo away = new FixtureResponse.TeamInfo();
        away.setId(wrapper.getTeams().getAway().getId());
        away.setName(wrapper.getTeams().getAway().getName());
        away.setLogo(wrapper.getTeams().getAway().getLogo());
        away.setGoals(wrapper.getTeams().getAway().getGoals());
        response.setAwayTeam(away);

        FixtureResponse.VenueInfo venue = new FixtureResponse.VenueInfo();
        venue.setName(wrapper.getFixture().getVenue().getName());
        venue.setCity(wrapper.getFixture().getVenue().getCity());
        response.setVenue(venue);

        return response;
    }
}
