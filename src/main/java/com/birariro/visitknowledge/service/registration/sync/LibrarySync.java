package com.birariro.visitknowledge.service.registration.sync;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.adapter.persistence.jpa.library.UrlType;
import com.birariro.visitknowledge.controller.init.CompanyJsonDto;
import com.birariro.visitknowledge.service.registration.ResourceFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibrarySync {

    private final ResourceFileService resourceFileService;

    public List<Library> getSyncLibrary() throws IOException {

        List<Library> libraries = new ArrayList<>();

        File resource =  resourceFileService.getFile("company-library.json");
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyJsonDto[] companyJsonDtoList = objectMapper.readValue(new FileReader(resource) , CompanyJsonDto[].class);

        for (CompanyJsonDto companyJsonDto : companyJsonDtoList) {
            Library library = new Library(companyJsonDto.getName(), companyJsonDto.getUrl(), companyJsonDto.getHome(), UrlType.valueOf(companyJsonDto.getType()));
            libraries.add(library);
        }

        File resource2 =  resourceFileService.getFile("alon-library.json");
        ObjectMapper objectMapper2 = new ObjectMapper();
        CompanyJsonDto[] alonJsonDtoList = objectMapper2.readValue(new FileReader(resource2) , CompanyJsonDto[].class);
        for (CompanyJsonDto alonJsonDto : alonJsonDtoList) {
            Library library = new Library(alonJsonDto.getName(), alonJsonDto.getUrl(), alonJsonDto.getHome(), UrlType.valueOf(alonJsonDto.getType()));
            libraries.add(library);
        }

        return libraries;

    }


}

