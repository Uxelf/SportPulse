package com.uxelf.sportpulse.ms_leagues.dto.league;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpecificLeagueResponse {
    private Integer id;
    private String name;
    private String type;
    private String country;
    private String logo;
    private List<Integer> seasons;
    private Season currentSeason;

    @Getter
    @Setter
    public static class Season{
        private Integer year;
        private String start;
        private String end;
        private boolean current;
    }
}
