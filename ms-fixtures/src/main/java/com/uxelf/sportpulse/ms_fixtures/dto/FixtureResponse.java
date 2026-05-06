package com.uxelf.sportpulse.ms_fixtures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uxelf.sportpulse.ms_fixtures.enums.FixtureStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class FixtureResponse {
    private Integer id;
    private Instant date;
    private StatusInfo status;
    private LeagueInfo league;
    private TeamInfo homeTeam;
    private TeamInfo awayTeam;
    private VenueInfo venue;

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
    public static class LeagueInfo{
        private Integer id;
        private String name;
        private String round;
    }

    @Getter
    @Setter
    public static class TeamInfo{
        private Integer id;
        private String name;
        private String logo;
        private Integer goals;
    }

    @Getter
    @Setter
    public static class VenueInfo{
        private String name;
        private String city;
    }
}
