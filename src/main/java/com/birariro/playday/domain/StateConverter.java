package com.birariro.playday.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter
public class StateConverter implements AttributeConverter<State, Integer> {

    @Override
    public Integer convertToDatabaseColumn(State attribute) {

        System.out.println("attribute = " + attribute);
        return attribute.getValue();
    }

    @Override
    public State convertToEntityAttribute(Integer dbData) {
        return State.getState(dbData);
    }
}
