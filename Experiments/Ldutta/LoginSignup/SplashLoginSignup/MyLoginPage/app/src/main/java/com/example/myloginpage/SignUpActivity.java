package com.example.myloginpage;

 /*import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley; */

/*public class SignUpActivity extends AppCompatActivity {

    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Volley RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Find the response TextView by its ID
        responseTextView = findViewById(R.id.responseTextView);

        // Find the "Create Account" button by its ID
        Button createAccountButton = findViewById(R.id.createaccount);

        // Define the URL for the sign-up endpoint (replace with your actual URL)
        String signUpUrl = "https://c10099fd-377d-46d4-8d6b-dd8c7c786349.mock.pstmn.io";

        // Set an OnClickListener for the "Create Account" button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a POST request using StringRequest
                StringRequest stringRequest = new StringRequest(Request.Method.POST, signUpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the sign-up response here
                                Log.d("SignUpResponse", "Response: " + response);
                                // Update the response TextView with the server's response
                                responseTextView.setText("Server Response: " + response);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Log.e("SignUpError", "Error: " + error.toString());
                        // Update the response TextView with the error message
                        responseTextView.setText("Error: " + error.toString());
                    }
                });

                // Add the request to the request queue
                requestQueue.add(stringRequest);
            }
        });
    }
} */

// ...
/*public class SignUpActivity extends AppCompatActivity {

    private TextView responseTextView;
    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Volley RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Find the response TextView by its ID
        responseTextView = findViewById(R.id.responseTextView);

        // Find the EditText fields by their IDs
        nameEditText = findViewById(R.id.textPersonName);
        emailEditText = findViewById(R.id.textEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.passwordConfirm);

        // Find the "Create Account" button by its ID
        Button createAccountButton = findViewById(R.id.createaccount);

        // Define the URL for the sign-up endpoint (replace with your actual URL)
        //String signUpUrl = "https://c10099fd-377d-46d4-8d6b-dd8c7c786349.mock.pstmn.io"; // Replace with your API URL
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

                // Create a JSON object with user data
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("name", name);
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                    jsonBody.put("confirmPassword", confirmPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a JsonObjectRequest for POST
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, signUpUrl, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the sign-up response here
                                Log.d("SignUpResponse", "Response: " + response.toString());
                                // Update the response TextView with the server's response
                                responseTextView.setText("Server Response: " + response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Log.e("SignUpError", "Error: " + error.toString());
                        // Update the response TextView with the error message
                        responseTextView.setText("Error: " + error.toString());
                    }
                });

                // Add the request to the request queue
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
} */

/*import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private TextView responseTextView;
    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Volley RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Find the response TextView by its ID
        responseTextView = findViewById(R.id.responseTextView);

        // Find the EditText fields by their IDs
        nameEditText = findViewById(R.id.textPersonName);
        emailEditText = findViewById(R.id.textEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.passwordConfirm);

        // Find the "Create Account" button by its ID
        Button createAccountButton = findViewById(R.id.createaccount);

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

                // Create a JSON object with user data
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("name", name);
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                    jsonBody.put("confirmPassword", confirmPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a JsonObjectRequest for POST
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, signUpUrl, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the sign-up response here
                                Log.d("SignUpResponse", "Response: " + response.toString());
                                try {
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        // Account created successfully, display a toast
                                        Toast.makeText(SignUpActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                                        // Optionally, you can also clear the input fields or perform other actions
                                    } else {
                                        // Account creation failed, handle the error (if needed)
                                        // You can display an error message in the responseTextView or take other actions
                                        responseTextView.setText("Account creation failed: " + response.getString("message"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    // Handle JSON parsing error
                                    responseTextView.setText("Error: " + e.toString());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Log.e("SignUpError", "Error: " + error.toString());
                        // Update the response TextView with the error message
                        responseTextView.setText("Error: " + error.toString());
                    }
                });

                // Add the request to the request queue
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}*/

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

        // Find the "Create Account" button by its ID
        Button createAccountButton = findViewById(R.id.createaccount);

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

                // Create a JSON object with user data
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("name", name);
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                    jsonBody.put("confirmPassword", confirmPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a JsonObjectRequest for POST
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, signUpUrl, jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the sign-up response here
                              //  Log.d("SignUpResponse", "Response: " + response.toString());
                                try {
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        // Account created successfully, display a toast
                                        Toast.makeText(SignUpActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                                        // Optionally, you can also clear the input fields or perform other actions
                                    } else {
                                        // Account creation failed, handle the error (if needed)
                                        // You can display an error message in the responseTextView or take other actions
                                        // responseTextView.setText("Account creation failed: " + response.getString("message"));
                                        // Optionally, display an error toast message
                                        Toast.makeText(SignUpActivity.this, "Account creation failed: " + response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    // Handle JSON parsing error
                                    // responseTextView.setText("Error: " + e.toString());
                                    // Optionally, display an error toast message
                                    Toast.makeText(SignUpActivity.this, "Account Successfully created", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        Log.e("SignUpError", "Error: " + error.toString());
                        // Update the response TextView with the error message
                        // responseTextView.setText("Error: " + error.toString());
                        // Optionally, display an error toast message
                        Toast.makeText(SignUpActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the request queue
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}



