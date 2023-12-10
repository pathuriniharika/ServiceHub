package com.example.myloginpage;

/*import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePasswordActivity extends AppCompatActivity {

    private static final String PASSWORD_UPDATE_URL = "http://coms-309-063.class.las.iastate.edu:8080/{id}/up/{password}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Assuming you have a button with the id btnSavePassword in your XML layout.
        Button btnSavePassword = findViewById(R.id.updatePasswordbtn);

        // Set an OnClickListener for btnSavePassword.
        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to update the password.
                updatePassword("new_password_here");
            }
        });
    }

    private void updatePassword(String newPassword) {
        // Create a RequestQueue using Volley.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a StringRequest to make a POST request.
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                PASSWORD_UPDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response if needed.
                        // For example, show a success message.
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here. You can show an error message.
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Set the parameters you want to send with the POST request.
                Map<String, String> params = new HashMap<>();
                params.put("new_password", newPassword);
                // Add more parameters if needed.

                return params;
            }
        };

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }
} */
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePasswordActivity extends AppCompatActivity {

    // Replace PASSWORD_UPDATE_URL with your actual URL
    private static final String PASSWORD_UPDATE_URL = "http://coms-309-063.class.las.iastate.edu/{id}/up/{password}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Assuming you have a button with the id btnSavePassword in your XML layout.
        Button btnSavePassword = findViewById(R.id.updatePasswordbtn);

        // Set an OnClickListener for btnSavePassword.
        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace "new_password_here" with the actual new password.
                updatePassword("password");
            }
        });
    }

    private void updatePassword(String newPassword) {
        // Create a RequestQueue using Volley.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a StringRequest to make a POST request.
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                PASSWORD_UPDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response if needed.
                        // For example, show a success message.
                        Toast.makeText(UpdatePasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here. You can show an error message.
                        Toast.makeText(UpdatePasswordActivity.this, "Error updating password", Toast.LENGTH_SHORT).show();
                        // You can also log the error for debugging purposes.
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Set the parameters you want to send with the POST request.
                Map<String, String> params = new HashMap<>();
                params.put("password", newPassword);
                // Add more parameters if needed.

                return params;
            }
        };

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }
}

