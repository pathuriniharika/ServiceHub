package com.example.servicehub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookingService extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView serviceTextView;
    private Button refreshButton;
    private Button bookNowButton;
    private Button chatNowButton;
    private int userId; // User ID variable to store the user ID
    private int listerId; // Lister ID variable to store the lister ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        scrollView = findViewById(R.id.scrollView);
        serviceTextView = findViewById(R.id.serviceTextView);
        refreshButton = findViewById(R.id.refreshButton);
        bookNowButton = findViewById(R.id.bookNowButton);
        chatNowButton = findViewById(R.id.chatNowButton);

        // Access the user ID directly from the userId variable
        userId = getSavedUserId(); // Retrieve the user ID from SharedPreferences

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);

        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BookingService", "bookNowButton clicked");
                // Handle the "Book Now" button click
                Intent intent = new Intent(BookingService.this, BookingSlotActivity.class);
                startActivity(intent);
            }
        });

        chatNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BookingService", "chatNowButton clicked");
                Intent intent = new Intent(BookingService.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Refresh button clicked, fetch new data
                fetchServices(queue, "http://coms-309-063.class.las.iastate.edu:8080/listings");
            }
        });

        // Fetch and display services using Volley
        fetchServices(queue, "http://coms-309-063.class.las.iastate.edu:8080/listings");
    }

    private void fetchServices(RequestQueue queue, String url) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response in your TextView within ScrollView
                        displayListings(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        serviceTextView.setText("Error fetching data");
                        Log.e("BookingService", "Error fetching data: " + error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void displayListings(String listings) {
        try {
            JSONArray jsonArray = new JSONArray(listings);
            StringBuilder formattedData = new StringBuilder();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject listerObject = jsonObject.optJSONObject("lister"); // Use optJSONObject

                // Build the string only if listerObject is not null
                if (listerObject != null) {
                    String id = listerObject.optString("id");
                    String name = listerObject.optString("name");
                    String email = listerObject.optString("email");

                    formattedData.append("Lister ID: ").append(id).append("\n");
                    formattedData.append("Name: ").append(name).append("\n");
                    formattedData.append("Email: ").append(email).append("\n\n");
                }

                String serviceId = jsonObject.optString("id");
                String serviceType = jsonObject.optString("serviceType");
                String description = jsonObject.optString("description");
                String price = jsonObject.optString("price");

                formattedData.append("Service ID: ").append(serviceId).append("\n");
                formattedData.append("Service Type: ").append(serviceType).append("\n");
                formattedData.append("Description: ").append(description).append("\n");
                formattedData.append("Price: ").append(price).append("\n");

                // Add a line break between each listing
                if (i < jsonArray.length() - 1) {
                    formattedData.append("\n--------------------------------------\n\n");
                }
            }

            serviceTextView.setText(formattedData.toString());
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        } catch (JSONException e) {
            e.printStackTrace();
            serviceTextView.setText("Error parsing JSON data");
        }
    }




    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }
}
