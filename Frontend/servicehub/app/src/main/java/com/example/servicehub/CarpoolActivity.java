package com.example.servicehub;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CarpoolActivity extends AppCompatActivity {

    private EditText departureLocationEditText, destinationEditText, capacityEditText;
    private DatePicker datePicker;
    private Spinner timeSpinner;
    private Button submitButton;
    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpool_activity);

        departureLocationEditText = findViewById(R.id.departureLocation);
        destinationEditText = findViewById(R.id.destination);
        datePicker = findViewById(R.id.datePicker);
        timeSpinner = findViewById(R.id.timeSpinner);
        capacityEditText = findViewById(R.id.capacityEditText);
        submitButton = findViewById(R.id.submitButton);

        networkManager = NetworkManager.getInstance(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the back button click event here by starting ConsumerActivity:
                Intent intent = new Intent(CarpoolActivity.this, ConsumerActivity.class);
                startActivity(intent);
                finish(); // Optional: Finish the CarpoolActivity if you don't want to return to it later.
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitCarpoolListing();
//                String startLocation = departureLocationEditText.getText().toString().trim();
//                navigateToMapActivity(startLocation);
                hideKeyboard();
            }
        });

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void submitCarpoolListing() {
        String departureLocation = departureLocationEditText.getText().toString().trim();
        String destination = destinationEditText.getText().toString().trim();
        String capacityStr = capacityEditText.getText().toString().trim();

        if (departureLocation.isEmpty() || destination.isEmpty() || capacityStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int dayOfMonth = datePicker.getDayOfMonth();
        String selectedTime = timeSpinner.getSelectedItem().toString();

        SimpleDateFormat parseTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        Date timeDate;
        Calendar timeCalendar = Calendar.getInstance();
        try {
            timeDate = parseTimeFormat.parse(selectedTime);
            timeCalendar.setTime(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(CarpoolActivity.this, "Invalid time format", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));

        Date dateTime = calendar.getTime();

        SimpleDateFormat formattedDateFormat = new SimpleDateFormat("EEEE hh:mm a", Locale.US);
        String dateTimeFormatted = formattedDateFormat.format(dateTime);

        int userId = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE).getInt("userId", 0);

        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
            if (capacity <= 0) {
                Toast.makeText(this, "Capacity must be a positive integer", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid capacity format", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject carpoolData = new JSONObject();
        JSONObject carpoolListingData = new JSONObject();
        try {
            carpoolData.put("userId", userId);
            carpoolListingData.put("startLocation", departureLocation);
            carpoolListingData.put("destination", destination);
            carpoolListingData.put("capacity", capacity);
            carpoolListingData.put("dateTime", dateTimeFormatted);

            carpoolData.put("carpoolListing", carpoolListingData);

            Log.d("Debug", "Carpool Data: " + carpoolData.toString());

            String carpoolUrl = "http://coms-309-063.class.las.iastate.edu:8080/listers";

            networkManager.sendJsonObjectRequest(Request.Method.POST, carpoolUrl, carpoolData, response -> {
                Toast.makeText(CarpoolActivity.this, "Carpool listing created!", Toast.LENGTH_SHORT).show();
                finish();
            }, error -> {
                Log.e("Network Error", "Error: " + error.toString());
                if (error.networkResponse != null) {
                    Log.e("Network Error Details", "Status code: " + error.networkResponse.statusCode);
                    Log.e("Network Error Details", "Data: " + new String(error.networkResponse.data));
                }
                if (error.getMessage() != null) {
                    Log.e("Network Error Message", error.getMessage());
                }
                Toast.makeText(CarpoolActivity.this, "Failed to create listing. Check logs for details.", Toast.LENGTH_SHORT).show();
            });
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON Error", e.toString());
            Toast.makeText(CarpoolActivity.this, "Failed to create listing due to JSON error.", Toast.LENGTH_SHORT).show();
        }
    }


//    private void navigateToMapActivity(String startLocation) {
//        Intent intent = new Intent(CarpoolActivity.this, maps_first.class);
//        intent.putExtra("startLocation", startLocation);
//        startActivity(intent);
//    }

}
