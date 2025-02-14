package com.pycriptsocket;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.ui.editor.extension.EditorCreationContext;
import burp.api.montoya.ui.editor.extension.ExtensionProvidedWebSocketMessageEditor;
import burp.api.montoya.ui.editor.extension.WebSocketMessageEditorProvider;
import com.pycriptsocket.UI;
class WebSocketEditorProvider implements WebSocketMessageEditorProvider
{
    private final MontoyaApi api;

    WebSocketEditorProvider(MontoyaApi api)
    {
        this.api = api;

       
    }

    @Override
    public ExtensionProvidedWebSocketMessageEditor provideMessageEditor(EditorCreationContext creationContext)
    {
        return new WebSocketRequestEditor(api, creationContext);
    }

}
