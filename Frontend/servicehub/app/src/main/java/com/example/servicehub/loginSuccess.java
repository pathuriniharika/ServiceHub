package com.example.servicehub;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class loginSuccess extends AppCompatActivity {


    private Button userProfileButton;
    private Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        userProfileButton = findViewById(R.id.userprofile);
        homeButton = findViewById(R.id.homeButton);




        // Set an OnClickListener for the homeButton
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to a different page (e.g., MainActivity)
                Intent intent = new Intent(loginSuccess.this, MainActivity.class);
                startActivity(intent);
            }
        });
        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to a different page (e.g., MainActivity)
                Intent intent = new Intent(loginSuccess.this, UserActivity.class);
                startActivity(intent);
            }
        });


    }


}
