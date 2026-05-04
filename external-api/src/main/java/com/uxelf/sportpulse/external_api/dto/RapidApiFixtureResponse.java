package com.uxelf.sportpulse.external_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapidApiFixtureResponse {
    private List<FixtureWrapper> response;

    @Getter
    @Setter
    public static class FixtureWrapper {
        private FixtureInfo fixture;
        private LeagueInfo league;
        private TeamsInfo teams;
        private List<EventInfo> events;
    }

    @Getter
    @Setter
    public static class FixtureInfo {
        private Integer id;
        private String date;
        private StatusInfo status;
        private VenueInfo venue;
    }

    @Getter
    @Setter
    public static class StatusInfo {
        @JsonProperty("short")
        private String shortStatus;
        @JsonProperty("long")
        private String longStatus;
        private Integer elapsed;
    }

    @Getter
    @Setter
    public static class VenueInfo {
        private String name;
        private String city;
    }

    @Getter
    @Setter
    public static class LeagueInfo {
        private Integer id;
        private String name;
        private String round;
    }

    @Getter
    @Setter
    public static class TeamsInfo {
        private TeamInfo home;
        private TeamInfo away;
    }

    @Getter
    @Setter
    public static class TeamInfo {
        private Integer id;
        private String name;
        private String logo;
        private Integer goals;
    }

    @Getter
    @Setter
    public static class EventInfo {
        private Integer elapsed;
        private String type;
        private String detail;
        private TeamRef team;
        private PlayerRef player;
        private PlayerRef assist;
    }

    @Getter
    @Setter
    public static class TeamRef {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class PlayerRef {
        private Integer id;
        private String name;
    }
}
