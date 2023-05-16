package com.birariro.vkestrel.service.library;

import com.birariro.vkestrel.adapter.persistence.library.Library;
import com.birariro.vkestrel.adapter.persistence.library.ScriptType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncLibraryService {


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

        File resource =  getFile(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryDto[] libraryDtoList = objectMapper.readValue(new FileReader(resource) , LibraryDto[].class);

        for (LibraryDto libraryDto : libraryDtoList) {
            Library library = new Library(libraryDto.getName(), libraryDto.getUrl(), libraryDto.getHome(), ScriptType.valueOf(libraryDto.getType()));
            libraries.add(library);
        }
        return libraries;
    }

    private File getFile(String path) throws IOException {

        Resource resource = new ClassPathResource(path);
        InputStream is = resource.getInputStream();

        File _file = File.createTempFile("create-file", ".json");

        try (FileOutputStream fos = new FileOutputStream(_file)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
        }
        return _file;
    }

    @Getter
    @ToString
    public static class LibraryDto {

        private String name;
        private String home;
        private String url;
        private String type;
    }


}

