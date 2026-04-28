package com.uxelf.sportpulse.ms_leagues.dto.league;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueResponse {
    private Integer id;
    private String name;
    private String type;
    private String country;
    private String logo;
    private Integer currentSeason;
    private String startDate;
    private String endDate;
}
