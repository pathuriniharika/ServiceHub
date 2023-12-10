package com.example.servicehub;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.Lifecycle;

public class TestUtils {

    private static Context appContext;

    public static void setUserIdInPreferences(Context context, int userId) {
        SharedPreferences preferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }

    public static void setListerIdInPreferences(Context context, int listerId) {
        SharedPreferences preferences = context.getSharedPreferences("service_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lister_id", listerId);
        editor.apply();
    }

    public static void sleepFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void setUserIdInPreferences(Lifecycle.State state, int userId) {
        // You can check the lifecycle state and perform actions accordingly
        if (state == Lifecycle.State.CREATED || state == Lifecycle.State.STARTED || state == Lifecycle.State.RESUMED) {
            if (appContext != null) {
                // For example, save userId to preferences when the lifecycle is active
                SharedPreferences preferences = appContext.getSharedPreferences("user_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("userId", userId);
                editor.apply();
            }
        } else {
            // Handle other lifecycle states if needed
        }
    }



}
