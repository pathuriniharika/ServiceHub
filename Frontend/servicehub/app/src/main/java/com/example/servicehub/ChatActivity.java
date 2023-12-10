package com.example.servicehub;
/*
Author: Leha Dutta
*/
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.servicehub.HomeCleaning;
import com.example.servicehub.HomeService;
import com.example.servicehub.WebSocketManager;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity implements WebSocketListener {

    private String BASE_URL = "ws://coms-309-063.class.las.iastate.edu:8080/chat/";
    private Button sendBtn, endConversationBtn, italicButton,connectButton;
    private Button appointmentButton, representativeButton, businessButton;
    private EditText msgEtx;
    private TextView msgTv, charCountTv;

    private boolean isSendingMessage = false;
    private boolean isTyping = false;
    private final Handler typingHandler = new Handler();
    private static final int TYPING_TIMEOUT = 2000; // 2 seconds
    private boolean isItalic = false;
    private int userId;

    private final Runnable stopTypingRunnable = () -> {
        isTyping = false;
        WebSocketManager.getInstance().sendTypingIndicator(false);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initializeUI();

        // WebSocketManager.getInstance().connectWebSocket(serverUrl);

        fetchUserId();
        String serverUrl = BASE_URL + userId;
        WebSocketManager.getInstance().connectWebSocket(serverUrl);
        WebSocketManager.getInstance().setWebSocketListener(this);

    }

    private void initializeUI() {
        sendBtn = findViewById(R.id.send);
        endConversationBtn = findViewById(R.id.endConversation);
        italicButton = findViewById(R.id.italicButton);
        appointmentButton = findViewById(R.id.appointmentButton);
        representativeButton = findViewById(R.id.representativeButton);
        businessButton = findViewById(R.id.businessButton);
        msgEtx = findViewById(R.id.msg);
        msgTv = findViewById(R.id.msgdisplay);
        charCountTv = findViewById(R.id.charCount);
        connectButton=findViewById(R.id.connectButton);


        //connectButton.setOnClickListener(this::onConnectButtonClick);
        sendBtn.setOnClickListener(this::onSendButtonClick);
        endConversationBtn.setOnClickListener(this::onEndConversationButtonClick);
        italicButton.setOnClickListener(v -> toggleItalicText());

        // Set onClick listeners for predefined message buttons
        appointmentButton.setOnClickListener(v -> sendMessageAndDisplay("I want to book a service appointment", Boolean.parseBoolean(String.valueOf(true))));
        representativeButton.setOnClickListener(v -> sendMessageAndDisplay("Can I speak to a representative?", Boolean.parseBoolean(String.valueOf(true))));
        businessButton.setOnClickListener(v -> sendMessageAndDisplay("What are your business hours?", Boolean.parseBoolean(String.valueOf(true))));


        msgEtx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // User is typing, start the typing indicator
                if (!isTyping) {
                    isTyping = true;
                    WebSocketManager.getInstance().sendTypingIndicator(true);
                }
                charCountTv.setText(s.length() + "/1000"); // Update character count
                msgTv.setText("Typing..."); // Display typing indicator
                typingHandler.removeCallbacks(stopTypingRunnable);
                typingHandler.postDelayed(stopTypingRunnable, TYPING_TIMEOUT);
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }

    /*private void onConnectButtonClick(View view) {
        WebSocketManager.getInstance().connectWebSocket(BASE_URL);
    }*/

    private void fetchUserId() {
        userId = getSavedUserId();

        if (userId == -1) {
            // Handle the case where the user ID is not found
            Toast.makeText(ChatActivity.this, "Error: User ID not found", Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message when the user ID is successfully fetched
            Toast.makeText(ChatActivity.this, "User ID successfully fetched: " + userId, Toast.LENGTH_SHORT).show();
        }
    }

    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }

    private void toggleItalicText() {
        isItalic = !isItalic;
        String message = msgEtx.getText().toString();
        if (!message.isEmpty()) {
            int selectionStart = msgEtx.getSelectionStart();
            int selectionEnd = msgEtx.getSelectionEnd();
            SpannableString spannable = new SpannableString(message);
            if (isItalic) {
                // Apply italics style to the selected text
                spannable.setSpan(new StyleSpan(Typeface.ITALIC), selectionStart, selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                // Remove italics style from the selected text
                StyleSpan[] styleSpans = spannable.getSpans(selectionStart, selectionEnd, StyleSpan.class);
                for (StyleSpan styleSpan : styleSpans) {
                    if (styleSpan.getStyle() == Typeface.ITALIC) {
                        spannable.removeSpan(styleSpan);
                    }
                }
            }
            msgEtx.getText().replace(selectionStart, selectionEnd, spannable);
        }
    }

    /*private void onSendButtonClick(View view) {
        String message = msgEtx.getText().toString();
        if (!message.isEmpty()) {
            sendMessageAndDisplay(message, true);
            WebSocketManager.getInstance().sendMessage("user:" + userId + ": " + message);
            msgEtx.setText("");
        }
    }*/

    private void onSendButtonClick(View view) {
        String message = msgEtx.getText().toString();
        if (!message.isEmpty() && WebSocketManager.getInstance().isWebSocketOpen()) {
            WebSocketManager.getInstance().sendMessage("user:" + userId + ": " + message);
            msgEtx.setText("");
        } else {
            Log.e("ChatActivity", "WebSocket not open. Unable to send message.");
        }
    }

       /* if (!message.isEmpty()) {
            JSONObject jsonMessage = new JSONObject();
            try {
                jsonMessage.put("type", "message");
                jsonMessage.put("content", message);
                WebSocketManager.getInstance().sendMessage(jsonMessage.toString());

                // Add the following log statement
                Log.d("ChatActivity", "Sent message to server: " + jsonMessage.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            sendMessageAndDisplay(message, true);
            msgEtx.setText("");
        }*/



    private void onEndConversationButtonClick(View view) {
        try {
            Intent intent = new Intent(ChatActivity.this, HomeCleaning.class);
            startActivity(intent);
            Toast.makeText(this, "Chat Ended", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("ChatActivity", "Intent creation error: " + e.getMessage());
        }
    }




    private void sendMessageAndDisplay(String message, boolean isSent) {
        String timestamp = getTimestamp();
        String displayMessage;

        if (isSent) {
            // If message is sent, display as "user:userid: message"
            displayMessage = "user " + userId + ": " + message + " [" + timestamp + "]";
        } else {
            // If message is received, display as "Server: message"
            displayMessage = "Server :  " + message + " [" + timestamp + "]";
        }

        // Append the message to the TextView
        msgTv.append("\n" + displayMessage);
    }



    private String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        Log.d("ChatActivity", "WebSocket connection opened");
    }

    /*public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String timestamp = getTimestamp();
            String displayMessage;

            try {
                // Parse the JSON message to check for typing indicator
                JSONObject jsonMessage = new JSONObject(message);
                if (jsonMessage.has("type") && jsonMessage.getString("type").equals("typing")) {
                    boolean isTyping = jsonMessage.getBoolean("isTyping");
                    if (isTyping) {
                        if (jsonMessage.has("sender") && jsonMessage.getString("sender").equals("server")) {
                            // Display "Server: Typing..." when the server is typing
                            displayMessage = "Server: Typing...";
                        } else {
                            // Display "You: Typing..." when the client is typing
                            displayMessage = "User id:"+ userId + "Typing...";
                        }
                    } else {
                        // Do not display anything when nobody is typing
                        return;
                    }
                } else {
                    // Handle other types of messages here
                    displayMessage = timestamp + " - " + message;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
                displayMessage = timestamp + " - " + message;
            }

            msgTv.append("\n" + displayMessage);
        });
    }*/
    /*@Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String timestamp = getTimestamp();
            String displayMessage;

            try {
                JSONObject jsonMessage = new JSONObject(message);
                if (jsonMessage.has("type")) {
                    String messageType = jsonMessage.getString("type");
                    if (messageType.equals("typing")) {
                        boolean isTyping = jsonMessage.getBoolean("isTyping");
                        if (isTyping) {
                            displayMessage = "Server: Typing...";
                        } else {
                            // If not typing, do not display anything
                            return;
                        }
                    } else {
                        // Handle other types of messages here
                        displayMessage = timestamp + " - " + message;
                    }
                } else {
                    // If the message doesn't have a "type" field, treat it as a regular message
                    displayMessage = timestamp + " - " + message;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
                displayMessage = timestamp + " - " + message;
            }

            msgTv.append("\n" + displayMessage);
        });
    }*/

    /*@Override
    public void onWebSocketMessage(String message) {*/
        /*runOnUiThread(() -> {
            String timestamp = getTimestamp();
            if (message.startsWith("Message seen: ")) {
                String seenMessage = message.substring("Message seen: ".length());
                msgTv.append("\n" + timestamp + " - " + seenMessage);
            } else if (message.startsWith("{\"type\":\"typing\",\"isTyping\":true}")) {
                msgTv.setText(userId + ": Typing...");
            } else {
                msgTv.append("\n" + timestamp + " - " + message);
            }
        });*/

            /*runOnUiThread(() -> {
                String timestamp = getTimestamp();
                String displayMessage;

                try {
                    JSONObject jsonMessage = new JSONObject(message);
                    if (jsonMessage.has("type")) {
                        String messageType = jsonMessage.getString("type");
                        if (messageType.equals("typing")) {
                            boolean isTyping = jsonMessage.getBoolean("isTyping");
                            if (isTyping) {
                                if (jsonMessage.has("sender") && jsonMessage.getString("sender").equals("server")) {
                                    // Display "Server: Typing..." when the server is typing
                                    displayMessage = "Server: Typing...";
                                } else {
                                    // Display "You: Typing..." when the client is typing
                                    displayMessage = "User id:" + userId + " Typing...";
                                }
                            } else {
                                // If not typing, do not display anything
                                return;
                            }
                        } else {
                            // Handle other types of messages here
                            displayMessage = timestamp + " - " + message;
                        }
                    } else {
                        // If the message doesn't have a "type" field, treat it as a regular message
                        displayMessage = timestamp + " - " + message;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    displayMessage = timestamp + " - " + message;
                }

                msgTv.append("\n" + displayMessage);
            });
        }*/

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            String timestamp = getTimestamp();

            // Check if the message is a typing indicator
            if (message.equals("typing")) {
                msgTv.setText("Server: Typing...");
            } else {
                // Display the received message
                msgTv.append("\n" + timestamp + " - " + message);
            }
        });
    }







    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            msgTv.append("\n---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
        Log.d("ChatActivity", "WebSocket connection closed by " + closedBy + " - Reason: " + reason);
    }

    @Override
    public void onWebSocketError(Exception ex) {
        Log.e("ChatActivity", "WebSocket error: " + ex.getMessage());
    }


}
