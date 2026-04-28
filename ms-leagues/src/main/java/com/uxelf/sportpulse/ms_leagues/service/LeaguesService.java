package com.uxelf.sportpulse.ms_leagues.service;

import com.uxelf.sportpulse.ms_leagues.client.AuthClient;
import com.uxelf.sportpulse.ms_leagues.client.ExternalApiClient;
import com.uxelf.sportpulse.ms_leagues.dto.league.LeagueResponse;
import com.uxelf.sportpulse.ms_leagues.dto.league.RapidApiLeagueResponse;
import com.uxelf.sportpulse.ms_leagues.dto.league.SpecificLeagueResponse;
import com.uxelf.sportpulse.ms_leagues.dto.validate.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaguesService {

    private final AuthClient authClient;
    private final ExternalApiClient externalApiClient;

    public List<LeagueResponse> getLeagues(String authHeader, String country, Integer season){
        authClient.validate(authHeader);

        List<LeagueResponse> response = new ArrayList<>();

        RapidApiLeagueResponse rapidApiLeagueResponse = externalApiClient.getLeagues();

        rapidApiLeagueResponse.getResponse().forEach(leagueWrapper -> {
            LeagueResponse league = new LeagueResponse();

            if (country != null && !leagueWrapper.getCountry().getName().equals(country)){
                   return;
            }
            if (season != null
                    && leagueWrapper.getSeasons().stream().noneMatch(
                    seasonInfo -> Objects.equals(seasonInfo.getYear(), season)
            )){
                return;
            }
            league.setId(leagueWrapper.getLeague().getId());
            league.setName(leagueWrapper.getLeague().getName());
            league.setType(leagueWrapper.getLeague().getType());
            league.setCountry(leagueWrapper.getCountry().getName());
            league.setLogo(leagueWrapper.getLeague().getLogo());

            leagueWrapper.getSeasons().forEach(seasonInfo -> {
                if (seasonInfo.isCurrent()){
                    league.setCurrentSeason(seasonInfo.getYear());
                    league.setStartDate(seasonInfo.getStart());
                    league.setEndDate(seasonInfo.getEnd());
                }
            });

            response.add(league);
        });

        return response;
    }

    public SpecificLeagueResponse getLeagueById(String authHeader, int id){
        authClient.validate(authHeader);

        RapidApiLeagueResponse apiLeagueResponse = externalApiClient.getLeagues();

        RapidApiLeagueResponse.LeagueWrapper leagueFound = apiLeagueResponse.getResponse().stream()
                .filter(leagueWrapper -> {return leagueWrapper.getLeague().getId() == id;})
                .findAny()
                .orElse(null);

        if (leagueFound == null)
            return null;

        SpecificLeagueResponse specificLeague = new SpecificLeagueResponse();
        specificLeague.setId(leagueFound.getLeague().getId());
        specificLeague.setName(leagueFound.getLeague().getName());
        specificLeague.setType(leagueFound.getLeague().getType());
        specificLeague.setCountry(leagueFound.getCountry().getName());
        specificLeague.setLogo(leagueFound.getLeague().getLogo());

        List<Integer> seasons = new ArrayList<>();
        leagueFound.getSeasons().forEach(seasonInfo -> {
            seasons.add(seasonInfo.getYear());
            if (seasonInfo.isCurrent()){
                SpecificLeagueResponse.Season currentSeason = new SpecificLeagueResponse.Season();
                currentSeason.setYear(seasonInfo.getYear());
                currentSeason.setStart(seasonInfo.getStart());
                currentSeason.setEnd(seasonInfo.getEnd());
                currentSeason.setCurrent(true);
                specificLeague.setCurrentSeason(currentSeason);
            }
        });

        specificLeague.setSeasons(seasons);

        return specificLeague;
    }
}
