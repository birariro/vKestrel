package com.birariro.visitknowledge.etc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void limitTest(){

        List<String> dates = List.of("A","B","C");

        List<String> collect = dates.stream().limit(5).collect(Collectors.toList());
        Assertions.assertEquals(dates.size(), collect.size());

    }
}
