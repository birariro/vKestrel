package com.birariro.visitknowledge.etc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void limitTest(){

        List<String> dates = List.of("A","B","C");

        List<String> collect = dates.stream().limit(5).collect(Collectors.toList());
        Assertions.assertEquals(dates.size(), collect.size());

    }

    @Test
    public void randomTest(){

        List<String> dates = List.of("A","B","C","D","E","F","G");

        Random random = new Random();

        List<String> collect = random.ints(0, dates.size())
                .distinct()
                .limit(2)
                .mapToObj(dates::get)
                .collect(Collectors.toList());

        System.out.println("collect = " + collect);


    }
}
