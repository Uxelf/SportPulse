package com.uxelf.sportpulse.external_api.service;

import com.uxelf.sportpulse.external_api.dto.RapidApiFixtureResponse;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureService {
    private final Instant startTime = Instant.now();

    public RapidApiFixtureResponse getFixtures() {
        int elapsed = calculateElapsed();
        RapidApiFixtureResponse response = new RapidApiFixtureResponse();
        response.setResponse(buildFixtures(elapsed));
        return response;
    }

    private int calculateElapsed() {
        long secondsSinceStart = Duration.between(startTime, Instant.now()).getSeconds() % 120;
        // Cada segundo real = 1 minuto de partido, máximo 120
        return (int) Math.min(secondsSinceStart, 120);
    }

    private List<RapidApiFixtureResponse.FixtureWrapper> buildFixtures(int elapsed) {
        List<RapidApiFixtureResponse.FixtureWrapper> fixtures = new ArrayList<>();

        // Partido en vivo que progresa
        String status;
        String longStatus;
        if (elapsed <= 1) {
            status = "NS";
            longStatus = "Not Started";
        } else if (elapsed <= 45) {
            status = "1H";
            longStatus = "First Half";
        } else if (elapsed == 46) {
            status = "HT";
            longStatus = "Half Time";
        } else if (elapsed <= 90) {
            status = "2H";
            longStatus = "Second Half";
        } else {
            status = "FT";
            longStatus = "Finished";
        }

        // Goles simulados en minutos concretos
        Integer homeGoals = 0;
        Integer awayGoals = 0;
        List<RapidApiFixtureResponse.EventInfo> events = new ArrayList<>();

        if (elapsed >= 15) {
            homeGoals++;
            events.add(buildGoal(15, "FC Barcelona", 529, "Robert Lewandowski", 1100, "Pedri", 284));
        }
        if (elapsed >= 33) {
            events.add(buildCard(33, "Real Madrid", 541, "Kylian Mbappé", 276));
        }
        if (elapsed >= 55) {
            awayGoals++;
            events.add(buildGoal(55, "Real Madrid", 541, "Kylian Mbappé", 276, null, null));
        }
        if (elapsed >= 72) {
            homeGoals++;
            events.add(buildGoal(72, "FC Barcelona", 529, "Pedri", 284, "Raphinha", 1200));
        }

        fixtures.add(buildFixture(
                1035020,
                "2025-01-15T21:00:00+01:00",
                status, longStatus, elapsed <= 100 ? elapsed : null,
                "Camp Nou", "Barcelona",
                140, "La Liga", "Jornada 20",
                529, "FC Barcelona", "https://media.api-sports.io/football/teams/529.png", homeGoals,
                541, "Real Madrid", "https://media.api-sports.io/football/teams/541.png", awayGoals,
                events
        ));

        // Partido no iniciado — siempre estático
        fixtures.add(buildFixture(
                1035065,
                "2025-01-20T21:00:00+01:00",
                "NS", "Not Started", null,
                "Etihad Stadium", "Manchester",
                39, "Premier League", "Jornada 21",
                50, "Manchester City", "https://media.api-sports.io/football/teams/50.png", null,
                47, "Tottenham Hotspur", "https://media.api-sports.io/football/teams/47.png", null,
                List.of()
        ));

        // Partido terminado — siempre estático
        fixtures.add(buildFixture(
                1035001,
                "2025-01-10T21:00:00+01:00",
                "FT", "Finished", null,
                "Anfield", "Liverpool",
                39, "Premier League", "Jornada 19",
                40, "Liverpool", "https://media.api-sports.io/football/teams/40.png", 3,
                33, "Manchester United", "https://media.api-sports.io/football/teams/33.png", 0,
                List.of(
                        buildGoal(12, "Liverpool", 40, "Mohamed Salah", 300, null, null),
                        buildGoal(45, "Liverpool", 40, "Luis Díaz", 301, "Mohamed Salah", 300),
                        buildGoal(78, "Liverpool", 40, "Darwin Núñez", 302, null, null)
                )
        ));

        return fixtures;
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
