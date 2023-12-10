package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ConsumerActivity extends AppCompatActivity {

    private CardView newBoxCard;
    private TextView catchPhraseTextView;
    private ImageView pictureImageView;
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_seekers);

        newBoxCard = findViewById(R.id.newBoxCard);
        catchPhraseTextView = findViewById(R.id.catchPhraseTextView);
        pictureImageView = findViewById(R.id.pictureImageView);
        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);

        newBoxCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on newBoxCard
                // You can add your logic here

                // For example, you can start a new activity when the card is clicked
                startActivity(new Intent(ConsumerActivity.this, SignUpActivity.class));
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on cardView1
                // You can add your logic here

                // For example, you can start a new activity when the card is clicked
                startActivity(new Intent(ConsumerActivity.this, ListingService.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on cardView2
                // You can add your logic here

                // For example, you can start a new activity when the card is clicked
                startActivity(new Intent(ConsumerActivity.this, tutor_become.class));
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on cardView3
                // You can add your logic here

                // For example, you can start a new activity when the card is clicked
                startActivity(new Intent(ConsumerActivity.this, carpool_listers.class));
            }
        });
    }
    public void onSearchButtonClick(View view) {
        // This method should handle the click event for the view with android:onClick="onSearchButtonClick"
        // Add your logic here
        Toast.makeText(this, "Search button clicked", Toast.LENGTH_SHORT).show();
    }

    public void onCardViewClick(View view) {
        // Your code to handle the click event goes here
    }

    public void onNewBoxCardClick(View view) {
        // Your code to handle the click event goes here
    }

}
