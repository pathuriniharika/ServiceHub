package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CounterActivity extends AppCompatActivity {
    private int counter = 0;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        // Initialize TextView
        counterTextView = findViewById(R.id.counterTextView);
        updateCounterText();
    }

    public void incrementCounter(View view) {
        if (counter < 15) {
            counter++;
            updateCounterText();
        } else {
            // Display a toast message when trying to increment above 15
            Toast.makeText(this, "Fail: Counter can't go above 15", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrementCounter(View view) {
        if (counter > -15) {
            counter--;
            updateCounterText();
        } else {
            // Display a toast message when trying to decrement below -15
            Toast.makeText(this, "Fail: Counter can't go below -15", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateCounterText() {
        counterTextView.setText(String.valueOf(counter));
    }
}
