package com.example.servicehub;

/*package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeCleaning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homecleaning);

        // Find the card views and button by their IDs
        CardView listServiceCardView = findViewById(R.id.listServiceCardView);
        CardView bookServiceCardView = findViewById(R.id.bookServiceCardView);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        // Set click listeners for the card views and button
        listServiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Listing Activity
                Intent intent = new Intent(HomeCleaning.this, ListingService.class);
                startActivity(intent);
            }
        });

        bookServiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Booking Activity
                Intent intent = new Intent(HomeCleaning.this, BookingService.class);
                startActivity(intent);
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the Home Activity
                Intent intent = new Intent(HomeCleaning.this, HomeService.class);
                startActivity(intent);
            }
        });
    }
} */

/*package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeCleaning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homecleaning);

        // Find the card views and button by their IDs
        CardView listServiceCardView = findViewById(R.id.listServiceCardView);
        CardView bookServiceCardView = findViewById(R.id.bookServiceCardView);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        // Set click listeners for the card views and button
        listServiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Listing Activity
                Intent intent = new Intent(HomeCleaning.this, ListingService.class);
                startActivity(intent);
            }
        });

        bookServiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Booking Activity
                Intent intent = new Intent(HomeCleaning.this, BookingService.class);
                startActivity(intent);
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the Home Activity
                Intent intent = new Intent(HomeCleaning.this, HomeService.class);
                startActivity(intent);
            }
        });
    }
} */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeCleaning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homecleaning);

        // Find the card views and button by their IDs
        CardView listServiceCardView = findViewById(R.id.listServiceCardView);
        CardView bookServiceCardView = findViewById(R.id.bookServiceCardView);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);

        // Set click listeners for the card views and button
        listServiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Listing Activity
                Intent intent = new Intent(HomeCleaning.this,ListingService.class);
                startActivity(intent);
            }
        });

        bookServiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Booking Activity
                Intent intent = new Intent(HomeCleaning.this, BookingService.class);
                startActivity(intent);
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add a log message to check if this code is executed
                Log.d("HomeCleaning", "backToHomeButton clicked");

                // Navigate back to the Home Activity
                Intent intent = new Intent(HomeCleaning.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
