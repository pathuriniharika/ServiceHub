package com.example.servicehub;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import java.util.ArrayList;

public class Ratings extends AppCompatActivity {
    private Button submitReviewButton;
    private EditText userReviewEditText;
    private RatingBar ratingBar;
    private TextView averageRatingTextView;
    private ListView reviewsListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> reviewList = new ArrayList<>();
    private WebSocket webSocket;

    private int carpoolListingId;
//    private AverageRatingManager averageRatingManager = AverageRatingManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratings_reviews);

        submitReviewButton = findViewById(R.id.submitReviewButton);
        userReviewEditText = findViewById(R.id.userReviewEditText);
        ratingBar = findViewById(R.id.ratingBar);
        averageRatingTextView = findViewById(R.id.averageRatingTextView);
        reviewsListView = findViewById(R.id.reviewsListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviewList);
        reviewsListView.setAdapter(adapter);

        carpoolListingId = getIntent().getIntExtra("carpoolListingId", -1);
        if (carpoolListingId != -1) {
            connectToRatingsWebSocket(carpoolListingId);
        } else {
            Toast.makeText(this, "Invalid Carpool Listing ID", Toast.LENGTH_SHORT).show();
        }

        submitReviewButton.setOnClickListener(v -> submitReview(carpoolListingId));
    }

    private void connectToRatingsWebSocket(int carpoolListingId) {
        OkHttpClient client = new OkHttpClient();
        Request Ratings_request = new Request.Builder().url("ws://coms-309-063.class.las.iastate.edu:8080/ratings/" + carpoolListingId).build();
        webSocket = client.newWebSocket(Ratings_request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {
                runOnUiThread(() -> Toast.makeText(Ratings.this, "Connected to server", Toast.LENGTH_SHORT).show());
                requestAverageRatingForCarpoolListing(carpoolListingId);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                runOnUiThread(() -> handleMessage(text));
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
                runOnUiThread(() -> Toast.makeText(Ratings.this, "Error connecting to server", Toast.LENGTH_SHORT).show());
            }
        });

        client.dispatcher().executorService().shutdown();
    }

    private void handleMessage(String text) {
        if (text.startsWith("Average Rating for Carpool ID")) {
            String[] messageParts = text.split(": ");
            if (messageParts.length >= 2) {

//                float averageRating = Float.parseFloat(messageParts[1]);
//                averageRatingManager.setAverageRating(carpoolListingId, averageRating);
//                averageRatingTextView.setText("Average Rating: " + averageRating);
            }
        } else if (text.startsWith("Success:")) {
            Toast.makeText(Ratings.this, text, Toast.LENGTH_LONG).show();
        } else if (text.startsWith("Error:")) {
            Toast.makeText(Ratings.this, text, Toast.LENGTH_LONG).show();
        } else {
            reviewList.add(text);
            adapter.notifyDataSetChanged();
        }
    }

    private void requestAverageRatingForCarpoolListing(int carpoolListingId) {
        if (webSocket != null) {
            String message = "GET_AVERAGE_RATING:" + carpoolListingId;
            webSocket.send(message);
        }
    }

    private void submitReview(int carpoolListingId) {
        String review = userReviewEditText.getText().toString().trim();
        int rating = Math.round(ratingBar.getRating());

        if (review.isEmpty()) {
            Toast.makeText(this, "Please enter a review", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rating == 0) {
            Toast.makeText(this, "Please give a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        if (webSocket != null) {
            // Construct the message for submitting a review.
            String message = "SUBMIT_RATING:" + carpoolListingId + ":" + rating + ":" + review;
            webSocket.send(message);
            userReviewEditText.setText("");
            ratingBar.setRating(0);

            // Immediately after sending a review, request the updated average rating.
            // This is done by sending another WebSocket message.
            requestAverageRatingForCarpoolListing(carpoolListingId);
        } else {
            Toast.makeText(this, "Not connected to server", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) {
            webSocket.close(1000, "Activity destroyed");
        }
    }
}
