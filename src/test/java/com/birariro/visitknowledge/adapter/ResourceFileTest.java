package com.birariro.visitknowledge.adapter;

import com.birariro.visitknowledge.adapter.file.ResourceFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ResourceFileTest {

    @Test
    public void getFile() throws IOException {
        ResourceFile resourceFile = new ResourceFile();
        resourceFile.getCompanyLibrary2();
    }
}
