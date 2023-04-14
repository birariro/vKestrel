package com.birariro.visitknowledge.controller.api.test;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test/info/library")
@RequiredArgsConstructor
public class InfoController {

    private final LibraryRepository libraryRepository;

    @GetMapping
    public ResponseEntity getLibrary(){

        List<String> collect = libraryRepository.findActiveByAll()
                                                .stream()
                                                .map(Library::getName)
                                                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
}
