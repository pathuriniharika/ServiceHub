package com.example.servicehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class carpool_search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarpoolAdapter carpoolAdapter;
    private List<CarpoolListing> carpoolListings;

    private ImageButton refreshButton;

    private ImageButton searchButton;

    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpool_avail);
        refreshButton = findViewById(R.id.refreshButton);
        searchButton = findViewById(R.id.searchButton);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carpoolListings = new ArrayList<>();
        userId = getSharedPreferences("YourPrefsFile", MODE_PRIVATE).getInt("userId", -1);
        if(userId == -1) {
            Log.e("CarpoolSearchActivity", "User ID not found in SharedPreferences.");
        } else {
            Log.d("CarpoolSearchActivity", "Retrieved User ID: " + userId);
        }
        carpoolAdapter = new CarpoolAdapter(carpoolListings, this::onJoinButtonClick, null, userId, true, false, true);

        recyclerView.setAdapter(carpoolAdapter);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recreate();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the search button click here
                performSearch();
            }
        });

        fetchData();
    }


    private void onJoinButtonClick(int position, int userId, int carpoolListingId) {
        Log.d("CarpoolSearchActivity", "Attempting to join carpool with ID: " + carpoolListingId);

        CarpoolListing carpoolListing = carpoolListings.get(position);
        if (carpoolListing.getId() != carpoolListingId) {
            Log.e("CarpoolSearchActivity", "Mismatch in Carpool IDs. Expected: " + carpoolListing.getId() + ", Received: " + carpoolListingId);
            return; // Stop further execution if there is a mismatch
        }


        if (carpoolListing.getCapacity() > 0) {
            carpoolListing.setCapacity(carpoolListing.getCapacity() - 1);
            carpoolAdapter.notifyItemChanged(position);

            String bookCarpoolUrl = "http://coms-309-063.class.las.iastate.edu:8080/bookings/add";
            String urlWithParams = bookCarpoolUrl + "?userId=" + userId + "&carpoolingId=" + carpoolListingId;
            NetworkManager.getInstance(this).createBooking(urlWithParams, userId, carpoolListingId, new NetworkManager.BookingResponseListener() {
                @Override
                public void onResponse(Booking booking) {
                    Toast.makeText(carpool_search.this, "Booked successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(carpool_search.this, MapActivity.class);
                    intent.putExtra("startLocation", carpoolListing.getStartLocation());
                    intent.putExtra("destination", carpoolListing.getDestination());
                    startActivity(intent);
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(carpool_search.this, "Failed to book carpool.", Toast.LENGTH_SHORT).show();
                    // Restore capacity because booking failed
                    carpoolListing.setCapacity(carpoolListing.getCapacity() + 1);
                    carpoolAdapter.notifyItemChanged(position);
                }
            });
        } else {
            Toast.makeText(this, "This carpool is full", Toast.LENGTH_SHORT).show();
        }
    }


    private void fetchData() {
        String url = "http://coms-309-063.class.las.iastate.edu:8080/listers";
        NetworkManager.getInstance(this).fetchCarpoolListings(url, new NetworkManager.CarpoolListingsResponseListener() {
            @Override
            public void onResponse(List<CarpoolListing> carpoolListingsResponse) {
                // Clear the existing carpoolListings
                carpoolListings.clear();

                // Iterate through the response and add non-null listings to carpoolListings
                for (CarpoolListing listing : carpoolListingsResponse) {
                    if (listing != null) {
                        carpoolListings.add(listing);
                    }
                }

                // Notify the adapter of the data change
                carpoolAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(carpool_search.this, "Error fetching data: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void performSearch() {
        String searchQuery = ((EditText) findViewById(R.id.searchEditText)).getText().toString().toLowerCase();

        List<CarpoolListing> filteredListings = new ArrayList<>();

        for (CarpoolListing listing : carpoolListings) {
            String destination = listing.getDestination().toLowerCase();

            if (destination.contains(searchQuery)) {
                filteredListings.add(listing);
            }
        }

        // Update the RecyclerView with the filtered data
        carpoolAdapter = new CarpoolAdapter(filteredListings, this::onJoinButtonClick, null, userId, true, false, true);
        recyclerView.setAdapter(carpoolAdapter);
        carpoolAdapter.notifyDataSetChanged();
    }


}
