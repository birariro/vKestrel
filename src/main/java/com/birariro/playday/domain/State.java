package com.birariro.playday.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum State {

    INACTIVE(0),//비활성
    ACTIVE(1) //활성
    ;
    int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isActive(){
        return value == ACTIVE.value;
    }
    public static State getState(int value){

        Map<Integer, State> collect = Stream.of(values())
                .collect(Collectors.toMap(State::getValue, Function.identity()));
        return collect.get(value);
    }
}
