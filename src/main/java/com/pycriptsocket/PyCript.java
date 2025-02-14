package com.pycriptsocket;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.pycriptsocket.WebSocketEditorProvider;
import com.pycriptsocket.UI;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.ui.UserInterface;
import java.awt.BorderLayout;

public class PyCript implements BurpExtension

{
    @Override
    public void initialize(MontoyaApi api)
    {
        // set extension name
        api.extension().setName("PyCript WebSocket");

        Logging logging = api.logging();
        logging.logToOutput("Author: Sourav Kalal");
        logging.logToOutput("VERSION: 0.1");
        logging.logToOutput("GitHub - https://github.com/Anof-cyber/PyCript-WebSocket");
        logging.logToOutput("Website - https://souravkalal.tech/");
        logging.logToOutput("Documentation - https://pycript.souravkalal.tech/");
        api.userInterface().registerSuiteTab("PyCript WebSocket", new UI(api));

        api.userInterface().registerWebSocketMessageEditorProvider(new WebSocketEditorProvider(api));

       
    }

    
}