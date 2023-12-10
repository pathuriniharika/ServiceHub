package com.example.servicehub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class ListingService extends AppCompatActivity {

    private static final String TAG = "ListingService";

    private EditText nameEditText, priceEditText, descriptionEditText;
    private Button listServiceButton;
    private Button viewAllListingsButton;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listingservice);

        nameEditText = findViewById(R.id.TextName);
        priceEditText = findViewById(R.id.TextPrice);
        descriptionEditText = findViewById(R.id.TextDescription);
        listServiceButton = findViewById(R.id.buttonListService);
        viewAllListingsButton = findViewById(R.id.buttonViewAllListings);

        fetchUserId();

        listServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "List Service Button Clicked");

                // Get user-entered data
                String serviceType = nameEditText.getText().toString();
                String price = priceEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // Log the service data
                Log.d(TAG, "Service Data: " + serviceType + ", " + price + ", " + description + ", " + userId);

                // Save the listerId as userId
                saveListerId(userId);

                // Create a JSON object with the data
                JSONObject serviceData = new JSONObject();
                try {
                    serviceData.put("serviceType", serviceType);
                    serviceData.put("price", price);
                    serviceData.put("description", description);
                    serviceData.put("lister_id", userId);
                    //serviceData.put("user_id",userId);
                    Log.d(TAG, "Request Data: " + serviceData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String listingUrl = "http://coms-309-063.class.las.iastate.edu:8080/users/" + userId + "/listService";

                Log.d(TAG, "Listing URL: " + listingUrl);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, listingUrl, serviceData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Log the entire server response
                                Log.d(TAG, "Server Response: " + response.toString());

                                try {
                                    // Check the response for success
                                    if (response.getString("message").equals("success")) {
                                        // Handle the server's response, e.g., show a success message
                                        Log.d(TAG, "Service listed successfully");
                                        Toast.makeText(ListingService.this, "Service listed successfully", Toast.LENGTH_SHORT).show();

                                    } else {
                                        // Handle the case where the server response indicates failure
                                        Log.e(TAG, "Error: " + response.getString("message"));
                                        Toast.makeText(ListingService.this, "Error: " + response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Log the error response
                                Log.e(TAG, "Error: " + error.getMessage());

                                // Handle errors, e.g., show an error message
                                Toast.makeText(ListingService.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                // Add the request to the Volley request queue
                RequestQueue requestQueue = Volley.newRequestQueue(ListingService.this);
                requestQueue.add(request);
            }
        });

        viewAllListingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "View All Listings Button Clicked");
                // Navigate to the ActivityServices activity
                Intent intent = new Intent(ListingService.this, FetchServices.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserId() {
        userId = getSavedUserId();

        if (userId == -1) {
            // Handle the case where the user ID is not found
            Toast.makeText(ListingService.this, "Error: User ID not found", Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message and log the user ID when it is successfully fetched
            Toast.makeText(ListingService.this, "User ID successfully fetched: " + userId, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "User ID: " + userId);
        }
    }

    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }

    private void saveListerId(int listerId) {
        SharedPreferences preferences = getSharedPreferences("service_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lister_id", listerId);
        editor.apply();
    }
}
