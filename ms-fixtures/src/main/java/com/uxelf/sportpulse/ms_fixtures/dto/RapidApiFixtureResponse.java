package com.uxelf.sportpulse.ms_fixtures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapidApiFixtureResponse {
    private List<ApiFixtureWrapper> response;

    @Getter
    @Setter
    public static class ApiFixtureWrapper {
        private ApiFixtureInfo fixture;
        private ApiLeagueInfo league;
        private ApiTeamsInfo teams;
        private List<ApiEventInfo> events;
    }

    @Getter
    @Setter
    public static class ApiFixtureInfo {
        private Integer id;
        private String date;
        private ApiStatusInfo status;
        private ApiVenueInfo venue;
    }

    @Getter
    @Setter
    public static class ApiStatusInfo {
        @JsonProperty("short")
        private String shortStatus;
        @JsonProperty("long")
        private String longStatus;
        private Integer elapsed;
    }

    @Getter
    @Setter
    public static class ApiVenueInfo {
        private String name;
        private String city;
    }

    @Getter
    @Setter
    public static class ApiLeagueInfo {
        private Integer id;
        private String name;
        private String round;
    }

    @Getter
    @Setter
    public static class ApiTeamsInfo {
        private ApiTeamInfo home;
        private ApiTeamInfo away;
    }

    @Getter
    @Setter
    public static class ApiTeamInfo {
        private Integer id;
        private String name;
        private String logo;
        private Integer goals;
    }

    @Getter
    @Setter
    public static class ApiEventInfo {
        private Integer elapsed;
        private String type;
        private String detail;
        private ApiTeamRef team;
        private ApiPlayerRef player;
        private ApiPlayerRef assist;
    }

    @Getter
    @Setter
    public static class ApiTeamRef {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class ApiPlayerRef {
        private Integer id;
        private String name;
    }
}
