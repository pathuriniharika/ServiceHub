package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private String loginUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passwordlogin);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get email and password from EditText fields
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                // Create a JSON object with email and password
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("email", email);
                    requestBody.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Make a POST request to the login API
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        loginUrl,
                        requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.has("message")) {
                                        String message = response.getString("message");
                                        if (message.equals("success")) {
                                            // Login successful, navigate to the next activity
                                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                            // Add code to start the next activity here
                                            Intent intent = new Intent(LoginActivity.this, HomeCleaning.class);
                                            startActivity(intent);
                                        } else {
                                            // Login failed, display a toast message
                                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
/*import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private String loginUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passwordlogin);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get email and password from EditText fields
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                // Make a GET request to the API
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        loginUrl,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Parse the JSON response
                                    String responseEmail = response.getString("email");
                                    String responsePassword = response.getString("password");

                                    if (email.equals(responseEmail) && password.equals(responsePassword)) {
                                        // Login successful, navigate to the next activity
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        // Add code to start the next activity here
                                        Intent intent = new Intent(LoginActivity.this, HomeService.class);
                                        startActivity(intent);
                                    } else {
                                        // Login failed, display a toast message
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}*/

/*import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private String loginUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passwordlogin);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get email and password from EditText fields
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                // Create a JSON object with email and password
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("email", email);
                    requestBody.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Make a POST request to the login API
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        loginUrl,
                        requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        // Login successful, navigate to the next activity
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivity.this, HomeService.class);
                                        startActivity(intent);
                                    } else {
                                        // Login failed, display a toast message
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}*/







/*import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private String apiUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passwordlogin);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get email and password from EditText fields
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                // Make a GET request to the API
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        apiUrl,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Parse the JSON response
                                    String responseEmail = response.getString("email");
                                    String responsePassword = response.getString("password");

                                    if (email.equals(responseEmail) && password.equals(responsePassword)) {
                                        // Login successful, navigate to the next activity
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        // Add code to start the next activity here
                                    } else {
                                        // Login failed, display a toast message
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}*/




/*import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find your login button by its ID
        Button loginButton = findViewById(R.id.login_button);

        // Set an OnClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HomeService
                Intent intent = new Intent(LoginActivity.this, HomeCleaning.class);
                startActivity(intent);

                // Display a "Login Successful" toast message
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/


