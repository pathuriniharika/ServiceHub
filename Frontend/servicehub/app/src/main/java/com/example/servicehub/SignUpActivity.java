package com.example.servicehub;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Spinner userTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Volley RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Find the EditText fields by their IDs
        nameEditText = findViewById(R.id.textPersonName);
        emailEditText = findViewById(R.id.textEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.passwordConfirm);

        Button createAccountButton = findViewById(R.id.createaccount);

        // Find the Spinner for User Type by its ID
        userTypeSpinner = findViewById(R.id.userTypeSpinner);

        // Define the URL for the sign-up endpoint (replace with your actual URL)
        String signUpUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

        // Set an OnClickListener for the "Create Account" button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Get selected user type from Spinner
                String userType = userTypeSpinner.getSelectedItem().toString();

                // Create a JSON object with user data
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("name", name);
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                    jsonBody.put("confirmPassword", confirmPassword);
                    jsonBody.put("status", userType); // Add user type to the JSON data
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a JsonObjectRequest for POST
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, signUpUrl, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the sign-up response here
                                try {
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        // Account created successfully, display a toast
                                        Toast.makeText(SignUpActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Account creation failed, handle the error (if needed)
                                        Toast.makeText(SignUpActivity.this, "Account creation failed: " + response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(SignUpActivity.this, "Account Successfully created", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Log.e("SignUpError", "Error: " + error.toString());
                        Toast.makeText(SignUpActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the request queue
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
