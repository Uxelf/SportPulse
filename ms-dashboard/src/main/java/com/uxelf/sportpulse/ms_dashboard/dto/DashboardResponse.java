package com.uxelf.sportpulse.ms_dashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class DashboardResponse {
    private LeagueInfo league;
    private String today;
    private List<MatchTodayInfo> matchesToday;
    private List<StandingPreviewInfo> standingsPreview;
    private List<TopScorerInfo> topScorers;
    private Instant lastUpdated;

    @Getter
    @Setter
    public static class LeagueInfo {
        private Integer id;
        private String name;
        private String country;
    }

    @Getter
    @Setter
    public static class MatchTodayInfo {
        private Integer id;
        private String date;
        private TeamMatchInfo homeTeam;
        private TeamMatchInfo awayTeam;
        private String status;
    }

    @Getter
    @Setter
    public static class TeamMatchInfo {
        private String name;
        private String logo;
    }

    @Getter
    @Setter
    public static class StandingPreviewInfo {
        private Integer rank;
        private String team;
        private Integer points;
        private Integer played;
    }

    @Getter
    @Setter
    public static class TopScorerInfo {
        private Integer rank;
        private String name;
        private String team;
        private Integer goals;
    }
}
