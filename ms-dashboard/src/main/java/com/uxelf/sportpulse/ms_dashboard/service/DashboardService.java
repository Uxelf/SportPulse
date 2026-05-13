package com.uxelf.sportpulse.ms_dashboard.service;

import com.uxelf.sportpulse.ms_dashboard.client.*;
import com.uxelf.sportpulse.ms_dashboard.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private final AuthClient authClient;
    private final LeaguesClient leaguesClient;
    private final FixturesClient fixturesClient;
    private final StandingsClient standingsClient;
    private final ExternalApiClient externalApiClient;

    public DashboardResponse getDashboard(String authHeader, Integer league, Integer season) {
        authClient.validate(authHeader);

        String today = LocalDate.now().toString();

        LeagueDetailResponse leagueDetail = leaguesClient.getLeagueById(league, authHeader);
        List<FixtureResponse> fixtures = fixturesClient.getFixtures(league, today, authHeader);
        StandingsResponse standings = standingsClient.getStandings(league, season, authHeader);
        RapidApiTopScorersResponse topScorers = externalApiClient.getTopScorers();

        DashboardResponse response = new DashboardResponse();
        response.setToday(today);
        response.setLastUpdated(Instant.now());

        DashboardResponse.LeagueInfo leagueInfo = new DashboardResponse.LeagueInfo();
        leagueInfo.setId(leagueDetail.getId());
        leagueInfo.setName(leagueDetail.getName());
        leagueInfo.setCountry(leagueDetail.getCountry());
        response.setLeague(leagueInfo);

        response.setMatchesToday(fixtures.stream()
                .map(this::toMatchTodayInfo)
                .toList());

        response.setStandingsPreview(standings.getStandings().stream()
                .limit(3)
                .map(this::toStandingPreviewInfo)
                .toList());

        response.setTopScorers(buildTopScorers(topScorers));

        return response;
    }

    private DashboardResponse.MatchTodayInfo toMatchTodayInfo(FixtureResponse fixture) {
        DashboardResponse.MatchTodayInfo match = new DashboardResponse.MatchTodayInfo();
        match.setId(fixture.getId());
        match.setDate(fixture.getDate());
        match.setStatus(fixture.getStatus().getShortStatus());

        DashboardResponse.TeamMatchInfo home = new DashboardResponse.TeamMatchInfo();
        home.setName(fixture.getHomeTeam().getName());
        home.setLogo(fixture.getHomeTeam().getLogo());
        match.setHomeTeam(home);

        DashboardResponse.TeamMatchInfo away = new DashboardResponse.TeamMatchInfo();
        away.setName(fixture.getAwayTeam().getName());
        away.setLogo(fixture.getAwayTeam().getLogo());
        match.setAwayTeam(away);

        return match;
    }

    private DashboardResponse.StandingPreviewInfo toStandingPreviewInfo(
            StandingsResponse.StandingEntry entry) {
        DashboardResponse.StandingPreviewInfo preview = new DashboardResponse.StandingPreviewInfo();
        preview.setRank(entry.getRank());
        preview.setTeam(entry.getTeam().getName());
        preview.setPoints(entry.getPoints());
        preview.setPlayed(entry.getPlayed());
        return preview;
    }

    private List<DashboardResponse.TopScorerInfo> buildTopScorers(
            RapidApiTopScorersResponse topScorers) {
        List<DashboardResponse.TopScorerInfo> result = new ArrayList<>();
        List<RapidApiTopScorersResponse.TopScorerWrapper> scorers = topScorers.getResponse();

        for (int i = 0; i < Math.min(3, scorers.size()); i++) {
            RapidApiTopScorersResponse.TopScorerWrapper wrapper = scorers.get(i);
            DashboardResponse.TopScorerInfo info = new DashboardResponse.TopScorerInfo();
            info.setRank(i + 1);
            info.setName(wrapper.getPlayer().getName());
            info.setTeam(wrapper.getStatistics().get(0).getTeam().getName());
            info.setGoals(wrapper.getStatistics().get(0).getGoals().getTotal());
            result.add(info);
        }

        return result;
    }
}
