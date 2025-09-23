package com.pycriptsocket;

import com.pycriptsocket.WebSocketEditorProvider;
import com.pycriptsocket.MainUI;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

public class PyCript implements BurpExtension

{
    @Override
    public void initialize(MontoyaApi api)
    {
        api.extension().setName("PyCript WebSocket");

        Logging logging = api.logging();
        logging.logToOutput("Author: Sourav Kalal");
        logging.logToOutput("VERSION: 0.2");
        logging.logToOutput("GitHub - https://github.com/Anof-cyber/PyCript-WebSocket");
        logging.logToOutput("Website - https://souravkalal.tech/");
        api.userInterface().registerSuiteTab("PyCript WebSocket", new MainUI(api));

        api.userInterface().registerWebSocketMessageEditorProvider(new WebSocketEditorProvider(api));


    }


}