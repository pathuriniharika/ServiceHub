package com.example.servicehub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutAdminActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private Button banUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_nav);

        userIdEditText = findViewById(R.id.etUserId);
        banUserButton = findViewById(R.id.btnBanUser);

        banUserButton.setOnClickListener(v -> {
            // Logic to ban user
            String userId = userIdEditText.getText().toString().trim();
            banUser(userId);
        });
    }

    private void banUser(String userId) {
        // Implement the logic to ban a user based on the user ID
    }
}