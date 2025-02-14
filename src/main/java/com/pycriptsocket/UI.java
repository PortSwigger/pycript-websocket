package com.pycriptsocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import burp.api.montoya.MontoyaApi;

public class UI extends JPanel {

    private static UI instance;
    private final MontoyaApi api;
    private String encryptionFilePath;
    private String decryptionFilePath;
    private String languageBinaryPath;
    private JLabel encryptionFileLabel;
    private JLabel decryptionFileLabel;
    private JTextField languageBinaryTextField;
    private JToggleButton statusToggleButton;

    public UI(MontoyaApi api) {
        this.api = api;
        instance = this; // Set the static reference to this instance
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Encryption file selection
        JLabel encryptionLabel = new JLabel("Select Encryption File:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(encryptionLabel, gbc);

        JButton encryptionButton = new JButton("Browse");
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(encryptionButton, gbc);

        encryptionFileLabel = new JLabel("No file selected");
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(encryptionFileLabel, gbc);

        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    encryptionFilePath = selectedFile.getAbsolutePath();
                    encryptionFileLabel.setText(encryptionFilePath);
                }
            }
        });

        // Decryption file selection
        JLabel decryptionLabel = new JLabel("Select Decryption File:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(decryptionLabel, gbc);

        JButton decryptionButton = new JButton("Browse");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(decryptionButton, gbc);

        decryptionFileLabel = new JLabel("No file selected");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(decryptionFileLabel, gbc);

        decryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    decryptionFilePath = selectedFile.getAbsolutePath();
                    decryptionFileLabel.setText(decryptionFilePath);
                }
            }
        });

        // Language binary path selection
        JLabel languageBinaryLabel = new JLabel("Select Language Binary Path:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(languageBinaryLabel, gbc);

        languageBinaryTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(languageBinaryTextField, gbc);

        JButton languageBinaryButton = new JButton("Browse");
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(languageBinaryButton, gbc);

        languageBinaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    languageBinaryPath = selectedFile.getAbsolutePath();
                    languageBinaryTextField.setText(languageBinaryPath);
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        gbc.gridx = 4;
        gbc.gridy = 2;
        add(clearButton, gbc);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageBinaryPath = "";
                languageBinaryTextField.setText("");
            }
        });

        // Status toggle button
        JLabel statusLabel = new JLabel("Status:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(statusLabel, gbc);

        statusToggleButton = new JToggleButton("OFF");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(statusToggleButton, gbc);

        statusToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusToggleButton.isSelected()) {
                    statusToggleButton.setText("ON");
                } else {
                    statusToggleButton.setText("OFF");
                }
            }
        });
    }

    public static UI getInstance() {
        return instance;
    }

    public String getEncryptionFilePath() {
        return encryptionFilePath;
    }

    public String getDecryptionFilePath() {
        return decryptionFilePath;
    }

    public String getLanguageBinaryPath() {
        return languageBinaryTextField.getText();
    }

    public boolean isStatusOn() {
        return statusToggleButton.isSelected();
    }
}
