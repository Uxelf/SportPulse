package com.uxelf.sportpulse.ms_teams.dto.team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecificTeamResponse {
    private Integer id;
    private String name;
    private String country;
    private String logo;
    private Integer founded;
    private boolean national;
    private SpecificStadiumInfo stadium;

    @Getter
    @Setter
    public static class SpecificStadiumInfo{
        private String name;
        private String address;
        private String city;
        private Integer capacity;
        private String surface;
    }
}
