package com.uxelf.sportpulse.external_api.service;

import com.uxelf.sportpulse.external_api.dto.RapidApiFixtureResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixtureService {
    public RapidApiFixtureResponse getFixtures() {
        RapidApiFixtureResponse response = new RapidApiFixtureResponse();
        response.setResponse(List.of(

                // La Liga — partidos terminados
                buildFixture(1035001, "2025-01-10T21:00:00+01:00", "FT", "Finished", null,
                        "Camp Nou", "Barcelona",
                        140, "La Liga", "Jornada 19",
                        529, "FC Barcelona", "https://media.api-sports.io/football/teams/529.png", 3,
                        541, "Real Madrid", "https://media.api-sports.io/football/teams/541.png", 1,
                        List.of(
                                buildGoal(15, "FC Barcelona", 529, "Robert Lewandowski", 1100, "Pedri", 284),
                                buildGoal(42, "FC Barcelona", 529, "Raphinha", 1200, null, null),
                                buildCard(55, "Real Madrid", 541, "Kylian Mbappé", 276),
                                buildGoal(67, "Real Madrid", 541, "Kylian Mbappé", 276, null, null),
                                buildGoal(89, "FC Barcelona", 529, "Pedri", 284, "Raphinha", 1200)
                        )),

                buildFixture(1035002, "2025-01-12T18:30:00+01:00", "FT", "Finished", null,
                        "Estadio Cívitas Metropolitano", "Madrid",
                        140, "La Liga", "Jornada 19",
                        530, "Atlético Madrid", "https://media.api-sports.io/football/teams/530.png", 2,
                        536, "Sevilla FC", "https://media.api-sports.io/football/teams/536.png", 0,
                        List.of(
                                buildGoal(33, "Atlético Madrid", 530, "Antoine Griezmann", 600, null, null),
                                buildGoal(71, "Atlético Madrid", 530, "Álvaro Morata", 601, "Antoine Griezmann", 600),
                                buildCard(85, "Sevilla FC", 536, "Jesús Navas", 700)
                        )),

                // La Liga — próximos
                buildFixture(1035065, "2025-01-20T21:00:00+01:00", "NS", "Not Started", null,
                        "Camp Nou", "Barcelona",
                        140, "La Liga", "Jornada 21",
                        529, "FC Barcelona", "https://media.api-sports.io/football/teams/529.png", null,
                        541, "Real Madrid", "https://media.api-sports.io/football/teams/541.png", null,
                        List.of()),

                buildFixture(1035066, "2025-01-21T20:00:00+01:00", "NS", "Not Started", null,
                        "Estadio Ramón Sánchez-Pizjuán", "Sevilla",
                        140, "La Liga", "Jornada 21",
                        536, "Sevilla FC", "https://media.api-sports.io/football/teams/536.png", null,
                        543, "Real Betis", "https://media.api-sports.io/football/teams/543.png", null,
                        List.of()),

                // La Liga — en vivo
                buildFixture(1035020, "2025-01-15T21:00:00+01:00", "2H", "Second Half", 67,
                        "Estadio Benito Villamarín", "Sevilla",
                        140, "La Liga", "Jornada 20",
                        543, "Real Betis", "https://media.api-sports.io/football/teams/543.png", 1,
                        530, "Atlético Madrid", "https://media.api-sports.io/football/teams/530.png", 1,
                        List.of(
                                buildGoal(23, "Real Betis", 543, "Isco", 800, null, null),
                                buildGoal(55, "Atlético Madrid", 530, "Antoine Griezmann", 600, null, null),
                                buildCard(60, "Real Betis", 543, "Germán Pezzella", 801)
                        )),

                // Premier League — partidos terminados
                buildFixture(1035100, "2025-01-11T17:30:00+00:00", "FT", "Finished", null,
                        "Anfield", "Liverpool",
                        39, "Premier League", "Round 20",
                        40, "Liverpool", "https://media.api-sports.io/football/teams/40.png", 3,
                        33, "Manchester United", "https://media.api-sports.io/football/teams/33.png", 0,
                        List.of(
                                buildGoal(12, "Liverpool", 40, "Mohamed Salah", 300, null, null),
                                buildGoal(45, "Liverpool", 40, "Luis Díaz", 301, "Mohamed Salah", 300),
                                buildCard(50, "Manchester United", 33, "Bruno Fernandes", 400),
                                buildGoal(78, "Liverpool", 40, "Darwin Núñez", 302, null, null)
                        )),

                buildFixture(1035101, "2025-01-12T14:00:00+00:00", "FT", "Finished", null,
                        "Emirates Stadium", "London",
                        39, "Premier League", "Round 20",
                        42, "Arsenal", "https://media.api-sports.io/football/teams/42.png", 2,
                        49, "Chelsea", "https://media.api-sports.io/football/teams/49.png", 2,
                        List.of(
                                buildGoal(20, "Arsenal", 42, "Bukayo Saka", 500, null, null),
                                buildGoal(38, "Chelsea", 49, "Cole Palmer", 501, null, null),
                                buildGoal(60, "Chelsea", 49, "Nicolas Jackson", 502, "Cole Palmer", 501),
                                buildGoal(88, "Arsenal", 42, "Martin Ødegaard", 503, "Bukayo Saka", 500)
                        )),

                // Premier League — próximos
                buildFixture(1035110, "2025-01-20T20:00:00+00:00", "NS", "Not Started", null,
                        "Etihad Stadium", "Manchester",
                        39, "Premier League", "Round 21",
                        50, "Manchester City", "https://media.api-sports.io/football/teams/50.png", null,
                        47, "Tottenham Hotspur", "https://media.api-sports.io/football/teams/47.png", null,
                        List.of()),

                // Premier League — en vivo
                buildFixture(1035111, "2025-01-15T20:00:00+00:00", "2H", "Second Half", 55,
                        "Old Trafford", "Manchester",
                        39, "Premier League", "Round 21",
                        33, "Manchester United", "https://media.api-sports.io/football/teams/33.png", 1,
                        42, "Arsenal", "https://media.api-sports.io/football/teams/42.png", 2,
                        List.of(
                                buildGoal(10, "Arsenal", 42, "Bukayo Saka", 500, null, null),
                                buildGoal(34, "Manchester United", 33, "Rasmus Højlund", 401, null, null),
                                buildCard(40, "Manchester United", 33, "Casemiro", 402),
                                buildGoal(51, "Arsenal", 42, "Leandro Trossard", 504, "Martin Ødegaard", 503)
                        )),

                // Serie A — terminados
                buildFixture(1035200, "2025-01-11T20:45:00+01:00", "FT", "Finished", null,
                        "San Siro", "Milan",
                        135, "Serie A", "Giornata 20",
                        489, "AC Milan", "https://media.api-sports.io/football/teams/489.png", 1,
                        505, "Inter Milan", "https://media.api-sports.io/football/teams/505.png", 2,
                        List.of(
                                buildGoal(25, "Inter Milan", 505, "Lautaro Martínez", 900, null, null),
                                buildGoal(50, "AC Milan", 489, "Rafael Leão", 901, null, null),
                                buildGoal(75, "Inter Milan", 505, "Marcus Thuram", 902, "Lautaro Martínez", 900)
                        )),

                // Bundesliga — terminados
                buildFixture(1035300, "2025-01-11T18:30:00+01:00", "FT", "Finished", null,
                        "Allianz Arena", "Munich",
                        78, "Bundesliga", "Spieltag 18",
                        157, "Bayern Munich", "https://media.api-sports.io/football/teams/157.png", 4,
                        165, "Borussia Dortmund", "https://media.api-sports.io/football/teams/165.png", 0,
                        List.of(
                                buildGoal(8, "Bayern Munich", 157, "Harry Kane", 1000, null, null),
                                buildGoal(30, "Bayern Munich", 157, "Jamal Musiala", 1001, "Harry Kane", 1000),
                                buildCard(44, "Borussia Dortmund", 165, "Emre Can", 1100),
                                buildGoal(60, "Bayern Munich", 157, "Harry Kane", 1000, "Thomas Müller", 1002),
                                buildGoal(80, "Bayern Munich", 157, "Leroy Sané", 1003, null, null)
                        )),

                // Ligue 1 — próximos
                buildFixture(1035400, "2025-01-21T21:00:00+01:00", "NS", "Not Started", null,
                        "Parc des Princes", "Paris",
                        61, "Ligue 1", "Journée 20",
                        85, "Paris Saint-Germain", "https://media.api-sports.io/football/teams/85.png", null,
                        81, "Marseille", "https://media.api-sports.io/football/teams/81.png", null,
                        List.of())
        ));
        return response;
    }

    private RapidApiFixtureResponse.FixtureWrapper buildFixture(
            Integer id, String date, String shortStatus, String longStatus, Integer elapsed,
            String venueName, String venueCity,
            Integer leagueId, String leagueName, String round,
            Integer homeId, String homeName, String homeLogo, Integer homeGoals,
            Integer awayId, String awayName, String awayLogo, Integer awayGoals,
            List<RapidApiFixtureResponse.EventInfo> events) {

        RapidApiFixtureResponse.FixtureWrapper wrapper = new RapidApiFixtureResponse.FixtureWrapper();

        RapidApiFixtureResponse.FixtureInfo fixture = new RapidApiFixtureResponse.FixtureInfo();
        fixture.setId(id);
        fixture.setDate(date);

        RapidApiFixtureResponse.StatusInfo status = new RapidApiFixtureResponse.StatusInfo();
        status.setShortStatus(shortStatus);
        status.setLongStatus(longStatus);
        status.setElapsed(elapsed);
        fixture.setStatus(status);

        RapidApiFixtureResponse.VenueInfo venue = new RapidApiFixtureResponse.VenueInfo();
        venue.setName(venueName);
        venue.setCity(venueCity);
        fixture.setVenue(venue);

        RapidApiFixtureResponse.LeagueInfo league = new RapidApiFixtureResponse.LeagueInfo();
        league.setId(leagueId);
        league.setName(leagueName);
        league.setRound(round);

        RapidApiFixtureResponse.TeamInfo home = new RapidApiFixtureResponse.TeamInfo();
        home.setId(homeId);
        home.setName(homeName);
        home.setLogo(homeLogo);
        home.setGoals(homeGoals);

        RapidApiFixtureResponse.TeamInfo away = new RapidApiFixtureResponse.TeamInfo();
        away.setId(awayId);
        away.setName(awayName);
        away.setLogo(awayLogo);
        away.setGoals(awayGoals);

        RapidApiFixtureResponse.TeamsInfo teams = new RapidApiFixtureResponse.TeamsInfo();
        teams.setHome(home);
        teams.setAway(away);

        wrapper.setFixture(fixture);
        wrapper.setLeague(league);
        wrapper.setTeams(teams);
        wrapper.setEvents(events);

        return wrapper;
    }

    private RapidApiFixtureResponse.EventInfo buildGoal(
            Integer elapsed, String teamName, Integer teamId,
            String playerName, Integer playerId,
            String assistName, Integer assistId) {

        RapidApiFixtureResponse.EventInfo event = new RapidApiFixtureResponse.EventInfo();
        event.setElapsed(elapsed);
        event.setType("Goal");
        event.setDetail("Normal Goal");

        RapidApiFixtureResponse.TeamRef team = new RapidApiFixtureResponse.TeamRef();
        team.setId(teamId);
        team.setName(teamName);
        event.setTeam(team);

        RapidApiFixtureResponse.PlayerRef player = new RapidApiFixtureResponse.PlayerRef();
        player.setId(playerId);
        player.setName(playerName);
        event.setPlayer(player);

        if (assistName != null) {
            RapidApiFixtureResponse.PlayerRef assist = new RapidApiFixtureResponse.PlayerRef();
            assist.setId(assistId);
            assist.setName(assistName);
            event.setAssist(assist);
        }

        return event;
    }

    private RapidApiFixtureResponse.EventInfo buildCard(
            Integer elapsed, String teamName, Integer teamId,
            String playerName, Integer playerId) {

        RapidApiFixtureResponse.EventInfo event = new RapidApiFixtureResponse.EventInfo();
        event.setElapsed(elapsed);
        event.setType("Card");
        event.setDetail("Yellow Card");

        RapidApiFixtureResponse.TeamRef team = new RapidApiFixtureResponse.TeamRef();
        team.setId(teamId);
        team.setName(teamName);
        event.setTeam(team);

        RapidApiFixtureResponse.PlayerRef player = new RapidApiFixtureResponse.PlayerRef();
        player.setId(playerId);
        player.setName(playerName);
        event.setPlayer(player);

        return event;
    }
}
