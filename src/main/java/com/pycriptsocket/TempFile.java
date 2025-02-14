package com.pycriptsocket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TempFile {

    public String processData(String data) {
        // Process the decrypted data and save it to a temporary file
        try {
            File tempFile = File.createTempFile("decrypted_", ".txt");
            FileWriter writer = new FileWriter(tempFile);
            writer.write(data);
            writer.close();
            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}