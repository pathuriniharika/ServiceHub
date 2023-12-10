package com.example.servicehub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AppRateActivity extends AppCompatActivity {

    private double userRate = 0;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateapp);

        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);
        final EditText doubleRatingEditText = findViewById(R.id.doubleRatingEditText);

        fetchUserId();

        rateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double userDoubleRating;

                // Check if doubleRatingEditText is not empty
                if (!doubleRatingEditText.getText().toString().isEmpty()) {
                    // If not empty, get the double rating from the EditText
                    userDoubleRating = Double.parseDouble(doubleRatingEditText.getText().toString());
                } else {
                    // If doubleRatingEditText is empty, use the float rating from the RatingBar
                    userDoubleRating = userRate;
                }

                // Submit the double rating to the server
                submitRatingToServer(userId, userDoubleRating);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                updateRatingImage((double) rating, ratingImage);
                animateImage(ratingImage);
                userRate = rating;
            }
        });

    }

    private void fetchUserId() {
        userId = getSavedUserId();

        if (userId == -1) {
            // Handle the case where the user ID is not found
            Toast.makeText(AppRateActivity.this, "Error: User ID not found", Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message when the user ID is successfully fetched
            Toast.makeText(AppRateActivity.this, "User ID successfully fetched: " + userId, Toast.LENGTH_SHORT).show();
        }
    }

    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }
    private void updateRatingImage(Double rating, ImageView ratingImage) {
        int drawableId;
        if (rating <= 1) {
            drawableId = R.drawable.one_star;
        } else if (rating <= 2) {
            drawableId = R.drawable.two_star;
        } else if (rating <= 3) {
            drawableId = R.drawable.three_star;
        } else if (rating <= 4) {
            drawableId = R.drawable.four_star;
        } else {
            drawableId = R.drawable.five_star;
        }
        ratingImage.setImageResource(drawableId);
    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }

    private void submitRatingToServer(int userId, double userDoubleRating) {
        // Replace this URL with your server's endpoint
        String url = "http://coms-309-063.class.las.iastate.edu:8080/users/" + userId + "/setRating";

        // Make a request using Volley
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the server response (if needed)
                        Toast.makeText(AppRateActivity.this, "Rating submitted successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        String errorMessage = "Error submitting rating: " + error.toString();
                        Log.e("AppRateActivity", errorMessage);
                        Toast.makeText(AppRateActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("rating", String.valueOf(userDoubleRating));
                return params;
            }
        };

        // Add the request to the request queue
        Volley.newRequestQueue(this).add(request);
    }
}

