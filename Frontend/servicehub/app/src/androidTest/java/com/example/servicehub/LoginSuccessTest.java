package com.example.servicehub;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginSuccessTest {

    @Rule
    public ActivityScenarioRule<loginSuccess> activityRule = new ActivityScenarioRule<>(loginSuccess.class);

    @Test
    public void testUserProfileButtonClick() {
        // Initialize Intents before the test
        Intents.init();

        onView(withId(R.id.userprofile)).perform(click());

        // Verify that an intent to UserActivity was initiated
        Intents.intended(IntentMatchers.hasComponent(UserActivity.class.getName()));

        // Release Intents after the test
        Intents.release();
    }

    @Test
    public void testHomeButtonClick() {
        // Initialize Intents before the test
        Intents.init();

        onView(withId(R.id.homeButton)).perform(click());

        // Verify that an intent to MainActivity was initiated
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));

        // Release Intents after the test
        Intents.release();
    }
}

