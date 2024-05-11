package com.sales.app.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtil {
    public static byte[] convertMultipartFileToByteArray(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        return file.getBytes();
    }
}
