package com.example.servicehub;
/*
Author: Leha Dutta
 */

/*import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookingHistory extends AppCompatActivity {
    private EditText serviceTypeEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Button backToHomeButton;

    private RequestQueue requestQueue;


    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080/bookings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        serviceTypeEditText = findViewById(R.id.serviceTypeSearch);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        requestQueue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchByServiceType();
            }
        });

        // Find and set the "Back to Home" button
        backToHomeButton = findViewById(R.id.backToHomeButton);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomeActivity when the "Back to Home" button is clicked
                Intent intent = new Intent(BookingHistory.this, HomeService.class);
                startActivity(intent);
            }
        });
    }

    // Method to fetch all bookings and display the response in the requested format
    private void fetchBookings() {
        String serviceType = serviceTypeEditText.getText().toString().trim();

        if (!serviceType.isEmpty()) {
            String url = BASE_URL + "?service=" + serviceType;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Log.d("Response", "Received response: " + response.toString());

                                // Iterate through the bookings and format the output
                                StringBuilder formattedResult = new StringBuilder();
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject booking = response.getJSONObject(i);
                                    int id = booking.getInt("id");
                                    String bookingServiceType = booking.getString("serviceType");
                                    String timeslot = booking.getString("timeslot");

                                    formattedResult.append("id: ").append(id).append("\n");
                                    formattedResult.append("serviceType: ").append(bookingServiceType).append("\n");
                                    formattedResult.append("timeslot: ").append(timeslot).append("\n\n");
                                }

                                // Display the formatted result in the TextView
                                resultTextView.setText(formattedResult.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("BookingHistory", "Error processing response: " + e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("BookingHistory", "Volley error: " + error.getMessage());
                        }
                    }
            );

            requestQueue.add(jsonArrayRequest);
        } else {
            Toast.makeText(BookingHistory.this, "Please enter the type of Service", Toast.LENGTH_SHORT).show();
        }
    }

    private void onSearchByServiceType() {
        fetchBookings();
    }
}*/

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookingHistory extends AppCompatActivity {
    private static final String TAG = "BookingHistory";
    private Button showAllBookingsButton;
    private TextView bookingsTextView;
    private RequestQueue requestQueue;

    private int userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory);

        showAllBookingsButton = findViewById(R.id.showAllBookingsButton);
        bookingsTextView = findViewById(R.id.bookingsTextView);

        fetchUserId();

        requestQueue = Volley.newRequestQueue(this);

        showAllBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://coms-309-063.class.las.iastate.edu:8080/Bookings/user/" + userId;
                Log.d(TAG, "Fetching bookings for user id: " + userId);

                // Show loading message
                bookingsTextView.setText("Loading...");

                // Create a request to fetch data from the URL
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Response: " + response);

                                // Check if the response is not an empty array
                                if (!response.equals("[]")) {
                                    // Handle the response and format it as plain text
                                    formatAndDisplayBookings(response);
                                } else {
                                    bookingsTextView.setText("No Bookings found.");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "Error: " + error.toString());

                                // Display a user-friendly error message
                                bookingsTextView.setText("Error fetching data. Please check your network connection.");
                            }
                        });

                // Add the request to the queue for execution
                requestQueue.add(stringRequest);
            }
        });
    }

    // Method to format and display bookings as plain text
    /*private void formatAndDisplayBookings(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            StringBuilder formattedData = new StringBuilder();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int bookingId = jsonObject.getInt("id");
                String serviceType = jsonObject.getString("service_type");
                String timeSlot = jsonObject.getString("time_slot");

                formattedData.append("Booking ID: ").append(bookingId).append("\n");
                formattedData.append("Service Type: ").append(serviceType).append("\n");
                formattedData.append("Time Slot: ").append(timeSlot).append("\n\n");
            }

            bookingsTextView.setText(formattedData.toString());
        } catch (JSONException e) {
            bookingsTextView.setText("Error parsing data.");
        }
    }*/

    // Method to format and display bookings as plain text
    private void formatAndDisplayBookings(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            StringBuilder formattedData = new StringBuilder();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject bookingObject = jsonArray.getJSONObject(i);

                int bookingId = bookingObject.getInt("id");
                String serviceType = bookingObject.getString("serviceType");
                String timeSlot = bookingObject.getString("timeslot");

                JSONObject userObject = bookingObject.getJSONObject("user");
                String userName = userObject.getString("name");
                String userEmail = userObject.getString("email");

                formattedData.append("Booking ID: ").append(bookingId).append("\n");
                formattedData.append("Service Type: ").append(serviceType).append("\n");
                formattedData.append("Time Slot: ").append(timeSlot).append("\n");
                formattedData.append("User Name: ").append(userName).append("\n");
                formattedData.append("User Email: ").append(userEmail).append("\n\n");
            }

            bookingsTextView.setText(formattedData.toString());
        } catch (JSONException e) {
            bookingsTextView.setText("Error parsing data: " + e.getMessage());
        }
    }



    private void fetchUserId () {
        userId = getSavedUserId();

        if (userId == -1) {
            // Handle the case where the user ID is not found
            Toast.makeText(BookingHistory.this, "Error: User ID not found", Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message when the user ID is successfully fetched
            Toast.makeText(BookingHistory.this, "User ID successfully fetched: " + userId, Toast.LENGTH_SHORT).show();
        }
    }

    private int getSavedUserId () {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }
}

