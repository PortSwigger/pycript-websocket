package com.pycriptsocket;

import burp.api.montoya.core.ByteArray;

public class Decryption {

    public ByteArray decryptAndPassToTempFile(ByteArray content) {
        String decryptedContent = content.toString();

        TempFile tempFile = new TempFile();
        String tempFilePath = tempFile.processData(decryptedContent);

        String decryptionFilePath = UI.getInstance().getDecryptionFilePath();

        Execution execution = new Execution();
        boolean success = execution.runCommand(decryptionFilePath, tempFilePath);

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