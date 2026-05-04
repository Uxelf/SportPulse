package com.uxelf.sportpulse.ms_fixtures.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixtureEventsResponse {
    private Integer elapsed;
    private String type;
    private String detail;
    private EventsTeamInfo team;
    private EventsPlayerInfo player;


    @Getter
    @Setter
    public static class EventsTeamInfo{
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class EventsPlayerInfo{
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    public static class EventsAssistInfo{
        private Integer id;
        private String name;
    }
}
