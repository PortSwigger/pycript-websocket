package com.pycriptsocket;

import burp.api.montoya.core.ByteArray;

public class EncDec {

    private final TempFile tempFile;
    private final Execution execution;

    public EncDec() {
        this.tempFile = new TempFile();
        this.execution = new Execution();
    }

    public ByteArray process(ByteArray content, boolean isEncryption) {
        String processedContent = content.toString();

        String tempFilePath = tempFile.processData(processedContent);

        String filePath = isEncryption
                ? UI.getInstance().getEncryptionFilePath()
                : UI.getInstance().getDecryptionFilePath();

        boolean success = execution.runCommand(filePath, tempFilePath);

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
