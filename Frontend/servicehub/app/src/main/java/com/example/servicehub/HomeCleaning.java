package com.example.servicehub;
/*
Author: Leha Dutta
 */

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



        CardView bookServiceCardView = findViewById(R.id.bookServiceCardView);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);




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
