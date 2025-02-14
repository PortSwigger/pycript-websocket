package com.pycriptsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Execution {

    public boolean runCommand(String decryptionFilePath, String tempFilePath) {
        try {
            String languageBinaryPath = UI.getInstance().getLanguageBinaryPath();
            List<String> command = new ArrayList<>();

            if (languageBinaryPath != null && !languageBinaryPath.isEmpty()) {
                command.add(languageBinaryPath);
            }

            if (decryptionFilePath.endsWith(".jar")) {
                command.add("-jar");
            }

            command.add(decryptionFilePath);
            command.add("-d");
            command.add(tempFilePath);

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
