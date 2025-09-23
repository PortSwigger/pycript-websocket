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

        String tempFilePath = tempFile.processData(content);

        String filePath = isEncryption
                ? MainUI.getInstance().getEncryptionFilePath()
                : MainUI.getInstance().getDecryptionFilePath();

        boolean success = execution.runCommand(filePath, tempFilePath);

        if (success) {
            byte[] updatedContent = tempFile.readFileContent(tempFilePath);

            if (MainUI.getInstance() != null && MainUI.getInstance().getLogUI().isLoggingEnabled()) {
                MainUI.getInstance().getLogUI().appendLog("$ cat " + tempFilePath);
                String fileContent = new String(updatedContent);
                MainUI.getInstance().getLogUI().appendLog(fileContent);
            }

            boolean isDeleted = tempFile.deleteFile(tempFilePath);
            if (isDeleted) {
                System.out.println("Temporary file deleted successfully.");
            } else {
                System.out.println("Failed to delete the temporary file.");
            }
            return ByteArray.byteArray(updatedContent);
        }
            else {
                    return content;
            }
    }
}
