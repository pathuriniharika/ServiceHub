package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

public class ListingServiceTest {

    @Rule
    public ActivityScenarioRule<ListingService> activityRule = new ActivityScenarioRule<>(ListingService.class);

    @Test
    public void testSuccessfulServiceListing() {
        Intents.init();

        // Enter valid input data in the form
        Espresso.onView(ViewMatchers.withId(R.id.TextName))
                .perform(ViewActions.typeText("Cleaning"));
        Espresso.onView(ViewMatchers.withId(R.id.TextPrice))
                .perform(ViewActions.typeText("50.00"));
        Espresso.onView(ViewMatchers.withId(R.id.TextDescription))
                .perform(ViewActions.typeText("Professional cleaning services"));

        // Click the list service button
        Espresso.onView(ViewMatchers.withId(R.id.buttonListService))
                .perform(ViewActions.click());

        // Verify that the appropriate action is taken after successful listing
        // (you may check for a toast message or navigate to a new activity)
        // Intents.intended(IntentMatchers.hasComponent(YourNextActivity.class.getName()));

        Intents.release();
    }

   /* @Test
    public void testEmptyServiceListing() {
        // Leave all fields empty

        // Click the list service button
        Espresso.onView(ViewMatchers.withId(R.id.buttonListService))
                .perform(ViewActions.click());

        // Verify that an appropriate error message is displayed
        Espresso.onView(ViewMatchers.withText("Error: Please fill in all fields"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }*/

    /*@Test
    public void testServiceListingWithNetworkError() {
        // Simulate a network error during service listing

        // Set up a test environment to force a network error (mock server, etc.)

        // Enter valid input data in the form
        Espresso.onView(ViewMatchers.withId(R.id.TextName))
                .perform(ViewActions.typeText("Plumbing"));
        Espresso.onView(ViewMatchers.withId(R.id.TextPrice))
                .perform(ViewActions.typeText("75.00"));
        Espresso.onView(ViewMatchers.withId(R.id.TextDescription))
                .perform(ViewActions.typeText("Professional plumbing services"));

        // Click the list service button
        Espresso.onView(ViewMatchers.withId(R.id.buttonListService))
                .perform(ViewActions.click());

        // Verify that an appropriate error message is displayed
        Espresso.onView(ViewMatchers.withText("Error: Network error"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }*/


}
