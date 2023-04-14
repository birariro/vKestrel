package com.birariro.vkestrel.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ResourceFileService {

    public File getFile(String path) throws IOException {

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
}
