package com.example.servicehub;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;

import com.example.servicehub.admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class loginActivityTest {

    @Before
    public void setUp() {
        // Initialize Espresso Intents
        Intents.init();

        // Launch the LoginActivity before each test
        ActivityScenario.launch(LoginActivity.class);
    }

    @After
    public void tearDown() {
        // Finish the LoginActivity after each test
        // No need to explicitly close the activity

        // Release Espresso Intents
        Intents.release();
    }

    @Test
    public void testValidAdminLogin() {
        // Type the email and password into EditText fields
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.typeText("administrator"));


        Espresso.onView(ViewMatchers.withId(R.id.passwordlogin))
                .perform(ViewActions.typeText("admin"))
                .perform(ViewActions.closeSoftKeyboard()); // Close the keyboard

        // Click the login button
        Espresso.onView(ViewMatchers.withId(R.id.login_button))
                .perform(ViewActions.click());

        // Verify that the admin activity is launched


        Intents.intended(IntentMatchers.hasComponent(admin.class.getName()));
    }

    @Test
    public void testInvalidLogin() {
        // Enter invalid credentials
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.typeText("invalid@example.com"));

        Espresso.onView(ViewMatchers.withId(R.id.passwordlogin))
                .perform(ViewActions.typeText("invalidpassword"))
                .perform(ViewActions.closeSoftKeyboard()); // Close the keyboard

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
                .perform(ViewActions.click());
//
//         Verify that no intent is sent (stay on login page)
        Intents.intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
    }

    @Test
    public void testEmptyLoginForm() {
        // Leave both email and password fields empty
        Espresso.onView(ViewMatchers.withId(R.id.login_button))
                .perform(ViewActions.click());


        Intents.intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
    }

    @Test
    public void testValidLogin() {
        // Assuming you have set up a test database with a valid user
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.typeText("seeker"));

        Espresso.onView(ViewMatchers.withId(R.id.passwordlogin))
                .perform(ViewActions.typeText("seeker1"))
                .perform(ViewActions.closeSoftKeyboard()); // Close the keyboard

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
                .perform(ViewActions.click());

//        Intents.intended(IntentMatchers.hasComponent(loginSuccess.class.getName()));
    }


}
