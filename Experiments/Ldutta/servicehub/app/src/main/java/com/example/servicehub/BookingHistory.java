/*package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;

public class BookingHistory extends AppCompatActivity {

    private EditText serviceTypeEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Button backToHomeButton;

    private RequestQueue requestQueue;

    // Define the base URL for your web service
    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080/bookings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        serviceTypeEditText = findViewById(R.id.providerNameEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        requestQueue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceType = serviceTypeEditText.getText().toString();
                if (!serviceType.isEmpty()) {
                    fetchBookings(serviceType);
                } else {
                    Toast.makeText(BookingHistory.this, "Please enter the service type", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Find and set the "Back to Home" button
        backToHomeButton = findViewById(R.id.backToHomeButton);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomeActivity when the "Back to Home" button is clicked
                Intent intent = new Intent(BookingHistory.this, HomeService.class);
                startActivity(intent);
            }
        });
    }

    private void fetchBookings(String serviceType) {
        // Construct the URL with the service type parameter
        String url = BASE_URL + "bookings?service=" + serviceType;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the JSON response and display it in the resultTextView
                        if (response.length() > 0) {
                            // If there are bookings for the specified service type, display them
                            resultTextView.setText(response.toString());
                        } else {
                            resultTextView.setText("No bookings found for the specified service type.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}
*/

package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookingHistory extends AppCompatActivity {

    private EditText serviceTypeEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Button backToHomeButton;

    private RequestQueue requestQueue;
    private JSONArray allBookings;
    private JSONArray filteredBookings;

    // Define the base URL for your web service
    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080/bookings/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        serviceTypeEditText = findViewById(R.id.providerNameEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        requestQueue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchByServiceType();
            }
        });

        // Find and set the "Back to Home" button
        backToHomeButton = findViewById(R.id.backToHomeButton);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomeActivity when the "Back to Home" button is clicked
                Intent intent = new Intent(BookingHistory.this, HomeService.class);
                startActivity(intent);
            }
        });
    }

    // Method to fetch all bookings and display the response directly
    private void fetchBookings() {
        String serviceType = serviceTypeEditText.getText().toString().trim();

        if (!serviceType.isEmpty()) {
            String url = BASE_URL + "?service=" + serviceType;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Log.d("Response", "Received response: " + response.toString());

                                // Convert the JSON response to a string
                                String responseString = response.toString(4);

                                // Store the response as JSON array
                                allBookings = response;

                                // Update the filtered bookings based on service type
                                filteredBookings = filterBookingsByServiceType(allBookings, serviceType);

                                // Display the response in the TextView
                                resultTextView.setText(responseString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            requestQueue.add(jsonArrayRequest);
        } else {
            Toast.makeText(BookingHistory.this, "Please enter the type of Service", Toast.LENGTH_SHORT).show();
        }
    }

    private void onSearchByServiceType() {
        fetchBookings();
    }

    private JSONArray filterBookingsByServiceType(JSONArray allBookings, String serviceType) {
        JSONArray filteredBookings = new JSONArray();

        try {
            for (int i = 0; i < allBookings.length(); i++) {
                JSONObject booking = allBookings.getJSONObject(i);
                String bookingServiceType = booking.getString("service_type");

                if (bookingServiceType.equalsIgnoreCase(serviceType)) {
                    filteredBookings.put(booking);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filteredBookings;
    }
}

//works and displays all the history
/*package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookingHistory extends AppCompatActivity {

    private EditText serviceTypeEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Button backToHomeButton;

    private RequestQueue requestQueue;
    private JSONArray allBookings;
    private JSONArray filteredBookings;

    // Define the base URL for your web service
    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080/bookings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        serviceTypeEditText = findViewById(R.id.providerNameEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        requestQueue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchByServiceType();
            }
        });

        // Find and set the "Back to Home" button
        backToHomeButton = findViewById(R.id.backToHomeButton);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomeActivity when the "Back to Home" button is clicked
                Intent intent = new Intent(BookingHistory.this, HomeService.class);
                startActivity(intent);
            }
        });
    }

    // Method to fetch all bookings and display the response directly
    private void fetchBookings() {
        String serviceType = serviceTypeEditText.getText().toString().trim();

        if (!serviceType.isEmpty()) {
            String url = BASE_URL + "?service=" + serviceType;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Log.d("Response", "Received response: " + response.toString());

                                // Convert the JSON response to a string
                                String responseString = response.toString(4);

                                // Store the response as JSON array
                                allBookings = response;
                                filteredBookings = allBookings;

                                // Display the response in the TextView
                                resultTextView.setText(responseString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            requestQueue.add(jsonArrayRequest);
        } else {
            Toast.makeText(BookingHistory.this, "Please enter the type of Service", Toast.LENGTH_SHORT).show();
        }
    }

    private void onSearchByServiceType() {
        fetchBookings();
    }
}
*/









/*package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray; */

/*public class BookingHistory extends AppCompatActivity {

    private EditText providerNameEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Button backToHomeButton;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        providerNameEditText = findViewById(R.id.providerNameEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        requestQueue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = providerNameEditText.getText().toString();
                if (!serviceName.isEmpty()) {
                    fetchBookings(serviceName);
                } else {
                    Toast.makeText(BookingHistory.this, "Please enter the type of Service", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Find and set the "Back to Home" button
        backToHomeButton = findViewById(R.id.backToHomeButton);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomeActivity when the "Back to Home" button is clicked
                Intent intent = new Intent(BookingHistory.this, HomeService.class);
                startActivity(intent);
            }
        });
    }

    private void fetchBookings(String serviceName) {
        String baseUrl = "http://coms-309-063.class.las.iastate.edu:8080/bookings";
        String url = baseUrl + "?service=" + serviceName;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the JSON response and display it in the resultTextView
                        resultTextView.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}*/


/*public class BookingHistory extends AppCompatActivity {

    private EditText providerNameEditText;
    private Button searchButton;
    private TextView resultTextView;
    private Button backToHomeButton;

    private RequestQueue requestQueue;

    // Define the base URL for your web service
    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        providerNameEditText = findViewById(R.id.providerNameEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        requestQueue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = providerNameEditText.getText().toString();
                if (!serviceName.isEmpty()) {
                    fetchBookings(serviceName);
                } else {
                    Toast.makeText(BookingHistory.this, "Please enter the type of Service", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Find and set the "Back to Home" button
        backToHomeButton = findViewById(R.id.backToHomeButton);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HomeActivity when the "Back to Home" button is clicked
                Intent intent = new Intent(BookingHistory.this, HomeService.class);
                startActivity(intent);
            }
        });
    }

    private void fetchBookings(String serviceName) {
        // Construct the URL with the service name parameter
        String url = BASE_URL + "bookings?service=" + serviceName;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the JSON response and display it in the resultTextView
                        if (response.length() > 0) {
                            // If there are bookings for the specified service, display them
                            resultTextView.setText(response.toString());
                        } else {
                            resultTextView.setText("No bookings found for the specified service.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookingHistory.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
} */


