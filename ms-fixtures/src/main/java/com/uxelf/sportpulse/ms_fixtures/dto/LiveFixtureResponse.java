package com.uxelf.sportpulse.ms_fixtures.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiveFixtureResponse {
    private Integer id;
    private Integer elapsed;
    private FixtureResponse.StatusInfo status;
    private LiveLeagueInfo league;
    private LiveTeamInfo homeTeam;
    private LiveTeamInfo awayTeam;



    @Getter
    @Setter
    public static class LiveLeagueInfo{
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class LiveTeamInfo{
        private Integer id;
        private String name;
        private Integer goals;
    }
}
