package com.example.servicehub;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class settings_fragment extends Fragment {

    private static final String TAG = "SettingsFragment";
    private static final String BASE_URL = "http://coms-309-063.class.las.iastate.edu:8080";
    private static final String NOTIFICATION_ENDPOINT = "/notify/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_frag, container, false);

        // Fetch notifications when the fragment view is created
        fetchNotifications(rootView);

        return rootView;
    }

    private void fetchNotifications(View rootView) {
        int userId = getActivity().getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE).getInt("userId", 0);

        try {
            String encodedUserId = URLEncoder.encode(String.valueOf(userId), "UTF-8");
            String backendEndpoint = BASE_URL + NOTIFICATION_ENDPOINT + encodedUserId;

            // Add a debug log statement to show the constructed backend endpoint
            Log.d(TAG, "fetchNotifications: Backend endpoint: " + backendEndpoint);

            RequestQueue queue = Volley.newRequestQueue(requireContext());

            StringRequest request = new StringRequest(Request.Method.GET, backendEndpoint,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle the response from your backend service
                            TextView notificationMessageTextView = rootView.findViewById(R.id.notificationMessage);
                            notificationMessageTextView.setText(response); // Display the notification message

                            // Add a debug log statement to indicate a successful response
                            Log.d(TAG, "onResponse: Notifications fetched successfully: " + response);

                            showNotificationPopup(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error responses here
                            Toast.makeText(requireContext(), "Failed to fetch notifications: " + error.getMessage(), Toast.LENGTH_SHORT).show();

                            // Add a debug log statement to indicate an error response
                            Log.e(TAG, "onErrorResponse: Failed to fetch notifications: " + error.getMessage(), error);
                        }
                    });

            // Add the request to the queue
            queue.add(request);

            Log.d(TAG, "fetchNotifications: Fetching notifications");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "fetchNotifications: Error encoding user ID: " + e.getMessage());
            Toast.makeText(requireContext(), "Error fetching notifications. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNotificationPopup(String notificationMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("New Notification");
        builder.setMessage(notificationMessage);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
