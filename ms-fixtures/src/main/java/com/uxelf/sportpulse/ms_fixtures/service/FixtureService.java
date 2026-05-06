package com.uxelf.sportpulse.ms_fixtures.service;

import com.uxelf.sportpulse.ms_fixtures.client.AuthClient;
import com.uxelf.sportpulse.ms_fixtures.client.ExternalApiClient;
import com.uxelf.sportpulse.ms_fixtures.dto.FixtureEventsResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.FixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.LiveFixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.RapidApiFixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.builder.RapidApiToFixture;
import com.uxelf.sportpulse.ms_fixtures.enums.FixtureStatus;
import com.uxelf.sportpulse.shared.exception.InvalidParamException;
import com.uxelf.sportpulse.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@AllArgsConstructor
public class FixtureService {

    private final AuthClient authClient;
    private final ExternalApiClient externalApiClient;
    private final RapidApiToFixture rapidApiToFixture = new RapidApiToFixture();

    public List<FixtureResponse> getFixtures(
            String authHeader,
            Integer league,
            Integer team,
            String date,
            FixtureStatus status){

        authClient.validate(authHeader);
        validateDateFormat(date);

        String effectiveDate = date != null ? date : LocalDate.now().toString();

        return externalApiClient.getFixtures().getResponse().stream()
                .filter(wrapper -> league == null
                        || wrapper.getLeague().getId().equals(league))
                .filter(wrapper -> team == null
                        || wrapper.getTeams().getHome().getId().equals(team)
                        || wrapper.getTeams().getAway().getId().equals(team))
                .filter(wrapper ->
                        wrapper.getFixture().getDate().startsWith(effectiveDate))
                .filter(wrapper -> status == null
                        || wrapper.getFixture().getStatus().getShortStatus()
                        .equals(status.toString()))
                .map(rapidApiToFixture::toFixtureResponse)
                .toList();
    }

    private void validateDateFormat(String date) {
        if (date == null) return;
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new InvalidParamException("INVALID_DATE_FORMAT",
                    "Invalid date format. Expected: YYYY-MM-DD");
        }
    }

    public List<LiveFixtureResponse> getLiveFixtures(String authHeader) {
        authClient.validate(authHeader);

        return externalApiClient.getFixtures().getResponse().stream()
                .filter(wrapper -> {
                    String shortStatus = wrapper.getFixture().getStatus().getShortStatus();
                    return !shortStatus.equals("NS") && !shortStatus.equals("FT");
                })
                .map(rapidApiToFixture::toLiveFixtureResponse)
                .toList();
    }

    public List<FixtureEventsResponse> getFixtureEvents(String authHeader, Integer fixtureId) {
        authClient.validate(authHeader);

        RapidApiFixtureResponse.ApiFixtureWrapper wrapper = externalApiClient.getFixtures()
                .getResponse().stream()
                .filter(f -> f.getFixture().getId().equals(fixtureId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("FIXTURE_NOT_FOUND",
                        "No existe un partido con el ID proporcionado"));

        return wrapper.getEvents().stream()
                .map(rapidApiToFixture::toFixtureEventsResponse)
                .toList();
    }

}
