package com.example.servicehub;

import android.content.Context;
import android.net.Uri;



import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkManager {
    private static NetworkManager instance = null;
    private RequestQueue requestQueue;
    private static Context ctx;
    private static final int MY_SOCKET_TIMEOUT_MS = 10000;

    private NetworkManager(Context context) {
        ctx = context.getApplicationContext();
        requestQueue = getRequestQueue();
    }
    public interface CarpoolListingsResponseListener {
        void onResponse(List<CarpoolListing> carpoolListings);
        void onError(String message);
    }


    public interface BookingResponseListener {
        void onResponse(Booking booking);
        void onError(String message);
    }
    public static synchronized NetworkManager getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkManager(context);
        }
        return instance;
    }
    public void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }


    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }

    public void sendPostRequest(JSONObject postData, String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, responseListener, errorListener);
        getRequestQueue().add(jsonObjectRequest);
    }

    public void sendGetRequest(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, responseListener, errorListener);
        getRequestQueue().add(jsonArrayRequest);
    }
    public void loginUser(String email, String password, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String url = "http://coms-309-063.class.las.iastate.edu:8080/users/login";
        url += "?email=" + Uri.encode(email) + "&password=" + Uri.encode(password);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        addToRequestQueue(request);
    }
    public void createBooking(String url, int userId, int carpoolListingId, BookingResponseListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        Booking booking = parseBooking(jsonResponse);
                        listener.onResponse(booking);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onError("Parsing error: " + e.getMessage());
                    }
                },
                error -> {
                    String message = "Unknown error";
                    if (error.networkResponse != null) {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        message = "Server error: " + responseBody + " - Status Code: " + error.networkResponse.statusCode;
                    } else if (error.getMessage() != null) {
                        message = "Server error: " + error.getMessage();
                    }
                    listener.onError(message);
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", String.valueOf(userId));
                params.put("carpoolingId", String.valueOf(carpoolListingId));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(stringRequest);
    }


    private Booking parseBooking(JSONObject jsonObject) {
        try {
            int id = jsonObject.optInt("id", -1);
            int userId = jsonObject.optInt("userId", -1);
            int carpoolListingId = jsonObject.optInt("carpoolListingId", -1);

            if (id == -1 || userId == -1 || carpoolListingId == -1) {
                // If any of the IDs are -1, it means they were not present in the response
                throw new JSONException("Required fields are missing in the JSON response.");
            }

            return new Booking(id, userId, carpoolListingId);
        } catch (JSONException e) {
            // Handle the JSON parsing error
            e.printStackTrace();
            // Log the error or throw a custom exception that your app can handle
            // For now, we're just returning null to indicate a parsing failure
            return null;
        }
    }



    // Modify the fetchCarpoolListings method to accept a CarpoolListingsResponseListener instead of the generic Response.Listeners
    public void fetchCarpoolListings(String url, final CarpoolListingsResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<CarpoolListing> listings = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String startLocation = jsonObject.getString("startLocation");
                            String destination = jsonObject.getString("destination");
                            String dateTime = jsonObject.getString("dateTime");
                            int capacity = jsonObject.getInt("capacity");
                            double avgerageRating = jsonObject.getDouble("avgRating");

                            CarpoolListing listing = new CarpoolListing(id, startLocation, destination, dateTime, capacity, avgerageRating);
                            listings.add(listing);
                        }
                        listener.onResponse(listings);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onError("JSON error: " + e.getMessage());
                    }
                },
                error -> listener.onError("Volley error: " + error.getMessage())
        );

        addToRequestQueue(jsonArrayRequest);
    }



    public void sendJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, jsonRequest, listener, errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(jsonObjectRequest);
    }

    public void sendJsonObjectGetRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(jsonObjectRequest);
    }
    public void sendJsonArrayPostRequest(String url, JSONArray jsonArray, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                responseListener,
                errorListener
        ) {
            @Override
            public byte[] getBody() {
                return jsonArray.toString().getBytes();
            }
        };
        getRequestQueue().add(jsonArrayRequest);
    }


    public void makeJsonArrayRequest(String url, final VolleyResponseListener<JSONArray> listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        listener.onResponse(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> listener.onError(error.toString()));
        getRequestQueue().add(jsonArrayRequest);
    }

    public interface VolleyResponseListener<T> {
        void onResponse(T response) throws JSONException;
        void onError(String message);
    }
}

