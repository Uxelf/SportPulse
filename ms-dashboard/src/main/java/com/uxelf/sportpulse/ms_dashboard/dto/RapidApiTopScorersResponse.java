package com.uxelf.sportpulse.ms_dashboard.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RapidApiTopScorersResponse {
    private List<TopScorerWrapper> response;

    @Getter
    @Setter
    public static class TopScorerWrapper {
        private PlayerInfo player;
        private List<StatisticInfo> statistics;
    }

    @Getter
    @Setter
    public static class PlayerInfo {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class StatisticInfo {
        private TeamInfo team;
        private GoalsInfo goals;
    }

    @Getter
    @Setter
    public static class TeamInfo {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class GoalsInfo {
        private Integer total;
    }
}

