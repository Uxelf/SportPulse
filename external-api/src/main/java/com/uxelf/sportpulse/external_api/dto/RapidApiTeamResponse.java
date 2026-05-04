package com.uxelf.sportpulse.external_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapidApiTeamResponse {
    private List<TeamWrapper> response;

    @Getter
    @Setter
    public static class TeamWrapper {
        private TeamInfo team;
        private VenueInfo venue;
    }

    @Getter
    @Setter
    public static class TeamInfo {
        private Integer id;
        private String name;
        private String country;
        private String logo;
        private Integer founded;
        private boolean national;
        private Integer league;
        private List<Integer> seasons;
    }

    @Getter
    @Setter
    public static class VenueInfo {
        private String name;
        private String address;
        private String city;
        private Integer capacity;
        private String surface;
    }
}
