package com.example.servicehub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Locale;

public class BookingSlotActivity extends AppCompatActivity {
    private static final String TAG = "BookingSlotService";
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button bookSlotButton;
    private EditText serviceTypeEditText;

    // Define your backend API URL
    private static final String bookingURL = "http://coms-309-063.class.las.iastate.edu:8080/Bookings";

    // Manually set listerId for testing
    private static final int TEST_LISTER_ID = 35;

    private int userId;
    private int listerId = TEST_LISTER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slotbooking);

        datePicker = findViewById(R.id.Date);
        timePicker = findViewById(R.id.Time);
        bookSlotButton = findViewById(R.id.bookSlot);
        serviceTypeEditText = findViewById(R.id.serviceType);

        userId = getSavedUserId(); // Fetch the user ID

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        bookSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute);

                String formattedDateTime = String.format(Locale.US, "%d-%02d-%02dT%02d:%02d:00",
                        year, month + 1, day, hour, minute);

                StringRequest request = new StringRequest(Request.Method.POST, bookingURL,
                        response -> {
                            Log.d(TAG, "Server response: " + response);
                            handleBookingResponse(response);
                        },
                        error -> {
                            handleErrorResponse(error);
                        }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        JSONObject requestBody = new JSONObject();
                        try {
                            requestBody.put("serviceType", serviceTypeEditText.getText().toString());
                            requestBody.put("timeslot", formattedDateTime);
                            requestBody.put("user_id", userId);
                            requestBody.put("lister_id", listerId);

                            Log.d(TAG, "Request body: " + requestBody.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Error creating JSON request: " + e.getMessage());
                        }
                        return requestBody.toString().getBytes(StandardCharsets.UTF_8);
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }
                };

                requestQueue.add(request);
            }
        });
    }

    private void handleBookingResponse(String response) {
        // Handle the string response from the server
        Toast.makeText(BookingSlotActivity.this, "Response: " + response, Toast.LENGTH_SHORT).show();
        // Add additional logic based on the response as needed
    }

    private void handleErrorResponse(VolleyError error) {
        Log.e(TAG, "Volley error: " + error.toString());
        Toast.makeText(BookingSlotActivity.this, "Booking Failed", Toast.LENGTH_SHORT).show();
    }

    private int getSavedUserId() {
        SharedPreferences preferences = getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }
}
