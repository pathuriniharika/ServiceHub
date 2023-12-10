package com.example.servicehub;

/*import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BookingService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Find the "Book Now" buttons by their IDs
        Button bookButton1 = findViewById(R.id.book1);
        Button bookButton2 = findViewById(R.id.book2);
        Button bookButton3 = findViewById(R.id.book3);
        Button bookButton4 = findViewById(R.id.book4);

        // Find the "Chat Now" buttons by their IDs
        Button chatButton1 = findViewById(R.id.chat1);
        Button chatButton2 = findViewById(R.id.chat2);
        Button chatButton3 = findViewById(R.id.chat3);
        Button chatButton4 = findViewById(R.id.chat4);

        // Set an OnClickListener for each "Book Now" button
        View.OnClickListener bookClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToBookingSlotActivity();
            }
        };

        bookButton1.setOnClickListener(bookClickListener);
        bookButton2.setOnClickListener(bookClickListener);
        bookButton3.setOnClickListener(bookClickListener);
        bookButton4.setOnClickListener(bookClickListener);

        // Set an OnClickListener for each "Chat Now" button
        View.OnClickListener chatClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToChatActivity();
            }
        };

        chatButton1.setOnClickListener(chatClickListener);
        chatButton2.setOnClickListener(chatClickListener);
        chatButton3.setOnClickListener(chatClickListener);
        chatButton4.setOnClickListener(chatClickListener);
    }

    // Method to navigate to BookingSlotActivity
    private void navigateToBookingSlotActivity() {
        Intent intent = new Intent(BookingService.this, BookingSlotActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to navigate to ChatActivity
    private void navigateToChatActivity() {
        Intent intent = new Intent(BookingService.this, ChatActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookingService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Call the method to fetch data from the database and update UI
        fetchDataFromDatabase();

        // Find the "Book Now" buttons by their IDs
        Button bookButton1 = findViewById(R.id.book1);
        Button bookButton2 = findViewById(R.id.book2);
        Button bookButton3 = findViewById(R.id.book3);
        Button bookButton4 = findViewById(R.id.book4);

        // Find the "Chat Now" buttons by their IDs
        Button chatButton1 = findViewById(R.id.chat1);
        Button chatButton2 = findViewById(R.id.chat2);
        Button chatButton3 = findViewById(R.id.chat3);
        Button chatButton4 = findViewById(R.id.chat4);

        // Set an OnClickListener for each "Book Now" button
        View.OnClickListener bookClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToBookingSlotActivity();
            }
        };

        bookButton1.setOnClickListener(bookClickListener);
        bookButton2.setOnClickListener(bookClickListener);
        bookButton3.setOnClickListener(bookClickListener);
        bookButton4.setOnClickListener(bookClickListener);

        // Set an OnClickListener for each "Chat Now" button
        View.OnClickListener chatClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToChatActivity();
            }
        };

        chatButton1.setOnClickListener(chatClickListener);
        chatButton2.setOnClickListener(chatClickListener);
        chatButton3.setOnClickListener(chatClickListener);
        chatButton4.setOnClickListener(chatClickListener);
    }

    // Method to navigate to BookingSlotActivity
    private void navigateToBookingSlotActivity() {
        Intent intent = new Intent(BookingService.this, BookingSlotActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to navigate to ChatActivity
    private void navigateToChatActivity() {
        Intent intent = new Intent(BookingService.this, ChatActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to fetch data from the specified database URL using Volley
    private void fetchDataFromDatabase() {
        // Define the URL for your database
        String databaseUrl = "http://coms-309-063.class.las.iastate.edu:8080/users";

        // Instantiate the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a request to retrieve a JSON array from the database URL
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, databaseUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON data here
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                // Extract data from the JSON object
                                String name = jsonObject.getString("name");
                                double price = jsonObject.getDouble("price");
                                String description = jsonObject.getString("description");

                                // Update the corresponding TextViews with the data
                                if (i == 0) {
                                    updateUI(name, price, description, R.id.name1, R.id.price1, R.id.description1);
                                } else if (i == 1) {
                                    updateUI(name, price, description, R.id.name2, R.id.price2, R.id.description2);
                                } else if (i == 2) {
                                    updateUI(name, price, description, R.id.name3, R.id.price3, R.id.description3);
                                } else if (i == 3) {
                                    updateUI(name, price, description, R.id.name4, R.id.price4, R.id.description4);
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
                        // Handle errors here
                        error.printStackTrace();
                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }

    // Helper method to update UI elements with data
    private void updateUI(String name, double price, String description, int nameResId, int priceResId, int descriptionResId) {
        TextView nameTextView = findViewById(nameResId);
        TextView priceTextView = findViewById(priceResId);
        TextView descriptionTextView = findViewById(descriptionResId);

        nameTextView.setText(name);
        priceTextView.setText("Price: $" + price);
        descriptionTextView.setText(description);
    }
}


