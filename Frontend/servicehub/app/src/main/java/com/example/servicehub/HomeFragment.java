package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class HomeFragment extends Fragment {
    private CardView service1Card;
    private CardView service2Card;
    private CardView service3Card;
    private TextView announcementTextView;
    private WebSocket webSocket;

    private static final String SERVER_URL = "ws://coms-309-063.class.las.iastate.edu:8080/chat/username";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag, container, false);

        service1Card = view.findViewById(R.id.service1Card);
        service2Card = view.findViewById(R.id.service2Card);
        service3Card = view.findViewById(R.id.service3Card);
        announcementTextView = view.findViewById(R.id.announcementTextView);

        initializeWebSocket();

        EditText searchEditText = view.findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterCards(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        service1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeCleaning.class);
                startActivity(intent);
            }
        });

        service2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), request_tutor.class);
                startActivity(intent);
            }
        });

        service3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), carpool_home.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initializeWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(SERVER_URL).build();
        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Log.d("WebSocket", "Received message: " + text);

                // Split the received text by newlines
                String[] messages = text.split("\n");
                // Find the most recent announcement
                for (int i = messages.length - 1; i >= 0; i--) {
                    if (messages[i].contains("[Announcement]")) {
                        final String announcementText = messages[i].substring(messages[i].indexOf("[Announcement]") + "[Announcement]".length()).trim();

                        getActivity().runOnUiThread(() -> {
                            announcementTextView.setText(announcementText);
                            Log.d("WebSocket", "Announcement updated: " + announcementText);
                        });
                        break;
                    }
                }
            }

        });
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webSocket != null) {
            webSocket.close(1000, "Fragment Destroyed");
        }
    }

    private void filterCards(String searchText) {
        String service1Title = "Home Service";
        String service2Title = "Tutoring Service";
        String service3Title = "Car Pooling";

        boolean showService1 = service1Title.toLowerCase().contains(searchText.toLowerCase());
        boolean showService2 = service2Title.toLowerCase().contains(searchText.toLowerCase());
        boolean showService3 = service3Title.toLowerCase().contains(searchText.toLowerCase());

        service1Card.setVisibility(showService1 ? View.VISIBLE : View.GONE);
        service2Card.setVisibility(showService2 ? View.VISIBLE : View.GONE);
        service3Card.setVisibility(showService3 ? View.VISIBLE : View.GONE);
    }
}
