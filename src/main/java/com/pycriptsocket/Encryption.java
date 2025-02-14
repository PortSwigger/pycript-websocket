package com.pycriptsocket;

import burp.api.montoya.core.ByteArray;

public class Encryption {

    public ByteArray encryptAndPassToTempFile(ByteArray content) {
        String encryptedContent = content.toString();

        TempFile tempFile = new TempFile();
        String tempFilePath = tempFile.processData(encryptedContent);

        // Get the encryption file path from the UI class
        String encryptionFilePath = UI.getInstance().getEncryptionFilePath();

        // Call the Execution class to run the system command
        Execution execution = new Execution();
        boolean success = execution.runCommand(encryptionFilePath, tempFilePath);

        if (success) {
            // Read the updated content from the temp file
            String updatedContent = tempFile.readFileContent(tempFilePath);
            return ByteArray.byteArray(updatedContent);
        } else {
            // Return the original content if the execution was not successful
            return content;
        }
    }
}
