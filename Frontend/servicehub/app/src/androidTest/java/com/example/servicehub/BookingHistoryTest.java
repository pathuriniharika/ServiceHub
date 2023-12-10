package com.example.servicehub;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class BookingHistoryTest {

    @Rule
    public ActivityScenarioRule<BookingHistory> activityRule = new ActivityScenarioRule<>(BookingHistory.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testShowAllBookings() {
        // Assuming that the user ID is successfully fetched
        setUserIdInPreferences(ApplicationProvider.getApplicationContext(), 1);

        // Click the "Show All Bookings" button
        Espresso.onView(ViewMatchers.withId(R.id.showAllBookingsButton)).perform(click());

        // Check that the response TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.bookingsTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testFormatAndDisplayBookings() {
        // Assuming that the user ID is successfully fetched
        TestUtils.setUserIdInPreferences(activityRule.getScenario().getState(), 1);

        // Click the "Show All Bookings" button
        Espresso.onView(ViewMatchers.withId(R.id.showAllBookingsButton)).perform(click());

        // Check that the response TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.bookingsTextView)).check(matches(isDisplayed()));
    }

    @Before
    public void tearDown() {
        Intents.release();
    }

    private void setUserIdInPreferences(Context context, int userId) {
        // Use InstrumentationRegistry to get the Context
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Set the user ID in preferences
        TestUtils.setUserIdInPreferences(appContext, userId);
    }
}
