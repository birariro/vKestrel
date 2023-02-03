package com.birariro.playday.config;

public class BaseEvent {
    private long timestamp;

    public BaseEvent(){
        this.timestamp = System.currentTimeMillis();
    }
    public long getTimestamp() {
        return timestamp;
    }
}