package com.example.servicehub;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ScrollView;
import android.widget.TextView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class BookingServiceTest {

    @Rule
    public ActivityScenarioRule<BookingService> activityScenarioRule = new ActivityScenarioRule<>(BookingService.class);

    @Before
    public void setUp() {
        // Set up any necessary data or mocks
    }

    @Test
    public void testBookNowButtonClicked() {
        // Click the "Book Now" button
        onView(withId(R.id.bookNowButton))
                .perform(click());

        // Check if BookingSlotActivity is launched
        onView(withId(R.id.BookingSlotActivity)) // Adjust with the actual layout ID of BookingSlotActivity
                .check(matches(isDisplayed()));
    }

    @Test
    public void testChatNowButtonClicked() {
        // Click the "Chat Now" button
        onView(withId(R.id.chatNowButton))
                .perform(click());

        // Check if ChatActivity is launched
        onView(withId(R.id.ChatActivity)) // Adjust with the actual layout ID of ChatActivity
                .check(matches(isDisplayed()));
    }

   /* @Test
    public void testRefreshButtonClicked() {
        // Click the "Refresh" button
        onView(withId(R.id.refreshButton))
                .perform(click());

        // Check if the serviceTextView is updated with new data
        onView(withId(R.id.serviceTextView))
                .check(matches(isDisplayed()));

        // You can add more assertions to check the specific content or behavior after refreshing
    }*/

    // You can add more test cases as needed to cover different scenarios

    // Helper method to set up a fake user ID in SharedPreferences
    private void setFakeUserId(int userId) {
        SharedPreferences preferences = ApplicationProvider.getApplicationContext().getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        preferences.edit().putInt("userId", userId).apply();
    }
}
