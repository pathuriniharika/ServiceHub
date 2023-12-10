package com.example.servicehub;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.material.snackbar.Snackbar.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BanUserActivityTest {

    @Rule
    public ActivityScenarioRule<ban_user> activityRule =
            new ActivityScenarioRule<>(ban_user.class);

    @Before
    public void setUp() throws Exception {
        // Initialize Intents before each test
        init();
    }
    @After
    public void tearDown() throws Exception {
        // Release Intents after each test
        release();
    }
    @Test
    public void testBanUserSuccess() {
        onView(withId(R.id.etUserId)).perform(typeText("37"));
        onView(withId(R.id.btnBanUser)).perform(click());

        // Check if the Snackbar with success message is displayed
        onView(withText("User banned successfully")).check(matches(isDisplayed()));
    }

    @Test
    public void testBanUserError() {
        onView(withId(R.id.etUserId)).perform(typeText("invalid_user_id"));
        onView(withId(R.id.btnBanUser)).perform(click());

        // Check if the Snackbar with error message is displayed
        onView(withText("Error: User not found or unable to ban")).check(matches(isDisplayed()));
    }

    @Test
    public void testBanUserWithEmptyInput() {
        onView(withId(R.id.btnBanUser)).perform(click());

        // Assuming your app should display an error or warning when no user ID is entered
        onView(withText("Please enter a user ID")).check(matches(isDisplayed()));
    }

    @Test
    public void testBackButtonNavigation() {
        onView(withId(R.id.btnBack)).perform(click());


        intended(hasComponent(admin.class.getName()));
    }
}
