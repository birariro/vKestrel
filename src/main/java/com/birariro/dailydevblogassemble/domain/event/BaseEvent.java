package com.birariro.dailydevblogassemble.domain.event;

public class BaseEvent {
    private long timestamp;

    public BaseEvent(){
        this.timestamp = System.currentTimeMillis();
    }
    public long getTimestamp() {
        return timestamp;
    }
}