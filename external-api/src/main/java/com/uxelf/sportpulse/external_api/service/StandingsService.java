package com.uxelf.sportpulse.external_api.service;

import com.uxelf.sportpulse.external_api.dto.RapidApiStandingsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingsService {
    public RapidApiStandingsResponse getStandings() {
        RapidApiStandingsResponse response = new RapidApiStandingsResponse();
        response.setResponse(List.of(
                buildLeague(140, "La Liga", "Spain", 2024, List.of(
                        buildEntry(1, 529, "FC Barcelona", "https://media.api-sports.io/football/teams/529.png",
                                48, 34, "Promotion - Champions League (Group Stage)", "WWWDW",
                                20, 15, 3, 2, 52, 18),
                        buildEntry(2, 541, "Real Madrid", "https://media.api-sports.io/football/teams/541.png",
                                44, 24, "Promotion - Champions League (Group Stage)", "WDWWW",
                                20, 13, 5, 2, 44, 20),
                        buildEntry(3, 530, "Atlético Madrid", "https://media.api-sports.io/football/teams/530.png",
                                38, 12, "Promotion - Champions League (Group Stage)", "WDWLW",
                                20, 11, 5, 4, 38, 26),
                        buildEntry(4, 543, "Real Betis", "https://media.api-sports.io/football/teams/543.png",
                                35, 8, "Promotion - Europa League (Group Stage)", "WWDLW",
                                20, 10, 5, 5, 35, 27),
                        buildEntry(5, 536, "Sevilla FC", "https://media.api-sports.io/football/teams/536.png",
                                30, 2, "Promotion - Europa Conference League (Qualification)", "DLWDW",
                                20, 8, 6, 6, 30, 28),
                        buildEntry(18, 532, "Valencia CF", "https://media.api-sports.io/football/teams/532.png",
                                18, -14, "Relegation - LaLiga2", "LLLLD",
                                20, 4, 6, 10, 22, 36)
                )),
                buildLeague(39, "Premier League", "England", 2024, List.of(
                        buildEntry(1, 50, "Manchester City", "https://media.api-sports.io/football/teams/50.png",
                                50, 28, "Promotion - Champions League (Group Stage)", "WWWWW",
                                20, 15, 5, 0, 55, 27),
                        buildEntry(2, 40, "Liverpool", "https://media.api-sports.io/football/teams/40.png",
                                47, 25, "Promotion - Champions League (Group Stage)", "WWDWW",
                                20, 14, 5, 1, 52, 27),
                        buildEntry(3, 42, "Arsenal", "https://media.api-sports.io/football/teams/42.png",
                                43, 20, "Promotion - Champions League (Group Stage)", "WWWDL",
                                20, 13, 4, 3, 45, 25),
                        buildEntry(4, 49, "Chelsea", "https://media.api-sports.io/football/teams/49.png",
                                38, 10, "Promotion - Champions League (Group Stage)", "WDWLW",
                                20, 11, 5, 4, 40, 30),
                        buildEntry(5, 47, "Tottenham Hotspur", "https://media.api-sports.io/football/teams/47.png",
                                35, 6, "Promotion - Europa League (Group Stage)", "DWWDL",
                                20, 10, 5, 5, 38, 32),
                        buildEntry(18, 33, "Manchester United", "https://media.api-sports.io/football/teams/33.png",
                                20, -10, "Relegation - Championship", "LLDLL",
                                20, 5, 5, 10, 25, 35)
                )),
                buildLeague(135, "Serie A", "Italy", 2024, List.of(
                        buildEntry(1, 505, "Inter Milan", "https://media.api-sports.io/football/teams/505.png",
                                50, 30, "Promotion - Champions League (Group Stage)", "WWWWW",
                                20, 15, 5, 0, 50, 20),
                        buildEntry(2, 496, "Juventus", "https://media.api-sports.io/football/teams/496.png",
                                44, 18, "Promotion - Champions League (Group Stage)", "WWDWL",
                                20, 13, 5, 2, 42, 24),
                        buildEntry(3, 489, "AC Milan", "https://media.api-sports.io/football/teams/489.png",
                                40, 14, "Promotion - Champions League (Group Stage)", "WDWWL",
                                20, 12, 4, 4, 40, 26),
                        buildEntry(4, 497, "AS Roma", "https://media.api-sports.io/football/teams/497.png",
                                36, 8, "Promotion - Europa League (Group Stage)", "WDLWW",
                                20, 10, 6, 4, 36, 28)
                )),
                buildLeague(78, "Bundesliga", "Germany", 2024, List.of(
                        buildEntry(1, 157, "Bayern Munich", "https://media.api-sports.io/football/teams/157.png",
                                52, 35, "Promotion - Champions League (Group Stage)", "WWWWW",
                                20, 16, 4, 0, 58, 23),
                        buildEntry(2, 173, "Bayer Leverkusen", "https://media.api-sports.io/football/teams/173.png",
                                45, 22, "Promotion - Champions League (Group Stage)", "WWWDW",
                                20, 13, 6, 1, 48, 26),
                        buildEntry(3, 165, "Borussia Dortmund", "https://media.api-sports.io/football/teams/165.png",
                                38, 10, "Promotion - Champions League (Group Stage)", "WDWLW",
                                20, 11, 5, 4, 40, 30)
                )),
                buildLeague(61, "Ligue 1", "France", 2024, List.of(
                        buildEntry(1, 85, "Paris Saint-Germain", "https://media.api-sports.io/football/teams/85.png",
                                55, 40, "Promotion - Champions League (Group Stage)", "WWWWW",
                                20, 17, 4, 0, 60, 20),
                        buildEntry(2, 81, "Marseille", "https://media.api-sports.io/football/teams/81.png",
                                40, 15, "Promotion - Champions League (Group Stage)", "WWDWL",
                                20, 12, 4, 4, 42, 27),
                        buildEntry(3, 80, "Lyon", "https://media.api-sports.io/football/teams/80.png",
                                36, 8, "Promotion - Europa League (Group Stage)", "WDLWW",
                                20, 10, 6, 4, 36, 28)
                ))
        ));
        return response;
    }

    private RapidApiStandingsResponse.LeagueWrapper buildLeague(
            Integer id, String name, String country, Integer season,
            List<RapidApiStandingsResponse.StandingEntry> standings) {

        RapidApiStandingsResponse.LeagueWrapper wrapper = new RapidApiStandingsResponse.LeagueWrapper();
        RapidApiStandingsResponse.LeagueInfo league = new RapidApiStandingsResponse.LeagueInfo();
        league.setId(id);
        league.setName(name);
        league.setCountry(country);
        league.setSeason(season);
        league.setStandings(standings);
        wrapper.setLeague(league);
        return wrapper;
    }

    private RapidApiStandingsResponse.StandingEntry buildEntry(
            Integer rank, Integer teamId, String teamName, String teamLogo,
            Integer points, Integer goalsDiff, String description, String form,
            Integer played, Integer win, Integer draw, Integer lose,
            Integer goalsFor, Integer against) {

        RapidApiStandingsResponse.StandingEntry entry = new RapidApiStandingsResponse.StandingEntry();
        entry.setRank(rank);
        entry.setPoints(points);
        entry.setGoalsDiff(goalsDiff);
        entry.setDescription(description);
        entry.setForm(form);

        RapidApiStandingsResponse.TeamInfo team = new RapidApiStandingsResponse.TeamInfo();
        team.setId(teamId);
        team.setName(teamName);
        team.setLogo(teamLogo);
        entry.setTeam(team);

        RapidApiStandingsResponse.StatsInfo stats = new RapidApiStandingsResponse.StatsInfo();
        stats.setPlayed(played);
        stats.setWin(win);
        stats.setDraw(draw);
        stats.setLose(lose);

        RapidApiStandingsResponse.GoalsInfo goals = new RapidApiStandingsResponse.GoalsInfo();
        goals.setGoalsFor(goalsFor);
        goals.setAgainst(against);
        stats.setGoals(goals);

        entry.setAll(stats);
        return entry;
    }
}
