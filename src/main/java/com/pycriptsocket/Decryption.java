package com.pycriptsocket;

import burp.api.montoya.core.ByteArray;

public class Decryption {

    public ByteArray decryptAndPassToTempFile(ByteArray content) {
        String decryptedContent = content.toString();

        TempFile tempFile = new TempFile();
        String tempFilePath = tempFile.processData(decryptedContent);

        // Get the decryption file path from the UI class
        String decryptionFilePath = UI.getInstance().getDecryptionFilePath();

        // Call the Execution class to run the system command
        Execution execution = new Execution();
        boolean success = execution.runCommand(decryptionFilePath, tempFilePath);

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