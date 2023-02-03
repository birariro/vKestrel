package com.birariro.playday.adapter.event;

public class BaseEvent {
    private long timestamp;

    public BaseEvent(){
        this.timestamp = System.currentTimeMillis();
    }
    public long getTimestamp() {
        return timestamp;
    }
}