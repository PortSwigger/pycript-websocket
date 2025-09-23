package com.pycriptsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Execution {

    public boolean runCommand(String decryptionFilePath, String tempFilePath) {
        try {
            String languageBinaryPath = MainUI.getInstance().getLanguageBinaryPath();
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
            if (MainUI.getInstance() != null && MainUI.getInstance().getLogUI().isLoggingEnabled()) {
                MainUI.getInstance().getLogUI().appendLog("$ " + String.join(" ", command));
            }
            Process process = processBuilder.start();

            // Read and log stdout
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (MainUI.getInstance() != null && MainUI.getInstance().getLogUI().isLoggingEnabled()) {
                    MainUI.getInstance().getLogUI().appendLog(line);
                }
            }

            // Read and log stderr
            BufferedReader errReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
            while ((line = errReader.readLine()) != null) {
                if (MainUI.getInstance() != null && MainUI.getInstance().getLogUI().isLoggingEnabled()) {
                    MainUI.getInstance().getLogUI().appendLog("ERROR: " + line);
                }
            }
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
