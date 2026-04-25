package com.uxelf.sportpulse.external_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapidApiLeagueResponse {
    private List<LeagueWrapper> response;

    @Getter
    @Setter
    public static class LeagueWrapper {
        private LeagueInfo league;
        private CountryInfo country;
        private List<SeasonInfo> seasons;
    }

    @Getter
    @Setter
    public static class LeagueInfo {
        private Integer id;
        private String name;
        private String type;
        private String logo;
    }

    @Getter
    @Setter
    public static class CountryInfo {
        private String name;
        private String code;
        private String flag;
    }

    @Getter
    @Setter
    public static class SeasonInfo {
        private Integer year;
        private String start;
        private String end;
        private boolean current;
    }
}
