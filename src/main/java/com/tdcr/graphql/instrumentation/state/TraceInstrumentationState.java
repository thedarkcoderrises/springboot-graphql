package com.tdcr.graphql.instrumentation.state;

import graphql.execution.instrumentation.InstrumentationState;

import java.util.HashMap;
import java.util.Map;

public class TraceInstrumentationState implements InstrumentationState {
    private Map<String, Object> anyStateYouLike = new HashMap<>();

    public void recordTiming(String key, long time) {
        anyStateYouLike.put(key, time);
    }
}