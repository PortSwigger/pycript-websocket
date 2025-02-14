package com.pycriptsocket;

import burp.api.montoya.core.ByteArray;

public class Encryption {

    public ByteArray encryptAndPassToTempFile(ByteArray content) {
        String encryptedContent = content.toString();

        TempFile tempFile = new TempFile();
        String tempFilePath = tempFile.processData(encryptedContent);
        String encryptionFilePath = UI.getInstance().getEncryptionFilePath();

        // Call the Execution class to run the system command
        Execution execution = new Execution();
        boolean success = execution.runCommand(encryptionFilePath, tempFilePath);

        if (success) {
            String updatedContent = tempFile.readFileContent(tempFilePath);
            boolean isDeleted = tempFile.deleteFile(tempFilePath);
            if (isDeleted) {
                System.out.println("Temporary file deleted successfully.");
            } else {
                System.out.println("Failed to delete the temporary file.");
            }
            return ByteArray.byteArray(updatedContent);
        } else {
            return content;
        }
    }
}
