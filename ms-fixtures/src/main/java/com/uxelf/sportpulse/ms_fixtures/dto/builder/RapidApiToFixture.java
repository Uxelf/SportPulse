package com.uxelf.sportpulse.ms_fixtures.dto.builder;

import com.uxelf.sportpulse.ms_fixtures.dto.FixtureEventsResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.FixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.LiveFixtureResponse;
import com.uxelf.sportpulse.ms_fixtures.dto.RapidApiFixtureResponse;

import java.time.Instant;

public class RapidApiToFixture {

    public FixtureResponse toFixtureResponse(RapidApiFixtureResponse.ApiFixtureWrapper wrapper) {
        FixtureResponse response = new FixtureResponse();

        response.setId(wrapper.getFixture().getId());
        response.setDate(Instant.parse(wrapper.getFixture().getDate()));

        FixtureResponse.StatusInfo status = new FixtureResponse.StatusInfo();
        status.setShortStatus(wrapper.getFixture().getStatus().getShortStatus());
        status.setLongStatus(wrapper.getFixture().getStatus().getLongStatus());
        response.setStatus(status);

        FixtureResponse.LeagueInfo league = new FixtureResponse.LeagueInfo();
        league.setId(wrapper.getLeague().getId());
        league.setName(wrapper.getLeague().getName());
        league.setRound(wrapper.getLeague().getRound());
        response.setLeague(league);

        FixtureResponse.TeamInfo home = new FixtureResponse.TeamInfo();
        home.setId(wrapper.getTeams().getHome().getId());
        home.setName(wrapper.getTeams().getHome().getName());
        home.setLogo(wrapper.getTeams().getHome().getLogo());
        home.setGoals(wrapper.getTeams().getHome().getGoals());
        response.setHomeTeam(home);

        FixtureResponse.TeamInfo away = new FixtureResponse.TeamInfo();
        away.setId(wrapper.getTeams().getAway().getId());
        away.setName(wrapper.getTeams().getAway().getName());
        away.setLogo(wrapper.getTeams().getAway().getLogo());
        away.setGoals(wrapper.getTeams().getAway().getGoals());
        response.setAwayTeam(away);

        FixtureResponse.VenueInfo venue = new FixtureResponse.VenueInfo();
        venue.setName(wrapper.getFixture().getVenue().getName());
        venue.setCity(wrapper.getFixture().getVenue().getCity());
        response.setVenue(venue);

        return response;
    }

    public LiveFixtureResponse toLiveFixtureResponse(RapidApiFixtureResponse.ApiFixtureWrapper wrapper) {
        LiveFixtureResponse response = new LiveFixtureResponse();

        response.setId(wrapper.getFixture().getId());
        response.setElapsed(wrapper.getFixture().getStatus().getElapsed());

        FixtureResponse.StatusInfo status = new FixtureResponse.StatusInfo();
        status.setShortStatus(wrapper.getFixture().getStatus().getShortStatus());
        status.setLongStatus(wrapper.getFixture().getStatus().getLongStatus());
        response.setStatus(status);

        LiveFixtureResponse.LiveLeagueInfo league = new LiveFixtureResponse.LiveLeagueInfo();
        league.setId(wrapper.getLeague().getId());
        league.setName(wrapper.getLeague().getName());
        response.setLeague(league);

        LiveFixtureResponse.LiveTeamInfo home = new LiveFixtureResponse.LiveTeamInfo();
        home.setId(wrapper.getTeams().getHome().getId());
        home.setName(wrapper.getTeams().getHome().getName());
        home.setGoals(wrapper.getTeams().getHome().getGoals());
        response.setHomeTeam(home);

        LiveFixtureResponse.LiveTeamInfo away = new LiveFixtureResponse.LiveTeamInfo();
        away.setId(wrapper.getTeams().getAway().getId());
        away.setName(wrapper.getTeams().getAway().getName());
        away.setGoals(wrapper.getTeams().getAway().getGoals());
        response.setAwayTeam(away);

        return response;
    }

    public FixtureEventsResponse toFixtureEventsResponse(RapidApiFixtureResponse.ApiEventInfo eventInfo) {
        FixtureEventsResponse response = new FixtureEventsResponse();

        response.setElapsed(eventInfo.getElapsed());
        response.setType(eventInfo.getType());
        response.setDetail(eventInfo.getDetail());

        FixtureEventsResponse.EventsTeamInfo team = new FixtureEventsResponse.EventsTeamInfo();
        team.setId(eventInfo.getTeam().getId());
        team.setName(eventInfo.getTeam().getName());
        response.setTeam(team);

        FixtureEventsResponse.EventsPlayerInfo player = new FixtureEventsResponse.EventsPlayerInfo();
        player.setId(eventInfo.getPlayer().getId());
        player.setName(eventInfo.getPlayer().getName());
        response.setPlayer(player);

        if (eventInfo.getAssist() != null) {
            FixtureEventsResponse.EventsAssistInfo assist = new FixtureEventsResponse.EventsAssistInfo();
            assist.setId(eventInfo.getAssist().getId());
            assist.setName(eventInfo.getAssist().getName());
            response.setAssist(assist);
        }

        return response;
    }
}
