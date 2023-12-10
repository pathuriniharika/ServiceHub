package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class carpool_listers extends AppCompatActivity {

    private Button listServiceButton;
    private Button updateListingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_user);

        listServiceButton = findViewById(R.id.listServiceButton);
        updateListingButton = findViewById(R.id.updateListingButton);

        listServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(carpool_listers.this, CarpoolActivity.class);
                startActivity(intent);
            }
        });

        updateListingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(carpool_listers.this, update_booking.class);
                startActivity(intent);
            }
        });
    }
}
