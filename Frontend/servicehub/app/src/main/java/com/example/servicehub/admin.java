package com.example.servicehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
    }


    public void navigateToHomeAdmin(View view) {

        Intent intent = new Intent(this, ban_user.class);
        startActivity(intent);
    }


    public void navigateToViewAccounts(View view) {

        Intent intent = new Intent(this, ViewAccountsActivity.class);
        startActivity(intent);
    }


    public void navigateToAnnouncements(View view) {

        Intent intent = new Intent(this, AnnouncementsActivity.class);
        startActivity(intent);
    }



    public void navigateToLogoutAdmin(View view) {

        Intent intent = new Intent(this, first_page.class);
        startActivity(intent);
    }


}
