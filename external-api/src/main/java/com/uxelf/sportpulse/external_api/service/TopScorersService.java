package com.uxelf.sportpulse.external_api.service;

import com.uxelf.sportpulse.external_api.dto.RapidApiTopScorersResponse;

import java.util.List;

public class TopScorersService {

    public RapidApiTopScorersResponse getTopScorers() {
        RapidApiTopScorersResponse response = new RapidApiTopScorersResponse();
        response.setResponse(List.of(
                buildTopScorer(276, "Kylian Mbappé", 541, "Real Madrid", 18),
                buildTopScorer(1100, "Robert Lewandowski", 529, "FC Barcelona", 15),
                buildTopScorer(600, "Antoine Griezmann", 530, "Atlético Madrid", 12),
                buildTopScorer(300, "Mohamed Salah", 40, "Liverpool", 17),
                buildTopScorer(1000, "Harry Kane", 157, "Bayern Munich", 20),
                buildTopScorer(900, "Lautaro Martínez", 505, "Inter Milan", 16),
                buildTopScorer(500, "Bukayo Saka", 42, "Arsenal", 11),
                buildTopScorer(85, "Ousmane Dembélé", 85, "Paris Saint-Germain", 13)
        ));
        return response;
    }

    private RapidApiTopScorersResponse.TopScorerWrapper buildTopScorer(
            Integer playerId, String playerName,
            Integer teamId, String teamName,
            Integer goals) {

        RapidApiTopScorersResponse.TopScorerWrapper wrapper =
                new RapidApiTopScorersResponse.TopScorerWrapper();

        RapidApiTopScorersResponse.PlayerInfo player =
                new RapidApiTopScorersResponse.PlayerInfo();
        player.setId(playerId);
        player.setName(playerName);

        RapidApiTopScorersResponse.TeamInfo team =
                new RapidApiTopScorersResponse.TeamInfo();
        team.setId(teamId);
        team.setName(teamName);

        RapidApiTopScorersResponse.GoalsInfo goalsInfo =
                new RapidApiTopScorersResponse.GoalsInfo();
        goalsInfo.setTotal(goals);

        RapidApiTopScorersResponse.StatisticInfo statistic =
                new RapidApiTopScorersResponse.StatisticInfo();
        statistic.setTeam(team);
        statistic.setGoals(goalsInfo);

        wrapper.setPlayer(player);
        wrapper.setStatistics(List.of(statistic));

        return wrapper;
    }
}
