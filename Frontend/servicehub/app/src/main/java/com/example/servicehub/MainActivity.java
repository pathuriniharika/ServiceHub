package com.example.servicehub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.servicehub.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.badge.BadgeDrawable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private static final String TAG = "MainActivity"; // Added for debugging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("YourPrefsFile", MODE_PRIVATE);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.profile) {
                    selectedFragment = new profile_fragment();
                } else if (item.getItemId() == R.id.settings) {
                    selectedFragment = new settings_fragment();

                    // Hide the badge when "Settings" tab is selected
                    hideBadge();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Update the badge on activity creation
        updateBadge();
    }

    private void updateBadge() {

        boolean hasNotifications = sharedPreferences.getBoolean("hasNotifications", false);


        String notificationText = sharedPreferences.getString("notification", null);


        BadgeDrawable badgeDrawable = binding.bottomNavigation.getOrCreateBadge(R.id.settings);


        if (hasNotifications && notificationText != null && !notificationText.equals("No notification available for the user")) {
            badgeDrawable.setVisible(true);
        } else {
            badgeDrawable.setVisible(false);
        }
    }





    private void hideBadge() {

        BadgeDrawable badgeDrawable = binding.bottomNavigation.getBadge(R.id.settings);


        if (badgeDrawable != null) {
            badgeDrawable.setVisible(false);
        }
    }
}
