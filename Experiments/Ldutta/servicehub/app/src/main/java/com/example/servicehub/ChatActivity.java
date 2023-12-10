package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.java_websocket.handshake.ServerHandshake;

public class ChatActivity extends AppCompatActivity implements WebSocketListener {

   // private String BASE_URL = "ws://10.0.2.2:8080/chat/";

    private String BASE_URL = "ws://coms-309-063.class.las.iastate.edu:8080/chat/";
    private Button connectBtn, sendBtn, endConversationBtn;
    private EditText usernameEtx, msgEtx;
    private TextView msgTv;

    private boolean isSendingMessage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initializeUI();
    }

    private void initializeUI() {
        connectBtn = findViewById(R.id.connect);
        sendBtn = findViewById(R.id.send);
        endConversationBtn = findViewById(R.id.endConversation);
        usernameEtx = findViewById(R.id.username);
        msgEtx = findViewById(R.id.msg);
        msgTv = findViewById(R.id.msgdisplay);

        connectBtn.setOnClickListener(this::onConnectButtonClick);
        sendBtn.setOnClickListener(this::onSendButtonClick);

        // Add a click listener for the "End Conversation" button
        endConversationBtn.setOnClickListener(this::onEndConversationButtonClick);
    }

    private void onConnectButtonClick(View view) {
        String serverUrl = BASE_URL + usernameEtx.getText().toString();
        WebSocketManager.getInstance().connectWebSocket(serverUrl);
        WebSocketManager.getInstance().setWebSocketListener(this);
    }

    private void onSendButtonClick(View view) {
        if (!isSendingMessage) {
            isSendingMessage = true;
            String message = msgEtx.getText().toString();
            if (!message.isEmpty()) {
                WebSocketManager.getInstance().sendMessage(message);
                msgEtx.setText("");
            }
            isSendingMessage = false;
        }
    }

    private void onEndConversationButtonClick(View view) {
        // Start the HomeCleaningActivity (activity_homecleaning.xml page)
        Intent intent = new Intent(ChatActivity.this, HomeCleaning.class);
        startActivity(intent);
    }

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String currentText = msgTv.getText().toString();
            msgTv.setText(currentText + "\n" + message);
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String currentText = msgTv.getText().toString();
            msgTv.setText(currentText + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketError(Exception ex) {}
}


