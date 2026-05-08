package com.uxelf.sportpulse.ms_notifications.scheduler;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FixtureStateStore {

    private final Map<Integer, FixtureState> states = new ConcurrentHashMap<>();

    public FixtureState get(Integer fixtureId){
        return states.get(fixtureId);
    }

    public void update(Integer fixtureId, FixtureState state){
        states.put(fixtureId, state);
    }

    public void remove(Integer fixtureID){
        states.remove(fixtureID);
    }

    public Set<Integer> getTrackedFixtures(){
        return states.keySet();
    }
}
