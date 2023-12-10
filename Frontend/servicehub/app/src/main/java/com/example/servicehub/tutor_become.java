package com.example.servicehub;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class tutor_become extends AppCompatActivity {

    private Button backButton;
    private Button submitButton;
    private NetworkManager networkManager;
    private EditText fullname;  // Keep the variable name as "fullname"
    private EditText Subject;
    private EditText qualification;
    private EditText description;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.become_tutor);

        networkManager = NetworkManager.getInstance(this);
        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.buttonSubmit);

        fullname = findViewById(R.id.editTextTutorName);
        Subject = findViewById(R.id.Subject);
        qualification = findViewById(R.id.editTextTutorSubject);
        description = findViewById(R.id.description);
        responseTextView = findViewById(R.id.responseTextView);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFormSubmit();
            }
        });
    }

    private void onFormSubmit() {
        JSONObject postData = new JSONObject();
        try {
            String tutorName = fullname.getText().toString().trim();  // Use "tutorName" as the variable name
            String subject = Subject.getText().toString().trim();
            String qualifications = qualification.getText().toString().trim();
            String tutorDescription = description.getText().toString().trim();  // Use "tutorDescription" as the variable name

            // Log output for debugging
            Log.d("Debug", "Name: " + tutorName);
            Log.d("Debug", "Subject: " + subject);
            Log.d("Debug", "Qualifications: " + qualifications);
            Log.d("Debug", "Description: " + tutorDescription);

            if (tutorName.isEmpty() || subject.isEmpty() || qualifications.isEmpty() || tutorDescription.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            postData.put("fullname", tutorName);  // Use "fullname" as the key
            postData.put("subject", subject);
            postData.put("qualification", qualifications);
            postData.put("description", tutorDescription);  // Use "description" as the key

            // Log output for debugging
            Log.d("Debug", "postData: " + postData.toString());

            String url_tutorbecome = "http://coms-309-063.class.las.iastate.edu:8080/tutors";

            networkManager.sendJsonObjectRequest(Request.Method.POST, url_tutorbecome, postData, response -> {
                responseTextView.setText("Form successfully submitted! Response: " + response.toString());
            }, error -> {
                Log.e("Network Error", "Error: " + error.toString());
                if (error.networkResponse != null) {
                    Log.e("Network Error Details", "Status code: " + error.networkResponse.statusCode);
                    Log.e("Network Error Details", "Data: " + new String(error.networkResponse.data));
                }
                if (error.getMessage() != null) {
                    Log.e("Network Error Message", error.getMessage());
                }
                responseTextView.setText("Submission failed! Please check logs for details.");
            });
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON Error", e.toString());
        }
    }
}
