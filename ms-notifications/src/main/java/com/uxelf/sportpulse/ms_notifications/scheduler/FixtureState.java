package com.uxelf.sportpulse.ms_notifications.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FixtureState {
    private Integer fixtureId;
    private Integer lastElapsed;
    private Integer homeTeamId;
    private Integer awayTeamId;
}
