package com.example.servicehub;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CarpoolActivityTest {

    @Rule
    public ActivityTestRule<CarpoolActivity> activityRule =
            new ActivityTestRule<>(CarpoolActivity.class);


    @Test
    public void testSubmitCarpoolListing() {

        // Initialize SharedPreferences with a user ID for testing
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("userId", 73).apply();


        onView(withId(R.id.departureLocation)).perform(typeText("Departure Location"), closeSoftKeyboard());
        onView(withId(R.id.destination)).perform(typeText("Destination"), closeSoftKeyboard());
        onView(withId(R.id.capacityEditText)).perform(typeText("4"), closeSoftKeyboard());


        onView(withId(R.id.submitButton)).perform(click());




    }
    @Test
    public void testBackButton() {
        // Initialize Espresso Intents
        Intents.init();

        // Perform actions to click the "Back" button
        Espresso.onView(ViewMatchers.withId(R.id.backButton))
                .perform(ViewActions.click());

        // Use Espresso Intents to verify the intended navigation
        Intents.intended(IntentMatchers.hasComponent(ConsumerActivity.class.getName()));

        // Finish Espresso Intents
        Intents.release();
    }
}