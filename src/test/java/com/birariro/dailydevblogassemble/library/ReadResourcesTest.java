package com.birariro.dailydevblogassemble.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ReadResourcesTest {

    @Test
    public void readTest() throws IOException {

        ClassPathResource resource = new ClassPathResource("company-library.json");
        System.out.println("resource = " + resource.getPath());

        FileReader fileReader = new FileReader(resource.getFile());



        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonTestClass[] jsonTestClasses = objectMapper.readValue(fileReader, JsonTestClass[].class);


            System.out.println("jsonTestClass = " + jsonTestClasses);
            System.out.println("jsonTestClass = " + jsonTestClasses[0].name);

            for (JsonTestClass testClass : jsonTestClasses) {
                System.out.println("testClass = " + testClass);
            }


        } catch (Exception e) {
            System.out.println("e = " + e);
        }


    }
}
