package com.example.servicehub;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class booking_history extends AppCompatActivity implements CarpoolAdapter.OnJoinButtonClickListener, CarpoolAdapter.OnRateButtonClickListener {

    private RecyclerView recyclerView;
    private CarpoolAdapter adapter;
    private List<CarpoolListing> carpoolListings;
    private RequestQueue requestQueue;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history);

        SharedPreferences prefs = getSharedPreferences("YourPrefsFile", MODE_PRIVATE);
        userId = prefs.getInt("userId", -1);

        recyclerView = findViewById(R.id.recycler_view_booking_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        carpoolListings = new ArrayList<>();
        adapter = new CarpoolAdapter(carpoolListings, this, this, userId, false, true, false);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        fetchBookingHistory();
    }

    private void fetchBookingHistory() {
        String bookingHistoryUrl = "http://coms-309-063.class.las.iastate.edu:8080/bookings/" + userId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                bookingHistoryUrl,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            // Check if the current item is null
                            if(response.isNull(i)) {
                                continue; // Skip this iteration if the item is null
                            }

                            JSONObject jsonObject = response.getJSONObject(i);
                            CarpoolListing carpoolListing = new CarpoolListing(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("startLocation"),
                                    jsonObject.getString("destination"),
                                    jsonObject.getString("dateTime"),
                                    jsonObject.getInt("capacity"),
                                    jsonObject.getDouble("avgRating")
                            );
                            carpoolListings.add(carpoolListing);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.e("Volley", "Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                },
                error -> {
                    if (error.networkResponse != null) {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        Log.e("Volley", "Error response body: " + responseBody);
                    }
                    Log.e("Volley", "Error: " + error.toString());

                }
        );

        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public void onJoinButtonClick(int position, int userId, int carpoolListingId) {

    }

    @Override
    public void onRateButtonClick(int position, int carpoolingListingId) {
        if (position >= 0 && position < carpoolListings.size()) {
            CarpoolListing carpoolListing = carpoolListings.get(position);
            int carpoolListingId = carpoolListing.getId();


            Intent intent = new Intent(this, Ratings.class);
            intent.putExtra("carpoolListingId", carpoolListingId);
            startActivity(intent);
        }
    }

}

