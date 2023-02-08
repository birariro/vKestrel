package com.birariro.dailydevblogassemble.validation;

import com.birariro.dailydevblogassemble.domain.library.Library;
import com.birariro.dailydevblogassemble.domain.library.LibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotNullTest {


    @Autowired
    LibraryRepository libraryRepository;

    @Test
    @DisplayName("필드에 @notnull 어노테이션을 사용하여 null 검사")
    public void notnullTest(){

        Library library = new Library(null,null,null,null);
        System.out.println("library = " + library);

        Assertions.assertThrows(RuntimeException.class, () ->{
            libraryRepository.save(library);
        });

    }
}
