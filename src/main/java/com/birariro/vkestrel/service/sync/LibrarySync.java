package com.birariro.vkestrel.service.sync;

import com.birariro.vkestrel.adapter.persistence.jpa.library.Library;
import com.birariro.vkestrel.adapter.persistence.jpa.library.ScriptType;
import com.birariro.vkestrel.service.ResourceFileService;
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

        List<Library> orgLibraries= getResourcesFileToLibraryList("org-library.json");
        List<Library> libraries = getResourcesFileToLibraryList("library.json");
        List<Library> inActiveLibraries = getResourcesFileToLibraryList("inactive-library.json");
        inActiveLibraries.stream().forEach(Library::inActive);



        List<Library> result = new ArrayList<>();
        result.addAll(orgLibraries);
        result.addAll(libraries);
        result.addAll(inActiveLibraries);
        return result;

    }

    private List<Library> getResourcesFileToLibraryList(String fileName) throws IOException {

        List<Library> libraries = new ArrayList<>();

        File resource =  resourceFileService.getFile(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryDto[] libraryDtoList = objectMapper.readValue(new FileReader(resource) , LibraryDto[].class);

        for (LibraryDto libraryDto : libraryDtoList) {
            Library library = new Library(libraryDto.getName(), libraryDto.getUrl(), libraryDto.getHome(), ScriptType.valueOf(libraryDto.getType()));
            libraries.add(library);
        }
        return libraries;
    }

}

