package com.example.servicehub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class UpdatePasswordActivity extends AppCompatActivity {

    // Replace this with your actual API endpoint
    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080";

    private EditText newPasswordEditText;
    private EditText confirmPasswordEditText;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        newPasswordEditText = findViewById(R.id.passwordlogin);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);

        Button updatePasswordButton = findViewById(R.id.updatePasswordbtn);

        fetchUserId();

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });
    }

    private void updatePassword() {
        int userId = getSavedUserId();

        if (userId != -1) {
            String newPassword = newPasswordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                if (newPassword.equals(confirmPassword)) {
                    // Passwords match, proceed with update
                    changePassword(userId, newPassword);
                } else {
                    // Passwords do not match, show an error message
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Either newPassword or confirmPassword is empty, show an error message
                Toast.makeText(this, "Enter both new password and confirm password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error: User ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchUserId() {
        userId = getSavedUserId();

        if (userId == -1) {
            // Handle the case where the user ID is not found
            Toast.makeText(UpdatePasswordActivity.this, "Error: User ID not found", Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message when the user ID is successfully fetched
            Toast.makeText(UpdatePasswordActivity.this, "User ID successfully fetched: " + userId, Toast.LENGTH_SHORT).show();
        }
    }

    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }

    /*private void changePassword(int userId, String newPassword) {
        String url = BASE_URL + "/users/" + userId + "/up/" + newPassword;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String message = response.getString("message");
                            Toast.makeText(UpdatePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UpdatePasswordActivity.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("PasswordChangeManager", "Error: " + error.toString());
                        Toast.makeText(UpdatePasswordActivity.this, "Error changing password. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }*/
    private void changePassword(int userId, String newPassword) {
        String url = BASE_URL + "/users/" + userId + "/up/" + newPassword;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");

                            if (success) {
                                // Password updated successfully
                                Toast.makeText(UpdatePasswordActivity.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                // Password update failed, display the error message from the server
                                String errorMessage = response.getString("message");
                                Toast.makeText(UpdatePasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("PasswordChangeManager", "Error: " + error.toString());
                        Toast.makeText(UpdatePasswordActivity.this, "Error changing password. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

}
