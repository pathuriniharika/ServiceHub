package com.example.servicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the CardView for "HOME CLEANING" by its ID
        @SuppressLint({"WrongViewCast", "MissingInflatedId", "LocalSuppress"})
        CardView homeCleaningCard = findViewById(R.id.homecleaning);

        // Set an OnClickListener to handle the click event
        homeCleaningCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to Activity_HomeCleaning
                Intent intent = new Intent(HomeService.this, HomeCleaning.class);
                startActivity(intent);
            }
        });
    }
}
