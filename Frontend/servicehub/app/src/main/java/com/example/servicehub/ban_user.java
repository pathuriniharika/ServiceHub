package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

public class ban_user extends AppCompatActivity {

    private EditText userIdEditText;
    private Button banUserButton;
    private RequestQueue requestQueue;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_nav);

        userIdEditText = findViewById(R.id.etUserId);
        backButton = findViewById(R.id.btnBack);
        banUserButton = findViewById(R.id.btnBanUser);
        requestQueue = Volley.newRequestQueue(this);

        banUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = userIdEditText.getText().toString().trim();
                if (userId.isEmpty()) {
                    Snackbar.make(view, "Please enter a user ID", Snackbar.LENGTH_SHORT).show();
                } else {
                    banUser(userId, view);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ban_user.this, admin.class);
                startActivity(intent);
            }
        });

    }

    private void banUser(String userId, View view) {
        String apiUrl = "http://coms-309-063.class.las.iastate.edu:8080/users/" + userId;

        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(view, "User banned successfully", Snackbar.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(view, "Error: User not found or unable to ban", Snackbar.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(stringRequest);
    }
}
