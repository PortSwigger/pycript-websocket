package com.pycriptsocket;

import javax.swing.*;
import java.awt.*;

import burp.api.montoya.MontoyaApi;

public class MainUI extends JPanel {

    private static MainUI instance;
    private final MontoyaApi api;
    private JTabbedPane tabbedPane;
    private ConfigUI configUI;
    private LogUI logUI;
    private DocumentationUI documentationUI;

    public MainUI(MontoyaApi api) {
        this.api = api;
        instance = this;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        configUI = new ConfigUI(api);
        tabbedPane.addTab("Config", configUI);

        logUI = new LogUI(api);
        tabbedPane.addTab("Log", logUI);

        documentationUI = new DocumentationUI(api);
        tabbedPane.addTab("Documentation", documentationUI);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static MainUI getInstance() {
        return instance;
    }

    public ConfigUI getConfigUI() {
        return configUI;
    }

    public LogUI getLogUI() {
        return logUI;
    }

    public DocumentationUI getDocumentationUI() {
        return documentationUI;
    }


    public String getEncryptionFilePath() {
        return configUI.getEncryptionFilePath();
    }

    public String getDecryptionFilePath() {
        return configUI.getDecryptionFilePath();
    }

    public String getLanguageBinaryPath() {
        return configUI.getLanguageBinaryPath();
    }

    public boolean isStatusOn() {
        return configUI.isStatusOn();
    }
}