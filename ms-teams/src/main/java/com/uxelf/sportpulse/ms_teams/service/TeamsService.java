package com.uxelf.sportpulse.ms_teams.service;

import com.uxelf.sportpulse.ms_teams.client.AuthClient;
import com.uxelf.sportpulse.ms_teams.client.ExternalApiClient;
import com.uxelf.sportpulse.ms_teams.dto.team.RapidApiTeamResponse;
import com.uxelf.sportpulse.ms_teams.dto.team.SpecificTeamResponse;
import com.uxelf.sportpulse.ms_teams.dto.team.TeamResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final AuthClient authClient;
    private final ExternalApiClient externalApiClient;

    public List<TeamResponse> getTeams(String authHeader, int league, int season){
        authClient.validate(authHeader);

        RapidApiTeamResponse apiResponse = externalApiClient.getTeams();

        List<TeamResponse> response = new ArrayList<>();

        apiResponse.getResponse().forEach(teamWrapper -> {
            RapidApiTeamResponse.TeamInfo team = teamWrapper.getTeam();
            if (team.getLeague() != league || !team.getSeasons().contains(season))
                return;

            TeamResponse newTeam = new TeamResponse();
            TeamResponse.StadiumInfo stadium = new TeamResponse.StadiumInfo();

            newTeam.setId(team.getId());
            newTeam.setName(team.getName());
            newTeam.setCountry(team.getCountry());
            newTeam.setLogo(team.getLogo());
            newTeam.setFounded(team.getFounded());

            stadium.setName(teamWrapper.getVenue().getName());
            stadium.setCity(teamWrapper.getVenue().getCity());
            stadium.setCapacity(teamWrapper.getVenue().getCapacity());
            newTeam.setStadium(stadium);

            response.add(newTeam);
        });

        return response;
    }

    public SpecificTeamResponse getTeamById(String authHeader, int id){
        authClient.validate(authHeader);

        RapidApiTeamResponse apiResponse = externalApiClient.getTeams();

        RapidApiTeamResponse.TeamWrapper teamWrapper = apiResponse.getResponse().stream()
                .filter(t -> {return t.getTeam().getId() == id;})
                .findAny()
                .orElse(null);
        if (teamWrapper == null)
            return null;

        SpecificTeamResponse team = new SpecificTeamResponse();
        team.setId(teamWrapper.getTeam().getId());
        team.setName(teamWrapper.getTeam().getName());
        team.setCountry(teamWrapper.getTeam().getCountry());
        team.setLogo(teamWrapper.getTeam().getLogo());
        team.setFounded(teamWrapper.getTeam().getFounded());
        team.setNational(teamWrapper.getTeam().isNational());

        SpecificTeamResponse.SpecificStadiumInfo stadium = new SpecificTeamResponse.SpecificStadiumInfo();
        stadium.setName(teamWrapper.getVenue().getName());
        stadium.setAddress(teamWrapper.getVenue().getAddress());
        stadium.setCity(teamWrapper.getVenue().getCity());
        stadium.setCapacity(teamWrapper.getVenue().getCapacity());
        stadium.setSurface(teamWrapper.getVenue().getSurface());
        team.setStadium(stadium);

        return team;
    }
}
