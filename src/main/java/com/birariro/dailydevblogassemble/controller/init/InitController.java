package com.birariro.dailydevblogassemble.controller.init;

import com.birariro.dailydevblogassemble.domain.library.Library;
import com.birariro.dailydevblogassemble.domain.library.LibraryRepository;
import com.birariro.dailydevblogassemble.domain.library.UrlType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InitController {

    private final LibraryRepository libraryRepository;
    @Transactional
    @GetMapping("/init/company")
    public ResponseEntity init() throws IOException {


        ClassPathResource companyResource = new ClassPathResource("company-library.json");



        ObjectMapper objectMapper = new ObjectMapper();
        CompanyJsonDto[] companyJsonDtos = objectMapper.readValue(new FileReader(companyResource.getFile()) , CompanyJsonDto[].class);

        List<Library> libraries = new ArrayList<>();
        for (CompanyJsonDto companyJsonDto : companyJsonDtos) {

            log.info("[init] library : "+ companyJsonDto.getName());
            if( libraryRepository.existsByName(companyJsonDto.getName())) {
                log.info("[init] 존재하는 library : "+ companyJsonDto.getName());
                continue;
            }

            Library library = new Library(companyJsonDto.getName(), companyJsonDto.getUrl(), companyJsonDto.getHome(), UrlType.valueOf(companyJsonDto.getType()));
            libraries.add(library);
        }


        ClassPathResource alonResource = new ClassPathResource("alon-library.json");
        ObjectMapper objectMapper2 = new ObjectMapper();
        CompanyJsonDto[] alonJsonDtos = objectMapper2.readValue(new FileReader(alonResource.getFile()) , CompanyJsonDto[].class);
        for (CompanyJsonDto alonJsonDto : alonJsonDtos) {

            log.info("[init] library : "+ alonJsonDto.getName());
            if( libraryRepository.existsByName(alonJsonDto.getName())) {
                log.info("[init] 존재하는 library : "+ alonJsonDto.getName());
                continue;
            }

            Library library = new Library(alonJsonDto.getName(), alonJsonDto.getUrl(), alonJsonDto.getHome(), UrlType.valueOf(alonJsonDto.getType()));
            libraries.add(library);
        }



        libraryRepository.saveAll(libraries);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
