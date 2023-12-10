package com.example.myloginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView emailTextView;
    private Button logoutButton;
    private boolean userLoggedOut = false; // Flag to track if the user has logged out

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameTextView = findViewById(R.id.textView);
        emailTextView = findViewById(R.id.textView2);
        logoutButton = findViewById(R.id.logoutButton);

        // Retrieve the user's name and email from the intent extras
        String userName = getIntent().getStringExtra("userName");
        String userEmail = getIntent().getStringExtra("userEmail");

        // Display the user's name and email
        nameTextView.setText("Hello, " + userName); // Display a greeting with the username
        emailTextView.setText("Email: " + userEmail);

        // Set an OnClickListener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the userLoggedOut flag to true when the user logs out
                userLoggedOut = true;

                // Perform logout actions here, if needed

                // Create an intent to navigate to MainActivity
                Intent intent = new Intent(UserActivity.this, MainActivity.class);

                // Clear the back stack, so the user cannot go back to UserActivity after logout
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Start the MainActivity
                startActivity(intent);
            }
        });

        // Fetch additional user data from the URL
        fetchUserDataFromURL();
    }

    private void fetchUserDataFromURL() {
        // Define the URL to retrieve additional user data
        String userDataUrl = ""; // Replace with your actual URL

        // Create a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                userDataUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Check if the user has logged out before updating the UI
                            if (!userLoggedOut) {
                                // Parse the JSON response to extract additional user data
                                String additionalData = response.getString("additionalData");

                                // Update the UI with the additional user data
                                // For example, you can display it in a TextView
                                TextView additionalDataTextView = findViewById(R.id.textView3);
                                additionalDataTextView.setText("Additional Data: " + additionalData);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (!userLoggedOut) {
                                Toast.makeText(UserActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Check if the user has logged out before showing the error message
                        //if (!userLoggedOut) {
                            // Handle errors here
                           // error.printStackTrace();
                           // Toast.makeText(UserActivity.this, "Error fetching user data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        //}
                    }
                }
        );

        // Add the request to the RequestQueue
        requestQueue.add(request);
    }
}










