package com.example.servicehub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordActivity extends AppCompatActivity {

    private static final String PASSWORD_UPDATE_URL = "http://coms-309-063.class.las.iastate.edu:8080/updatePassword";

    private EditText newPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        newPasswordEditText = findViewById(R.id.passwordlogin);
        Button updatePasswordButton = findViewById(R.id.updatePasswordbtn);

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = newPasswordEditText.getText().toString();
                updatePassword(newPassword);
            }
        });
    }

    private void updatePassword(String newPassword) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                PASSWORD_UPDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response here (e.g., show a success message).
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here, e.g., show an error message.
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("new_password", newPassword);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
