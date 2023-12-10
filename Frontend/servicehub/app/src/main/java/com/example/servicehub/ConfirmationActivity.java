package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView confirmationText = findViewById(R.id.confirmationText);
        TextView bookingDetails = findViewById(R.id.bookingDetails);
        Button okButton = findViewById(R.id.okButton);

        // Get the booking details passed from BookingSlotActivity
        Intent intent = getIntent();
        if (intent.hasExtra("bookingTime")) {
            String bookingTime = intent.getStringExtra("bookingTime");
            confirmationText.setText("Booking Successful");
            bookingDetails.setText("Booking Time: " + bookingTime);
        }

        /*okButton.setOnClickListener(view -> { */
            // Navigate to the BookingHistory activity
            /*Intent intent = new Intent(ConfirmationActivity.this, BookingHistory.class);
            startActivity(intent);*/
           /* finish(); // Close the ConfirmationActivity
        });*/
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the BookingHistory activity
                Intent intent = new Intent(ConfirmationActivity.this, BookingHistory.class);
                startActivity(intent);
            }
        });
    }
}
