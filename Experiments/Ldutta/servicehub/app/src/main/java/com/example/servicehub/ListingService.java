package com.example.servicehub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ListingService extends AppCompatActivity {

    private EditText nameEditText, priceEditText, descriptionEditText;
    private Button listServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listingservice);

        nameEditText = findViewById(R.id.TextName);
        priceEditText = findViewById(R.id.TextPrice);
        descriptionEditText = findViewById(R.id.TextDescription);
        listServiceButton = findViewById(R.id.buttonListService);

        listServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user-entered data
                String name = nameEditText.getText().toString();
                String price = priceEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // Make a POST request using Volley
                String url = "http://coms-309-063.class.las.iastate.edu:8080/users";
                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the server's response, e.g., show a success message
                                Toast.makeText(ListingService.this, "Service listed successfully", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle errors, e.g., show an error message
                                Toast.makeText(ListingService.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Set POST parameters with user-entered data
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("price", price);
                        params.put("description", description);
                        return params;
                    }
                };

                // Add the request to the Volley request queue
                Volley.newRequestQueue(ListingService.this).add(request);
            }
        });
    }
}

