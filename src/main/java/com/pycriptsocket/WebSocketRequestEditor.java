package com.pycriptsocket;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.ui.editor.EditorOptions;
import burp.api.montoya.ui.editor.RawEditor;
import burp.api.montoya.ui.editor.extension.EditorCreationContext;
import burp.api.montoya.ui.editor.extension.EditorMode;
import burp.api.montoya.ui.editor.extension.ExtensionProvidedWebSocketMessageEditor;
import burp.api.montoya.ui.contextmenu.WebSocketMessage;
import burp.api.montoya.logging.Logging;

import java.awt.*;

class WebSocketRequestEditor implements ExtensionProvidedWebSocketMessageEditor
{
    private final RawEditor requestEditor;
    private final EncDec encDec = new EncDec();
    private final Logging logging;

    WebSocketRequestEditor(MontoyaApi api, EditorCreationContext creationContext)
    {
        logging = api.logging();
        if (creationContext.editorMode() == EditorMode.READ_ONLY)
        {
            requestEditor = api.userInterface().createRawEditor(EditorOptions.READ_ONLY);
        }
        else {
            requestEditor = api.userInterface().createRawEditor();
        }
    }

    @Override
    public ByteArray getMessage() {
        // Use the same instance of EncDec
        ByteArray encryptedContent = encDec.process(requestEditor.getContents(), true, logging);
        return encryptedContent;
    }

    @Override
    public void setMessage(WebSocketMessage message) {
        // Use the same instance of EncDec
        ByteArray content = message.payload();
        ByteArray updatedContent = encDec.process(content, false, logging);
        requestEditor.setContents(updatedContent);
    }

    @Override
    public boolean isEnabledFor(WebSocketMessage message) {
        return UI.getInstance().isStatusOn(); // Access the UI instance and call isStatusOn
    }

    @Override
    public String caption() {
        return "PyCript WebSocket";
    }

    @Override
    public Component uiComponent() {
        return requestEditor.uiComponent();
    }

    @Override
    public Selection selectedData() {
        return requestEditor.selection().orElse(null);
    }

    @Override
    public boolean isModified() {
        return requestEditor.isModified();
    }
}
