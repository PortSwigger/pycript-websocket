package com.pycriptsocket;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.ui.Selection;
import burp.api.montoya.ui.editor.EditorOptions;
import burp.api.montoya.ui.editor.RawEditor;
import burp.api.montoya.ui.editor.extension.EditorCreationContext;
import burp.api.montoya.ui.editor.extension.EditorMode;
import burp.api.montoya.ui.editor.extension.ExtensionProvidedWebSocketMessageEditor;
import burp.api.montoya.ui.contextmenu.WebSocketMessage;
import burp.api.montoya.utilities.Base64Utils;
import burp.api.montoya.utilities.URLUtils;

import java.awt.*;

import static burp.api.montoya.core.ByteArray.byteArray;

class WebSocketRequestEditor implements ExtensionProvidedWebSocketMessageEditor
{
    private final RawEditor requestEditor;
    private final Base64Utils base64Utils;
    private final URLUtils urlUtils;
    private final MontoyaApi api;

    WebSocketRequestEditor(MontoyaApi api, EditorCreationContext creationContext)
    {
        this.api = api;
        base64Utils = api.utilities().base64Utils();
        urlUtils = api.utilities().urlUtils();
        Logging logging = api.logging();

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
        // message is edited in the editor, so we return the encrypted contents 
        Encryption encryption = new Encryption();
        ByteArray encryptedContent = encryption.encryptAndPassToTempFile(requestEditor.getContents());
        return encryptedContent;
    }

    @Override
    public void setMessage(WebSocketMessage message) {
        //decrypt the message
        ByteArray content = message.payload();
        Decryption decryption = new Decryption();
        ByteArray updatedContent = decryption.decryptAndPassToTempFile(content);
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