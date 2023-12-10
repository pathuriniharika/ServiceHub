package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    @Before
    public void setUp() {
        // Initialize Espresso Intents
        Intents.init();

        // Launch the SignUpActivity before each test
        ActivityScenario.launch(SignUpActivity.class);
    }

    @After
    public void tearDown() {
        // Finish the SignUpActivity after each test
        // No need to explicitly close the activity

        // Release Espresso Intents
        Intents.release();
    }

    @Test
    public void testValidSignUp() {
        // Type the necessary information into EditText fields
        Espresso.onView(ViewMatchers.withId(R.id.textPersonName))
                .perform(ViewActions.typeText("John Doe"));

        Espresso.onView(ViewMatchers.withId(R.id.textEmailAddress))
                .perform(ViewActions.typeText("john.doe@example.com"));

        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword))
                .perform(ViewActions.typeText("password123"));

        Espresso.onView(ViewMatchers.withId(R.id.passwordConfirm))
                .perform(ViewActions.typeText("password123"))
                .perform(ViewActions.closeSoftKeyboard()); // Close the keyboard


        // Click the create account button
        Espresso.onView(ViewMatchers.withId(R.id.createaccount))
                .perform(ViewActions.click());

        // Verify that the appropriate action is taken after successful signup
        // (you may check for a toast message or navigate to a new activity)
        // Intents.intended(IntentMatchers.hasComponent(YourNextActivity.class.getName()));
    }

    @Test
    public void testInvalidSignUp() {
        // Type invalid information into EditText fields
        Espresso.onView(ViewMatchers.withId(R.id.textPersonName))
                .perform(ViewActions.typeText(""));

        Espresso.onView(ViewMatchers.withId(R.id.textEmailAddress))
                .perform(ViewActions.typeText("invalid-email"));

        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword))
                .perform(ViewActions.typeText("short"));

        Espresso.onView(ViewMatchers.withId(R.id.passwordConfirm))
                .perform(ViewActions.typeText("mismatch"))
                .perform(ViewActions.closeSoftKeyboard()); // Close the keyboard


        // Click the create account button
        Espresso.onView(ViewMatchers.withId(R.id.createaccount))
                .perform(ViewActions.click());

        // Verify that the appropriate action is taken after failed signup
        // (you may check for a toast message indicating the error)
    }

    // Add more test cases as needed

}
