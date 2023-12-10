package com.example.servicehub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class AnnouncementsActivity extends AppCompatActivity {

    private EditText announcementEditText;
    private Button submitButton;
    private WebSocket webSocket;

    private static final String SERVER_URL = "ws://coms-309-063.class.las.iastate.edu:8080/chat/Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_admin);

        announcementEditText = findViewById(R.id.announcementEditText);
        submitButton = findViewById(R.id.submitButton);

        initializeWebSocket();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String announcement = announcementEditText.getText().toString();
                if (!announcement.isEmpty()) {
                    sendAnnouncementToServer(announcement);
                    announcementEditText.setText("");
                }
            }
        });
    }

    private void initializeWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(SERVER_URL).build();
        WebSocketListener webSocketListener = new WebSocketListener() {
            // WebSocket listener methods
        };

        webSocket = client.newWebSocket(request, webSocketListener);
    }

    private void sendAnnouncementToServer(String announcement) {
        if (webSocket != null) {
            webSocket.send("[Announcement] " + announcement);
        }
        Toast.makeText(AnnouncementsActivity.this, "Message Sent: " + announcement, Toast.LENGTH_SHORT).show();
    }
}
