package com.uxelf.sportpulse.ms_notifications.dto.fixtures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiveFixtureResponse {
    private Integer id;
    private Integer elapsed;
    private StatusInfo status;
    private LiveLeagueInfo league;
    private LiveTeamInfo homeTeam;
    private LiveTeamInfo awayTeam;

    @Getter
    @Setter
    public static class StatusInfo{
        @JsonProperty("short")
        private String shortStatus;
        @JsonProperty("long")
        private String longStatus;
    }

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

