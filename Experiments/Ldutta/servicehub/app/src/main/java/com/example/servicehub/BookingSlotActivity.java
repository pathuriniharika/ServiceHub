//THIS CODE WORKS USE THIS
//package com.example.servicehub;

/*import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class BookingSlotActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button bookSlotButton;
    private EditText serviceTypeEditText;

    // Define your backend API URL
    private static final String bookingURL = "http://coms-309-063.class.las.iastate.edu:8080/bookings";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slotbooking);

        datePicker = findViewById(R.id.Date);
        timePicker = findViewById(R.id.Time);
        bookSlotButton = findViewById(R.id.bookSlot);
        serviceTypeEditText = findViewById(R.id.serviceType);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        bookSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected date and time
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                // Create a JSONObject with the booking data
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("serviceType", serviceTypeEditText.getText().toString());
                    requestBody.put("serviceType", serviceTypeEditText.getText().toString());
                    //requestBody.put("tutorName", "YourTutorName");
                    //requestBody.put("subjectName", "YourSubjectName");

                    // Convert date and time to a suitable format and add to the JSON
                    String formattedDateTime = String.format("%d-%02d-%02dT%02d:%02d:00",
                            year, month + 1, day, hour, minute);  // Month is 0-based
                    requestBody.put("timeslot", formattedDateTime);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, bookingURL, requestBody,
                       new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the success response from the server
                                Toast.makeText(BookingSlotActivity.this, "Booking successful", Toast.LENGTH_SHORT).show();

                                // Navigate to ActivityHistory
                                Intent intent = new Intent(BookingSlotActivity.this, BookingHistory.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle network or server errors
                                Toast.makeText(BookingSlotActivity.this, "Booking failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(request);

            }
        });
    }
}*/

package com.example.servicehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingSlotActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button bookSlotButton;
    private EditText serviceTypeEditText;

    // Define your backend API URL
    private static final String bookingURL = "http://coms-309-063.class.las.iastate.edu:8080/bookings";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slotbooking);

        datePicker = findViewById(R.id.Date);
        timePicker = findViewById(R.id.Time);
        bookSlotButton = findViewById(R.id.bookSlot);
        serviceTypeEditText = findViewById(R.id.serviceType);

        // Button for navigating to Booking History
        Button bookingHistoryButton = findViewById(R.id.bookingHistory);
        bookingHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the BookingHistory activity
                Intent intent = new Intent(BookingSlotActivity.this, BookingHistory.class);
                startActivity(intent);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        bookSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected date and time
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                // Create a JSONObject with the booking data
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("serviceType", serviceTypeEditText.getText().toString());
                    requestBody.put("tutorName", "YourTutorName");
                    requestBody.put("subjectName", "YourSubjectName");

                    // Convert date and time to a suitable format and add to the JSON
                    String formattedDateTime = String.format("%d-%02d-%02dT%02d:%02d:00",
                            year, month + 1, day, hour, minute);  // Month is 0-based
                    requestBody.put("timeslot", formattedDateTime);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, bookingURL, requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the success response from the server
                                Toast.makeText(BookingSlotActivity.this, "Booking successful", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle network or server errors
                                Toast.makeText(BookingSlotActivity.this, "Booking failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(request);
            }
        });
    }
}







