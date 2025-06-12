package com.pycriptsocket;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.logging.Logging;

public class EncDec {

    private final TempFile tempFile;
    private final Execution execution;

    public EncDec() {
        this.tempFile = new TempFile();
        this.execution = new Execution();
    }

    public ByteArray process(ByteArray content, boolean isEncryption, Logging logging) {
        
        String tempFilePath = tempFile.processData(content);

        String filePath = isEncryption
                ? UI.getInstance().getEncryptionFilePath()
                : UI.getInstance().getDecryptionFilePath();

        boolean success = execution.runCommand(filePath, tempFilePath, logging);

        if (success) {
            byte[] updatedContent = tempFile.readFileContent(tempFilePath);
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
