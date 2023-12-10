package com.example.servicehub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewAccountsActivity extends AppCompatActivity {

    private static final String USERS_API_URL = "http://coms-309-063.class.las.iastate.edu:8080/users";

    private EditText searchEditText;
    private Button searchButton;
    private TextView responseTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_accounts_admin);

        initializeViews();
        setupSearchButtonListener();
        fetchAllUsers();
    }

    private void initializeViews() {
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        responseTextView = findViewById(R.id.responseTextView);
        requestQueue = Volley.newRequestQueue(this);
    }

    private void setupSearchButtonListener() {
        searchButton.setOnClickListener(v -> {
            String searchQuery = searchEditText.getText().toString().trim();
            if (!searchQuery.isEmpty()) {
                performSearch(searchQuery);
            } else {
                fetchAllUsers();
            }
        });
    }

    private void fetchAllUsers() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                USERS_API_URL,
                null,
                this::handleResponse,
                this::handleError
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void performSearch(String searchQuery) {
        // This is a placeholder method, modify according to your backend implementation
        filterUsersByName(searchQuery);
    }

    private void filterUsersByName(String searchQuery) {
        String[] users = responseTextView.getText().toString().split("\n\n");
        StringBuilder filteredUsers = new StringBuilder();

        for (String user : users) {
            if (user.toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredUsers.append(user).append("\n\n");
            }
        }

        responseTextView.setText(filteredUsers.length() > 0 ? filteredUsers.toString() : "No matching users found.");
    }

    private void handleResponse(JSONArray response) {
        try {
            StringBuilder userList = new StringBuilder();
            for (int i = 0; i < response.length(); i++) {
                JSONObject userObject = response.getJSONObject(i);
                userList.append(formatUserDetails(userObject)).append("\n\n");
            }
            responseTextView.setText(userList.length() > 0 ? userList.toString() : "No users found.");
        } catch (Exception e) {
            responseTextView.setText("Error: " + e.getMessage());
        }
    }

    private String formatUserDetails(JSONObject userObject) throws Exception {
        return "User ID: " + userObject.getInt("id") + "\n" +
                "Name: " + userObject.getString("name") + "\n" +
                "Email: " + userObject.getString("email") + "\n" +
                "Status: " + userObject.getString("status");
    }

    private void handleError(VolleyError error) {
        responseTextView.setText("Error: " + error.getMessage());
    }
}
