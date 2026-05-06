package com.uxelf.sportpulse.ms_standings.dto.builder;

import com.uxelf.sportpulse.ms_standings.client.TeamsClient;
import com.uxelf.sportpulse.ms_standings.dto.RapidApiStandingsResponse;
import com.uxelf.sportpulse.ms_standings.dto.StandingsResponse;
import com.uxelf.sportpulse.ms_standings.dto.TeamDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RapidApiToStanding {

    private final TeamsClient teamsClient;

    public StandingsResponse.StandingEntry toStandingEntry(
            RapidApiStandingsResponse.StandingEntry entry, String authHeader) {

        StandingsResponse.StandingEntry dto = new StandingsResponse.StandingEntry();
        dto.setRank(entry.getRank());
        dto.setPoints(entry.getPoints());
        dto.setGoalDifference(entry.getGoalsDiff());
        dto.setDescription(entry.getDescription());
        dto.setForm(entry.getForm());
        dto.setPlayed(entry.getAll().getPlayed());
        dto.setWon(entry.getAll().getWin());
        dto.setDrawn(entry.getAll().getDraw());
        dto.setLost(entry.getAll().getLose());
        dto.setGoalsFor(entry.getAll().getGoals().getGoalsFor());
        dto.setGoalsAgainst(entry.getAll().getGoals().getAgainst());


        try {
            TeamDetailResponse teamDetail = teamsClient.getTeamById(
                    entry.getTeam().getId(), authHeader);
            StandingsResponse.TeamInfo team = new StandingsResponse.TeamInfo();
            team.setId(teamDetail.getId());
            team.setName(teamDetail.getName());
            team.setLogo(teamDetail.getLogo());
            dto.setTeam(team);
        } catch (Exception e) {
            StandingsResponse.TeamInfo team = new StandingsResponse.TeamInfo();
            team.setId(entry.getTeam().getId());
            team.setName(entry.getTeam().getName());
            team.setLogo(entry.getTeam().getLogo());
            dto.setTeam(team);
        }

        return dto;
    }
}
