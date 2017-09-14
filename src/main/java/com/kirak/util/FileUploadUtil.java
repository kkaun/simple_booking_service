package com.kirak.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Kir on 24.08.2017.
 */
public class FileUploadUtil {

    public static boolean fileUploaded(MultipartFile multipartFile, String fileName) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File newFile = new File("/booking_demo/resources/uploaded_images/" + fileName);
        try {
            inputStream = multipartFile.getInputStream();
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
