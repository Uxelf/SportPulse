package com.uxelf.sportpulse.ms_teams.service;

import com.uxelf.sportpulse.ms_teams.client.AuthClient;
import com.uxelf.sportpulse.ms_teams.client.ExternalApiClient;
import com.uxelf.sportpulse.ms_teams.dto.team.RapidApiTeamResponse;
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
}
