package com.birariro.dailydevblogassemble.repository;

import com.birariro.dailydevblogassemble.domain.library.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SoftDeleteTest {

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    public void softDeleteQueryTest(){
        libraryRepository.findAll();
    }
}
