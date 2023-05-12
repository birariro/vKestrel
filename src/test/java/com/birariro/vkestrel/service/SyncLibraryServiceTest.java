package com.birariro.vkestrel.service;

import com.birariro.vkestrel.adapter.persistence.library.Library;
import com.birariro.vkestrel.adapter.persistence.library.LibraryRepository;
import com.birariro.vkestrel.service.library.SyncLibraryService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class SyncLibraryServiceTest {

    @Autowired
    SyncLibraryService syncLibraryService;

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    public void echo() throws IOException {
        List<Library> syncLibrary = syncLibraryService.getSyncLibrary();

        List<Library> actives = syncLibrary.stream().filter(Library::isActive).collect(Collectors.toList());
        List<Library> inActives = syncLibrary.stream().filter(item -> !item.isActive()).collect(Collectors.toList());

        for (Library item : actives) {
            System.out.println("actives = " + item.getName());
        }

        for (Library item : inActives) {
            System.out.println("inActives = " + item.getName());
        }
    }

    @Test
    @DisplayName("기존 저정된 데이터 기준 활성, 비활성 분류 기능 테스트")
    public void sync() throws IOException {

        List<Library> items = syncLibraryService.getSyncLibrary();

        List<Library> activeLibrary = items.stream().filter(Library::isActive)
            .filter(library -> !libraryRepository.existsByName(library.getName()))
            .collect(Collectors.toList());

        for (Library library : activeLibrary) {
            System.out.println("new active = " + library.getName());
        }

        String s = activeLibrary.stream().map(Library::getName).collect(Collectors.toList()).toString();
        String addItemMessage = s + "사이트가 새롭게 추가 되었습니다.";

        System.out.println("addItemMessage = " + addItemMessage);

        List<String> inActiveLibraryNames = items.stream()
            .filter(item -> !item.isActive())
            .map(Library::getName)
            .collect(Collectors.toList());

        List<Library> activeByAll = libraryRepository.findActiveByAll()
            .stream().filter(item -> inActiveLibraryNames.contains(item.getName()))
            .collect(Collectors.toList());

        for (Library library : activeByAll) {
            System.out.println("new delete = " + library.getName());
        }
    }


}
