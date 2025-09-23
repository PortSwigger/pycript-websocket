package com.pycriptsocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import burp.api.montoya.MontoyaApi;

public class ConfigUI extends JPanel {

    private static ConfigUI instance;
    private final MontoyaApi api;
    private String encryptionFilePath;
    private String decryptionFilePath;
    private String languageBinaryPath;
    private JLabel encryptionFileLabel;
    private JLabel decryptionFileLabel;
    private JTextField languageBinaryTextField;
    private JToggleButton statusToggleButton;

    public ConfigUI(MontoyaApi api) {
        this.api = api;
        instance = this;
        setLayout(new BorderLayout());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setMaximumSize(new Dimension(600, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel statusLabel = new JLabel("Extension Status:");
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(statusLabel, gbc);

        statusToggleButton = new JToggleButton("OFF");
        statusToggleButton.setPreferredSize(new Dimension(70, 25));
        statusToggleButton.setFont(statusToggleButton.getFont().deriveFont(Font.BOLD, 12f));
        statusToggleButton.setBackground(Color.RED);
        statusToggleButton.setForeground(Color.WHITE);
        statusToggleButton.setOpaque(true);
        statusToggleButton.setBorderPainted(false);
        statusToggleButton.setFocusPainted(false);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(statusToggleButton, gbc);

        JLabel encryptionLabel = new JLabel("Select Encryption File:");
        encryptionLabel.setFont(encryptionLabel.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(encryptionLabel, gbc);

        JButton encryptionButton = new JButton("Browse");
        encryptionButton.setPreferredSize(new Dimension(80, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(encryptionButton, gbc);

        encryptionFileLabel = new JLabel("No file selected");
        encryptionFileLabel.setForeground(Color.GRAY);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        mainPanel.add(encryptionFileLabel, gbc);

        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    encryptionFilePath = selectedFile.getAbsolutePath();
                    encryptionFileLabel.setText(encryptionFilePath);
                    encryptionFileLabel.setForeground(Color.BLACK);
                }
            }
        });

        JLabel decryptionLabel = new JLabel("Select Decryption File:");
        decryptionLabel.setFont(decryptionLabel.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(decryptionLabel, gbc);

        JButton decryptionButton = new JButton("Browse");
        decryptionButton.setPreferredSize(new Dimension(80, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(decryptionButton, gbc);

        decryptionFileLabel = new JLabel("No file selected");
        decryptionFileLabel.setForeground(Color.GRAY);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        mainPanel.add(decryptionFileLabel, gbc);

        decryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    decryptionFilePath = selectedFile.getAbsolutePath();
                    decryptionFileLabel.setText(decryptionFilePath);
                    decryptionFileLabel.setForeground(Color.BLACK);
                }
            }
        });


        JLabel languageBinaryLabel = new JLabel("Select Language Binary Path:");
        languageBinaryLabel.setFont(languageBinaryLabel.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        mainPanel.add(languageBinaryLabel, gbc);

        languageBinaryTextField = new JTextField();
        languageBinaryTextField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        mainPanel.add(languageBinaryTextField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton languageBinaryButton = new JButton("Browse");
        languageBinaryButton.setPreferredSize(new Dimension(80, 25));

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(60, 25));

        buttonPanel.add(languageBinaryButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        mainPanel.add(buttonPanel, gbc);


        JPanel guidePanel = new JPanel();
        guidePanel.setLayout(new BoxLayout(guidePanel, BoxLayout.Y_AXIS));
        guidePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Language Binary Path Guide"));

        String[] guideLines = {
            "• Scripting Languages (Python, JavaScript, Ruby, PHP, etc.):",
            "  Python: /usr/bin/python or C:/python312/python.exe",
            "  JavaScript: /usr/bin/node  or C:/Program Files/nodejs/node.exe",
            "  Ruby: /usr/bin/ruby or C:/Ruby32/bin/ruby.exe",
            "  PHP: /usr/bin/php or C:/php/php.exe",
            "",
            "• Compiled Binaries (C, C++, Rust, Go, etc.):",
            "  Keep language path empty",
            "",
            "• Shell Scripts (Bash, PowerShell, Zsh, etc.):",
            "  Bash: bash  or /usr/bin/bash",
            "  PowerShell: powershell.exe -File",
            "  Zsh: zsh, /usr/bin/zsh",
            "",
            "• Java Applications (JAR files only):",
            "  Java: java, /usr/bin/java or C:/Program Files/Java/bin/java.exe",
            "  Note: JAR files required (Class files not supported)",
            "",
            "• Other Languages (Perl, Lua, etc.):",
            "  Follow same pattern as scripting languages above"
        };

        for (String line : guideLines) {
            JLabel guideLine = new JLabel(line);
            if (line.startsWith("•")) {
                guideLine.setFont(guideLine.getFont().deriveFont(Font.BOLD));
                guideLine.setForeground(Color.WHITE);
            } else if (line.trim().isEmpty()) {
                guideLine.setText(" ");
            } else {
                guideLine.setFont(guideLine.getFont().deriveFont(Font.PLAIN));
                guideLine.setForeground(Color.LIGHT_GRAY);
            }
            guideLine.setAlignmentX(Component.LEFT_ALIGNMENT);
            guidePanel.add(guideLine);
        }

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(guidePanel, gbc);

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

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageBinaryPath = "";
                languageBinaryTextField.setText("");
            }
        });


        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containerPanel.add(mainPanel);
        add(containerPanel, BorderLayout.NORTH);



        statusToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (encryptionFilePath != null && !encryptionFilePath.isEmpty() &&
                    decryptionFilePath != null && !decryptionFilePath.isEmpty()) {
                    if (statusToggleButton.isSelected()) {
                        statusToggleButton.setText("ON");
                        statusToggleButton.setBackground(Color.GREEN);
                        statusToggleButton.setForeground(Color.WHITE);
                    } else {
                        statusToggleButton.setText("OFF");
                        statusToggleButton.setBackground(Color.RED);
                        statusToggleButton.setForeground(Color.WHITE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select both encryption and decryption files before turning on the status.");
                    statusToggleButton.setSelected(false);
                    statusToggleButton.setText("OFF");
                    statusToggleButton.setBackground(Color.RED);
                    statusToggleButton.setForeground(Color.WHITE);
                }
            }
        });
    }

    public static ConfigUI getInstance() {
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
