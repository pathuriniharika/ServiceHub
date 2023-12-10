package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the LinearLayout for "HOME CLEANING" by its ID
        LinearLayout homeCleaningLayout = findViewById(R.id.homecleaning);

        // Set an OnClickListener to handle the click event
        homeCleaningLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to HomeCleaning activity
                Intent intent = new Intent(HomeService.this, HomeCleaning.class);
                startActivity(intent);
            }
        });
    }
}
