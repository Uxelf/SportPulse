package com.uxelf.sportpulse.ms_dashboard.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class LeagueDetailResponse {
    private Integer id;
    private String name;
    private String type;
    private String country;
    private String logo;
    private List<Integer> seasons;
    private SeasonDetail currentSeason;

    @Getter
    @Setter
    public static class SeasonDetail {
        private Integer year;
        private String startDate;
        private String endDate;
        private boolean current;
    }
}
