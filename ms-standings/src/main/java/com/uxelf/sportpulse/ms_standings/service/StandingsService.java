package com.uxelf.sportpulse.ms_standings.service;

import com.uxelf.sportpulse.ms_standings.client.AuthClient;
import com.uxelf.sportpulse.ms_standings.client.ExternalApiClient;
import com.uxelf.sportpulse.ms_standings.dto.RapidApiStandingsResponse;
import com.uxelf.sportpulse.ms_standings.dto.StandingsResponse;
import com.uxelf.sportpulse.ms_standings.dto.TeamStandingResponse;
import com.uxelf.sportpulse.ms_standings.dto.builder.RapidApiToStanding;
import com.uxelf.sportpulse.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class StandingsService {

    private final AuthClient authClient;
    private final ExternalApiClient externalApiClient;
    private final RapidApiToStanding rapidApiToStanding;

    public StandingsResponse getStandings(String authHeader, Integer league, Integer season) {
        authClient.validate(authHeader);

        RapidApiStandingsResponse.LeagueWrapper wrapper = externalApiClient.getStandings()
                .getResponse().stream()
                .filter(w -> w.getLeague().getId().equals(league)
                        && w.getLeague().getSeason().equals(season))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("LEAGUE_NOT_FOUND",
                        "No existe una liga con el ID proporcionado"));

        RapidApiStandingsResponse.LeagueInfo leagueInfo = wrapper.getLeague();

        StandingsResponse response = new StandingsResponse();

        StandingsResponse.LeagueInfo leagueDto = new StandingsResponse.LeagueInfo();
        leagueDto.setId(leagueInfo.getId());
        leagueDto.setName(leagueInfo.getName());
        leagueDto.setCountry(leagueInfo.getCountry());
        leagueDto.setSeason(leagueInfo.getSeason());
        response.setLeague(leagueDto);

        List<StandingsResponse.StandingEntry> entries = leagueInfo.getStandings().stream()
                .sorted(Comparator.comparingInt(RapidApiStandingsResponse.StandingEntry::getRank))
                .map(entry -> rapidApiToStanding.toStandingEntry(entry, authHeader))
                .toList();

        response.setStandings(entries);
        return response;
    }

    public TeamStandingResponse getTeamStanding(
            String authHeader, Integer teamId, Integer league, Integer season) {

        authClient.validate(authHeader);

        RapidApiStandingsResponse.LeagueWrapper wrapper = externalApiClient.getStandings()
                .getResponse().stream()
                .filter(w -> w.getLeague().getId().equals(league)
                        && w.getLeague().getSeason().equals(season))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("LEAGUE_NOT_FOUND",
                        "No existe una liga con el ID proporcionado"));

        RapidApiStandingsResponse.LeagueInfo leagueInfo = wrapper.getLeague();

        RapidApiStandingsResponse.StandingEntry entry = leagueInfo.getStandings().stream()
                .filter(e -> e.getTeam().getId().equals(teamId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("TEAM_NOT_FOUND",
                        "No existe un equipo con el ID proporcionado en esa liga y temporada"));

        TeamStandingResponse response = new TeamStandingResponse();
        response.setSeason(season);
        response.setRank(entry.getRank());
        response.setPoints(entry.getPoints());
        response.setPlayed(entry.getAll().getPlayed());
        response.setForm(entry.getForm());
        response.setDescription(entry.getDescription());

        TeamStandingResponse.TeamInfo team = new TeamStandingResponse.TeamInfo();
        team.setId(entry.getTeam().getId());
        team.setName(entry.getTeam().getName());
        response.setTeam(team);

        TeamStandingResponse.LeagueInfo leagueDto = new TeamStandingResponse.LeagueInfo();
        leagueDto.setId(leagueInfo.getId());
        leagueDto.setName(leagueInfo.getName());
        response.setLeague(leagueDto);

        return response;
    }

}
