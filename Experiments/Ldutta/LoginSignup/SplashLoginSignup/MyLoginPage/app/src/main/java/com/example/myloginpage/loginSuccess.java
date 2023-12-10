package com.example.myloginpage;

/*import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class loginSuccess extends AppCompatActivity {

    private Button getUsersButton;
    private Button userProfileButton;
    private TextView msgResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        getUsersButton = findViewById(R.id.Getusers);
        userProfileButton = findViewById(R.id.userprofile);
        msgResponse = findViewById(R.id.msgResponse);

        getUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your API URL here for retrieving user data
                String userDataUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

                // Make an HTTP request to fetch user data
                fetchUserData(userDataUrl);
            }
        });

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can add user authentication logic here
                // For demonstration purposes, assume the login is successful
                boolean loginSuccessful = true;

                if (loginSuccessful) {
                    // Successful login, navigate to UserActivity
                    Toast.makeText(loginSuccess.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginSuccess.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    // Failed login, show a message to the user
                    Toast.makeText(loginSuccess.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchUserData(String url) {
        // Make an HTTP request to retrieve user data from the server
        // You can use libraries like Volley, Retrofit, or OkHttp for this purpose
        // Parse the API response and display user data on the profile page
        // Example code for making the HTTP request using Volley:

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the JSON response to extract user data
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String userName = jsonObject.getString("name");
                            String userEmail = jsonObject.getString("email");

                            // Create an intent to navigate to UserActivity and pass user data
                            Intent intent = new Intent(loginSuccess.this, UserActivity.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("userEmail", userEmail);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(loginSuccess.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(loginSuccess.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}*/
/*import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class loginSuccess extends AppCompatActivity {

    private Button getUsersButton;
    private Button userProfileButton;
    private TextView msgResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        getUsersButton = findViewById(R.id.Getusers);
        userProfileButton = findViewById(R.id.userprofile);
        msgResponse = findViewById(R.id.msgResponse);

        getUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your API URL here for retrieving user data
                String userDataUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";
                //String userDataUrl = "https://c10099fd-377d-46d4-8d6b-dd8c7c786349.mock.pstmn.io";
                // Make an HTTP request to fetch user data
                fetchUserData(userDataUrl);
            }
        });

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can add user authentication logic here
                // For demonstration purposes, assume the login is successful
                boolean loginSuccessful = true;

                if (loginSuccessful) {
                    // Successful login, navigate to activity_user
                    Toast.makeText(loginSuccess.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginSuccess.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    // Failed login, show a message to the user
                    Toast.makeText(loginSuccess.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // The fetchUserData method remains the same as in your original code
    // You don't need to make any changes to it for the navigation purpose.

    private void fetchUserData(String url) {
        // Your fetchUserData method implementation
    }
} */

/*import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class loginSuccess extends AppCompatActivity {

    private Button getUsersButton;
    private Button userProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        getUsersButton = findViewById(R.id.Getusers);
        userProfileButton = findViewById(R.id.userprofile);

        getUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your API URL here for retrieving user data
                String userDataUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";
                //String userDataUrl = "https://c10099fd-377d-46d4-8d6b-dd8c7c786349.mock.pstmn.io";
                // Make an HTTP request to fetch user data
                fetchUserData(userDataUrl);
            }
        });

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can add user authentication logic here
                // For demonstration purposes, assume the login is successful
                boolean loginSuccessful = true;

                if (loginSuccessful) {
                    // Successful login, navigate to UserActivity
                    Toast.makeText(loginSuccess.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginSuccess.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    // Failed login, show a message to the user
                    Toast.makeText(loginSuccess.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // The fetchUserData method remains the same as in your original code
    // You don't need to make any changes to it for the navigation purpose.

    private void fetchUserData(String url) {
        // Your fetchUserData method implementation
    }
} */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginSuccess extends AppCompatActivity {

    private Button getUsersButton;
    private Button userProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        getUsersButton = findViewById(R.id.Getusers);
        userProfileButton = findViewById(R.id.userprofile);

        getUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your API URL here for retrieving user data
                String userDataUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";
                // Make an HTTP request to fetch user data
                fetchUserData(userDataUrl);
            }
        });

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can add user authentication logic here
                // For demonstration purposes, assume the login is successful
                boolean loginSuccessful = true;

                if (loginSuccessful) {
                    // Successful login, navigate to ActivityUser
                    Toast.makeText(loginSuccess.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginSuccess.this, UserActivity.class); // Change to ActivityUser
                    startActivity(intent);
                } else {
                    // Failed login, show a message to the user
                    Toast.makeText(loginSuccess.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // The fetchUserData method remains the same as in your original code
    // You don't need to make any changes to it for the navigation purpose.

    private void fetchUserData(String url) {
        // Your fetchUserData method implementation
    }
}


