package com.uxelf.sportpulse.ms_dashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StandingsResponse {
    private LeagueInfo league;
    private List<StandingEntry> standings;

    @Getter
    @Setter
    public static class LeagueInfo {
        private Integer id;
        private String name;
        private String country;
        private Integer season;
    }

    @Getter
    @Setter
    public static class StandingEntry {
        private Integer rank;
        private TeamInfo team;
        private Integer points;
        private Integer played;
    }

    @Getter
    @Setter
    public static class TeamInfo {
        private Integer id;
        private String name;
        private String logo;
    }
}