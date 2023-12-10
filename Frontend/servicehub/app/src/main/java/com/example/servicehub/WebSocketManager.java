package com.example.servicehub;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketManager {

    private static WebSocketManager instance;
    private MyWebSocketClient webSocketClient;
    private WebSocketListener webSocketListener;
    private WebSocketClient webSocket;

    private WebSocketManager() {}

    public static synchronized WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    public void setWebSocketListener(WebSocketListener listener) {
        this.webSocketListener = listener;
    }

    public void removeWebSocketListener() {
        this.webSocketListener = null;
    }

    /*public void connectWebSocket(String serverUrl) {
        try {
            URI serverUri = URI.create(serverUrl);
            webSocketClient = new MyWebSocketClient(serverUri);
            webSocketClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void connectWebSocket(String serverUrl) {
        /*try {
            URI serverUri = URI.create(serverUrl);
            webSocketClient = new MyWebSocketClient(serverUri);
            webSocketClient.connect();
        } catch (Exception e) {
            Log.e("WebSocketManager", "Error connecting to WebSocket: " + e.getMessage());
            e.printStackTrace();
        }*/
        URI uri;
        try {
            uri = new URI(serverUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocket = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {
                Log.d("WebSocket", "Received message: " + message);
                if (webSocketListener != null) {
                    webSocketListener.onWebSocketMessage(message);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
            // Implement WebSocketClient methods as needed
            // ...
        };

        webSocket.connect();

    }

    /*public void sendMessage(String message) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(message);
        }
    }*/
    public void sendMessage(String message) {
        if (webSocket != null && webSocket.isOpen()) {
            Log.d("WebSocket", "Sending message: " + message);
            webSocket.send(message);
        } else {
            Log.e("WebSocket", "WebSocket not open");
        }
    }


    public void sendTypingIndicator(boolean isTyping) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            try {
                JSONObject typingData = new JSONObject();
                typingData.put("type", "typing");
                typingData.put("isTyping", isTyping);
                webSocketClient.send(typingData.toString());
            } catch (JSONException e) {
                Log.e("WebSocketManager", "Error sending typing indicator to server: " + e.getMessage());
            }
        }
    }

    public void sendUserRating(float userRating, double userDoubleRating) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            try {
                JSONObject ratingData = new JSONObject();
                ratingData.put("type", "user_rating");
                ratingData.put("rating", userRating);
                webSocketClient.send(ratingData.toString());
            } catch (JSONException e) {
                Log.e("WebSocketManager", "Error sending user rating to server: " + e.getMessage());
            }
        }
    }

    public void disconnectWebSocket() {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    public boolean isWebSocketOpen() {
        return webSocket != null && webSocket.isOpen();
    }

    private class MyWebSocketClient extends WebSocketClient {
        private MyWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            Log.d("WebSocket", "Connected");
            if (webSocketListener != null) {
                webSocketListener.onWebSocketOpen(handshakedata);
            }
        }

        @Override
        public void onMessage(String message) {
            Log.d("WebSocket", "Received message: " + message);
            if (webSocketListener != null) {
                webSocketListener.onWebSocketMessage(message);
            }
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            Log.d("WebSocket", "Closed");
            if (webSocketListener != null) {
                webSocketListener.onWebSocketClose(code, reason, remote);
            }
        }

        @Override
        public void onError(Exception ex) {
            Log.d("WebSocket", "Error");
            if (webSocketListener != null) {
                webSocketListener.onWebSocketError(ex);
            }
        }
    }
}
