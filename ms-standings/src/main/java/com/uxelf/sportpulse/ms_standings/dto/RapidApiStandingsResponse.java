package com.uxelf.sportpulse.ms_standings.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapidApiStandingsResponse {
    private List<LeagueWrapper> response;

    @Getter
    @Setter
    public static class LeagueWrapper {
        private LeagueInfo league;
    }

    @Getter
    @Setter
    public static class LeagueInfo {
        private Integer id;
        private String name;
        private String country;
        private Integer season;
        private List<StandingEntry> standings;
    }

    @Getter
    @Setter
    public static class StandingEntry {
        private Integer rank;
        private TeamInfo team;
        private Integer points;
        private Integer goalsDiff;
        private String description;
        private String form;
        private StatsInfo all;
    }

    @Getter
    @Setter
    public static class TeamInfo {
        private Integer id;
        private String name;
        private String logo;
    }

    @Getter
    @Setter
    public static class StatsInfo {
        private Integer played;
        private Integer win;
        private Integer draw;
        private Integer lose;
        private GoalsInfo goals;
    }

    @Getter
    @Setter
    public static class GoalsInfo {
        @JsonProperty("for")
        private Integer goalsFor;
        private Integer against;
    }
}