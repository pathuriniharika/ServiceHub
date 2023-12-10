package com.example.servicehub;

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

public class FetchServices extends AppCompatActivity {
    private static final String TAG = "FetchServices";
    private Button showAllListingsButton;
    private TextView listingsTextView;
    private RequestQueue requestQueue;

    private int userId;
    private int savedListerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        showAllListingsButton = findViewById(R.id.showAllListingsButton);
        listingsTextView = findViewById(R.id.listingsTextView);

        fetchUserIdAndListerId();

        requestQueue = Volley.newRequestQueue(this);

        showAllListingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://coms-309-063.class.las.iastate.edu:8080/listingsByLister/" + savedListerId;
                Log.d("FetchServices", "Fetching listings for lister_id: " + savedListerId);

                // Show loading message
                listingsTextView.setText("Loading...");

                // Create a request to fetch data from the URL
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("FetchServices", "Response: " + response);

                                // Check if the response is not an empty array
                                if (!response.equals("[]")) {
                                    // Handle the response and format it as plain text
                                    formatAndDisplayListings(response);
                                } else {
                                    listingsTextView.setText("No listings found.");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("FetchServices", "Error: " + error.toString());

                                // Display a user-friendly error message
                                listingsTextView.setText("Error fetching data. Please check your network connection.");
                            }
                        });

                // Add the request to the queue for execution
                requestQueue.add(stringRequest);
            }
        });
    }

    // Method to format and display service listings as plain text
    // Method to format and display service listings as plain text with each object on a new line
    // Method to format and display service listings with each JSON object on a new line
    private void formatAndDisplayListings(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            StringBuilder formattedData = new StringBuilder();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Format the JSON object as a string with line breaks
                String formattedJson = formatJsonObject(jsonObject);

                // Append the formatted JSON object to the result
                formattedData.append(formattedJson);

                // Add a line break between each JSON object
                formattedData.append("\n\n");
            }

            listingsTextView.setText(formattedData.toString());
        } catch (JSONException e) {
            listingsTextView.setText("Error parsing data.");
        }
    }

    // Method to format a JSON object as a string with line breaks
    private String formatJsonObject(JSONObject jsonObject) {
        StringBuilder formattedJson = new StringBuilder();

        try {
            formattedJson.append("Lister ID: ").append(jsonObject.getJSONObject("lister").getInt("id")).append("\n");
            formattedJson.append("Lister Name: ").append(jsonObject.getJSONObject("lister").getString("name")).append("\n");
            formattedJson.append("Service Type: ").append(jsonObject.getString("serviceType")).append("\n");
            formattedJson.append("Description: ").append(jsonObject.getString("description")).append("\n");
            formattedJson.append("Price: $").append(jsonObject.getDouble("price")).append("\n");
        } catch (JSONException e) {
            formattedJson.append("Error formatting JSON object");
        }

        return formattedJson.toString();
    }



    private void fetchUserIdAndListerId() {
        userId = getSavedUserId();
        savedListerId = getSavedListerId();

        if (userId == -1 || savedListerId == -1) {
            // Handle the case where the user ID or lister ID is not found
            Log.e(TAG, "Error: User ID or Lister ID not found");
            Toast.makeText(FetchServices.this, "Error: User ID or Lister ID not found", Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message when both the user ID and lister ID are successfully fetched
            Log.d(TAG, "User ID successfully fetched: " + userId);
            Log.d(TAG, "Lister ID successfully fetched: " + savedListerId);
            Toast.makeText(FetchServices.this, "User ID successfully fetched: " + userId, Toast.LENGTH_SHORT).show();
            Toast.makeText(FetchServices.this, "Lister ID successfully fetched: " + savedListerId, Toast.LENGTH_SHORT).show();
        }
    }

    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }

    private int getSavedListerId() {
        SharedPreferences preferences = getSharedPreferences("service_data", Context.MODE_PRIVATE);
        return preferences.getInt("lister_id", -1);
    }
}
