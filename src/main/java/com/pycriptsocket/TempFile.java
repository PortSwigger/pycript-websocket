package com.pycriptsocket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import burp.api.montoya.core.ByteArray;

public class TempFile {

    public String processData(ByteArray data) {
        try {
            byte[] bytes = data.getBytes();
            File tempFile = File.createTempFile("decrypted_", ".txt");

            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(bytes);
            fos.close();

            if (MainUI.getInstance() != null && MainUI.getInstance().getLogUI().isLoggingEnabled()) {
                MainUI.getInstance().getLogUI().appendLog("$ cat " + tempFile.getAbsolutePath());

                String content = new String(bytes);
                MainUI.getInstance().getLogUI().appendLog(content);
            }

            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] readFileContent(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }
}