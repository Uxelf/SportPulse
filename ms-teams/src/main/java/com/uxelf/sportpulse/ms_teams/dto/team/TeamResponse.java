package com.uxelf.sportpulse.ms_teams.dto.team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponse {
    private Integer id;
    private String name;
    private String country;
    private String logo;
    private Integer founded;
    private StadiumInfo stadium;

    @Getter
    @Setter
    public static class StadiumInfo{
        private String name;
        private String city;
        private Integer capacity;
    }
}
