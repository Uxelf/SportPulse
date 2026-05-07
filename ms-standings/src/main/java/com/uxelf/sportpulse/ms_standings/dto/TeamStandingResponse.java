package com.uxelf.sportpulse.ms_standings.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamStandingResponse {
    private TeamInfo team;
    private LeagueInfo league;
    private Integer season;
    private Integer rank;
    private Integer points;
    private Integer played;
    private String form;
    private String description;

    @Getter
    @Setter
    public static class TeamInfo {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class LeagueInfo {
        private Integer id;
        private String name;
    }
}
