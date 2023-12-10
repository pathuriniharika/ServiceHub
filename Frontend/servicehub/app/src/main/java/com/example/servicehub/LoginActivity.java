package com.example.servicehub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private RequestQueue requestQueue;
    private Map<String, String> adminCredentials;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passwordlogin);
        Button loginButton = findViewById(R.id.login_button);

        // Initialize the request queue
        requestQueue = Volley.newRequestQueue(this);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("YourPrefsFile", MODE_PRIVATE);

        loginButton.setOnClickListener(v -> attemptLogin());
        initializeAdminCredentials();
    }

    private void initializeAdminCredentials() {
        adminCredentials = new HashMap<>();
        adminCredentials.put("administrator", "admin");
    }

    private void attemptLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (adminCredentials.containsKey(email) && adminCredentials.get(email).equals(password)) {
            Intent adminIntent = new Intent(LoginActivity.this, admin.class);
            startActivity(adminIntent);
            finish();
            return; // Admin login handled, exit the method.
        }

        // This is your login endpoint
        String loginUrl = "http://coms-309-063.class.las.iastate.edu:8080/login";

        JSONObject credentials = new JSONObject();
        try {
            credentials.put("email", email);
            credentials.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LoginActivity", "Login Response: " + response);

                        if ("Login successful".equals(response.trim())) {
                            fetchUserDetails(email);
                        } else {
                            showSnackbar("Invalid login credentials");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle Volley error here and log it for debugging
                        Log.e("LoginActivity", "Volley Error: " + error.toString());
                        handleVolleyError(error);
                    }
                }) {
            @Override
            public byte[] getBody() {
                return credentials.toString().getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        requestQueue.add(request);
    }

    private void handleVolleyError(VolleyError error) {
        String errorMessage = "An error occurred during login. Please try again.";
        if (error instanceof AuthFailureError) {
            Log.e("LoginActivity", "Authentication Failure");
        } else if (error instanceof NetworkError) {
            Log.e("LoginActivity", "Network Error");
        } else if (error instanceof NoConnectionError) {
            Log.e("LoginActivity", "No Connection Error");
        } else if (error instanceof ParseError) {
            Log.e("LoginActivity", "Parse Error");
        } else if (error instanceof ServerError) {
            Log.e("LoginActivity", "Server Error");
        } else if (error instanceof TimeoutError) {
            Log.e("LoginActivity", "Timeout Error");
        }

        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    private void fetchUserDetails(String email) {
        String userDetailsUrl = "http://coms-309-063.class.las.iastate.edu:8080/getUserDetails?email=" + email;

        JsonObjectRequest userDetailsRequest = new JsonObjectRequest(Request.Method.GET, userDetailsUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("LoginActivity", "User Details Response: " + response);

                        try {
                            int userId = response.getJSONObject("details").getInt("id");
                            String userStatus = response.getJSONObject("details").getString("status");
                            String notification = response.getJSONObject("details").getString("notification");

                            boolean hasNotifications = (notification != null && !notification.equals("no notification is available for the user"));

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("userId", userId);
                            editor.putString("userStatus", userStatus);
                            editor.putBoolean("hasNotifications", hasNotifications);

                            if (hasNotifications) {
                                editor.putString("notification", notification);
                                Log.d("LoginActivity", "Notification found: " + notification);
                            } else {
                                Log.d("LoginActivity", "No notifications found");
                            }

                            editor.apply();


                            if ("SEEKER".equals(userStatus)) {
                                Intent providerIntent = new Intent(LoginActivity.this, loginSuccess.class);
                                startActivity(providerIntent);
                            } else if ("PROVIDER".equals(userStatus)) {
                                Intent consumerIntent = new Intent(LoginActivity.this, ConsumerActivity.class);
                                startActivity(consumerIntent);
                            } else {
                                showSnackbar("Unknown user status");
                            }

                            finish();
                        } catch (JSONException e) {
                            Log.e("LoginActivity", "Error parsing user details JSON");
                            showSnackbar("Error getting user details.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle Volley error here and log it for debugging
                        Log.e("LoginActivity", "Volley Error (User Details): " + error.toString());
                        handleVolleyError(error);
                    }
                });

        requestQueue.add(userDetailsRequest);
    }
    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }



}
