package com.birariro.visitknowledge.service;

import com.birariro.visitknowledge.adapter.persistence.jpa.library.Library;
import com.birariro.visitknowledge.service.registration.ResourceFileService;
import com.birariro.visitknowledge.service.registration.sync.LibrarySync;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


public class LibrarySyncTest {


    LibrarySync librarySync = new LibrarySync(new ResourceFileService());

    @Test
    public void echo() throws IOException {
        List<Library> syncLibrary = librarySync.getSyncLibrary();

        syncLibrary.forEach(item ->{
            System.out.println("item.getName() = " + item.getName());
        });
    }
}
