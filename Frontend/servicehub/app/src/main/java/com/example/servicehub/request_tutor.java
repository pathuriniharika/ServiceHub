package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class request_tutor extends AppCompatActivity {

    private NetworkManager networkManager;
    private EditText searchNameField;
    private Button searchButton;
    private RecyclerView tutorRecyclerView;
    private JSONArray allTutors;
    private JSONArray filteredTutors;

    private int userId;

    public void setUserIdForTesting(int userId) {
        this.userId = userId;
    }

    private class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.TutorViewHolder> {

        private JSONArray tutorData;

        public TutorAdapter(JSONArray tutorData) {
            this.tutorData = tutorData;
        }

        public void updateData(JSONArray tutorData) {
            this.tutorData = tutorData;
            notifyDataSetChanged();
        }

        @Override
        public TutorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutor, parent, false);
            return new TutorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TutorViewHolder holder, int position) {
            try {
                JSONObject tutor = tutorData.getJSONObject(position);

                holder.tutorNameTextView.setText("name: " + tutor.getString("fullname"));
                holder.tutorQualificationTextView.setText("Qualification: " + tutor.getString("qualification"));
                holder.tutorDescriptionTextView.setText("Description: " + tutor.getString("description"));
                holder.tutorSubjectTextView.setText("Subject: " + tutor.getString("subject"));

                // Handle the bookTutorButton click event here
                holder.bookTutorButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            bookTutor(tutor.getInt("id"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return tutorData.length();
        }

        public class TutorViewHolder extends RecyclerView.ViewHolder {
            TextView tutorNameTextView;
            TextView tutorQualificationTextView;
            TextView tutorDescriptionTextView;
            TextView tutorSubjectTextView;
            Button bookTutorButton;

            public TutorViewHolder(View itemView) {
                super(itemView);
                tutorNameTextView = itemView.findViewById(R.id.tutorName);
                tutorQualificationTextView = itemView.findViewById(R.id.tutorQualification);
                tutorDescriptionTextView = itemView.findViewById(R.id.tutorDescription);
                tutorSubjectTextView = itemView.findViewById(R.id.tutorSubject);
                bookTutorButton = itemView.findViewById(R.id.bookTutorButton);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tutor);

        networkManager = NetworkManager.getInstance(this);
        searchNameField = findViewById(R.id.searchNameField);
        searchButton = findViewById(R.id.searchButton);
        tutorRecyclerView = findViewById(R.id.tutorRecyclerView);

        Button backButton = findViewById(R.id.backButton);

        tutorRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userId = getSharedPreferences("YourPrefsFile", MODE_PRIVATE).getInt("userId", -1);
        if(userId == -1) {
            Log.e("request_tutor", "User ID not found in SharedPreferences.");
        } else {
            Log.d("request_tutor", "Retrieved User ID: " + userId);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(request_tutor.this, loginSuccess.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchByName();
            }
        });

        fetchTutors();
    }

    private void fetchTutors() {
        String url_requestTutor = "http://coms-309-063.class.las.iastate.edu:8080/tutors";
        Log.d("Request", "Fetching tutors from URL: " + url_requestTutor);

        networkManager.sendGetRequest(url_requestTutor,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response", "Received response: " + response.toString());
                        allTutors = response;
                        filteredTutors = allTutors;

                        // Set up the RecyclerView adapter and display tutors
                        TutorAdapter adapter = new TutorAdapter(filteredTutors);
                        tutorRecyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Error fetching tutors: " + error.toString());
                    }
                }
        );
    }

    private void onSearchByName() {
        String fullname = searchNameField.getText().toString().trim();

        if (!fullname.isEmpty()) {
            filteredTutors = filterTutorsByFullname(fullname);
        } else {
            filteredTutors = allTutors;
        }

        // Update the RecyclerView with the filtered tutors
        ((TutorAdapter) tutorRecyclerView.getAdapter()).updateData(filteredTutors);
    }

    private JSONArray filterTutorsByFullname(String fullname) {
        JSONArray filteredTutors = new JSONArray();
        try {
            for (int i = 0; i < allTutors.length(); i++) {
                JSONObject tutor = allTutors.getJSONObject(i);
                String tutorFullname = tutor.getString("fullname");
                if (tutorFullname.toLowerCase().contains(fullname.toLowerCase())) {
                    filteredTutors.put(tutor);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filteredTutors;
    }

    private void bookTutor(int tutorId) {
        // Build the URL with query parameters
        String urlBookTutor = "http://coms-309-063.class.las.iastate.edu:8080/bookings/bookForTutoring"
                + "?userId=" + userId
                + "&tutoringId=" + tutorId;
        Log.d("Booking", "Booking tutor with URL: " + urlBookTutor);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlBookTutor, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the booking response here
                        Log.d("Booking", "Booking response: " + response.toString());
                        try {
                            if (response != null && response.has("id")) {
                                int bookingId = response.getInt("id");
                                Toast.makeText(request_tutor.this, "Tutor booked successfully! Booking ID: " + bookingId, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(request_tutor.this, "Failed to book tutor. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(request_tutor.this, "Failed to book tutor. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Booking", "Error booking tutor: " + error.toString());
                        Toast.makeText(request_tutor.this, "Failed to book tutor. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
        );



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }




}
