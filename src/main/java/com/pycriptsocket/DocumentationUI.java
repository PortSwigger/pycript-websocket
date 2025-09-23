package com.pycriptsocket;

import javax.swing.*;
import java.awt.*;

import burp.api.montoya.MontoyaApi;

public class DocumentationUI extends JPanel {

    private static DocumentationUI instance;
    private final MontoyaApi api;

    public DocumentationUI(MontoyaApi api) {
        this.api = api;
        instance = this;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(43, 43, 43));

        JEditorPane documentationArea = new JEditorPane();
        documentationArea.setContentType("text/html");
        documentationArea.setEditable(false);
        documentationArea.setBackground(new Color(43, 43, 43));
        documentationArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String documentation = getDocumentationContent();
        documentationArea.setText(documentation);

        JScrollPane scrollPane = new JScrollPane(documentationArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(new Color(43, 43, 43));
        scrollPane.setBackground(new Color(43, 43, 43));

        add(scrollPane, BorderLayout.CENTER);
    }

    private String getDocumentationContent() {
        return "<html><head><style>" +
                "body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #2b2b2b; color: #bbbbbb; margin: 0; padding: 20px; }" +
                "h1 { color: #ffffff; text-align: center; border-bottom: 2px solid #555; padding-bottom: 10px; }" +
                "h2 { color: #4fc3f7; border-bottom: 1px solid #444; padding-bottom: 5px; margin-top: 30px; }" +
                "h3 { color: #81c784; margin-top: 25px; }" +
                "ul { margin-left: 20px; }" +
                "li { margin-bottom: 5px; }" +
                "pre { background-color: #1e1e1e; border: 1px solid #444; border-radius: 5px; padding: 15px; margin: 10px 0; overflow-x: auto; }" +
                "code { font-family: 'Consolas', 'Monaco', monospace; color: #dcdcaa; }" +
                ".python-code { color: #dcdcaa; }" +
                ".javascript-code { color: #d4d4aa; }" +
                ".comment { color: #6a9955; font-style: italic; }" +
                ".keyword { color: #569cd6; font-weight: bold; }" +
                ".string { color: #ce9178; }" +
                ".function { color: #dcdcaa; }" +
                ".note { background-color: #3a3a00; border-left: 4px solid #ffeb3b; padding: 10px; margin: 10px 0; }" +
                ".warning { background-color: #3a1a00; border-left: 4px solid #ff9800; padding: 10px; margin: 10px 0; }" +
                "</style></head><body>" +

                "<h2>OVERVIEW</h2>" +
                "<p>PyCript WebSocket requires user-provided encryption and decryption scripts. Scripts can be from any language including:</p>" +
                "<ul>" +
                "<li><strong>Python</strong></li>" +
                "<li><strong>PowerShell</strong></li>" +
                "<li><strong>JavaScript (Node.js)</strong></li>" +
                "<li><strong>Java</strong></li>" +
                "<li><strong>Rust</strong></li>" +
                "<li><strong>Go</strong></li>" +
                "<li><strong>Swift</strong></li>" +
                "<li><strong>C/C++</strong></li>" +
                "<li><strong>Bash</strong></li>" +
                "<li><strong>And many more...</strong></li>" +
                "</ul>" +
                "<p>As long as the code and script use the required format as per PyCript WebSocket specifications.</p>" +

                "<h2>HOW PYCRIPT WEBSOCKET WORKS</h2>" +
                "<ol>" +
                "<li>PyCript WebSocket takes the WebSocket message data and creates a temporary file in the OS</li>" +
                "<li>Saves the WebSocket data in the temporary file</li>" +
                "<li>Runs your encryption/decryption script using a command like:</li>" +
                "</ol>" +
                "<pre><code>node /home/user/encryption.js -d /tmp/tempfile.txt</code></pre>" +
                "<p>This is one example of a command executable by PyCript WebSocket.</p>" +

                "<h2>📝 SCRIPT REQUIREMENTS</h2>" +
                "<p>PyCript expects your encryption and decryption scripts to:</p>" +
                "<ul>" +
                "<li>Have command line argument support</li>" +
                "<li>Read the <code>'-d'</code> value which is the location of the temp file with encrypted or plain text WebSocket message data</li>" +
                "<li>Take the <code>'-d'</code> value to get the full path of the temp file</li>" +
                "<li>Read the temp file to get WebSocket message data of the selected WebSocket message in Burp Suite</li>" +
                "<li>Process the data to encrypt/decrypt/encode/decode or perform any other needed operations</li>" +
                "<li>Write the updated data to the SAME temp file received from <code>'-d'</code></li>" +
                "<li>OVERWRITE the file with updated data (do NOT append to existing data)</li>" +
                "<li>Ensure old data is removed and only new updated data remains</li>" +
                "</ul>" +
                "<p>After script execution, PyCript WebSocket reads the updated data from the same temp file to show the processed encrypted or decrypted data.</p>" +

                "<div class='note'>" +
                "<h2>USAGE NOTES</h2>" +
                "<ul>" +
                "<li><strong>Use the same script logic for both encryption and decryption</li>" +
                "<li><strong>Create separate files: encryption.py/encryption.js and decryption.py/decryption.js</li>" +
                "<li><strong>Make sure scripts are executable and have proper permissions</li>" +
                "<li><strong>Test your scripts independently before using with PyCript WebSocket</li>" +
                "<li><strong>Always use the <code>'-d'</code> parameter to get the temp file path</li>" +
                "<li><strong>Always overwrite the temp file, never append to it</li>" +
                "<li><strong>Use the LOG TAB to monitor script execution, view output, and debug errors</strong></li>" +
                "<li><strong>Enable logging in the Log tab to see real-time script execution details</strong></li>" +
                "</ul>" +
                "</div>" +

                "<h2>EXAMPLE SCRIPTS</h2>" +

                "<h3>PYTHON EXAMPLE (encryption.py & decryption.py)</h3>" +
                "<pre class='python-code'><code>" +
                "<span class='comment'>#!/usr/bin/env python3</span><br>" +
                "<span class='keyword'>import</span> argparse<br>" +
                "<br>" +
                "<span class='keyword'>def</span> <span class='function'>encrypt_decrypt_data</span>(data):<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'># UPDATE THIS FUNCTION ACCORDING TO YOUR REQUIREMENTS</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'># Replace with your actual encryption/decryption logic</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='keyword'>import</span> base64<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='keyword'>return</span> base64.b64encode(data.encode()).decode()&nbsp;&nbsp;<span class='comment'># Simple example</span><br>" +
                "<br>" +
                "<span class='keyword'>def</span> <span class='function'>main</span>():<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;parser = argparse.ArgumentParser()<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;parser.add_argument(<span class='string'>'-d'</span>, <span class='string'>'--data-file'</span>, required=<span class='keyword'>True</span>)<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;args = parser.parse_args()<br>" +
                "<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'># Read data from temp file</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='keyword'>with</span> open(args.data_file, <span class='string'>'r'</span>) <span class='keyword'>as</span> f:<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data = f.read()<br>" +
                "<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'># Call encryption/decryption function</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;updated_data = encrypt_decrypt_data(data)<br>" +
                "<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'># Overwrite same temp file with updated data</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='keyword'>with</span> open(args.data_file, <span class='string'>'w'</span>) <span class='keyword'>as</span> f:<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;f.write(updated_data)<br>" +
                "<br>" +
                "<span class='keyword'>if</span> __name__ == <span class='string'>'__main__'</span>:<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;main()" +
                "</code></pre>" +

                "<h3>NODE.JS EXAMPLE (encryption.js & decryption.js)</h3>" +
                "<pre class='javascript-code'><code>" +
                "<span class='keyword'>const</span> fs = <span class='function'>require</span>(<span class='string'>'fs'</span>);<br>" +
                "<span class='keyword'>const</span> { program } = <span class='function'>require</span>(<span class='string'>'commander'</span>);<br>" +
                "<br>" +
                "<span class='keyword'>function</span> <span class='function'>encryptDecryptData</span>(data) {<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'>// UPDATE THIS FUNCTION ACCORDING TO YOUR REQUIREMENTS</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='comment'>// Replace with your actual encryption/decryption logic</span><br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class='keyword'>return</span> Buffer.from(data).toString(<span class='string'>'base64'</span>);&nbsp;&nbsp;<span class='comment'>// Simple example</span><br>" +
                "}<br>" +
                "<br>" +
                "<span class='comment'>// Setup command line argument parsing with Commander</span><br>" +
                "program<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;.requiredOption(<span class='string'>'-d, --data-file &lt;path&gt;'</span>, <span class='string'>'Path to temp file'</span>)<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;.parse();<br>" +
                "<br>" +
                "<span class='keyword'>const</span> options = program.opts();<br>" +
                "<br>" +
                "<span class='comment'>// Read data from temp file</span><br>" +
                "<span class='keyword'>const</span> data = fs.readFileSync(options.dataFile, <span class='string'>'utf8'</span>);<br>" +
                "<br>" +
                "<span class='comment'>// Call encryption/decryption function</span><br>" +
                "<span class='keyword'>const</span> updatedData = encryptDecryptData(data);<br>" +
                "<br>" +
                "<span class='comment'>// Overwrite same temp file with updated data</span><br>" +
                "fs.writeFileSync(options.dataFile, updatedData);" +
                "</code></pre>" +

                "<p>For more complex encryption/decryption, replace the base64 operations with your custom logic.</p>" +

                "<div class='warning'>" +
                "<h2>🔧 TROUBLESHOOTING</h2>" +
                "<ul>" +
                "<li><strong>LOG TAB</strong> for detailed script execution information</li>" +
                "<li>Enable logging to see real-time output and error messages</li>" +
                "<li><strong>Verify script permissions and executable paths</li>" +
                "<li>Test scripts manually with temp files before using in Burp Suite</li>" +
                "</ul>" +
                "</div>" +

                "</body></html>";
    }

    public static DocumentationUI getInstance() {
        return instance;
    }
}