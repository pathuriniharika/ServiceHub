package com.example.servicehub;/*package com.example.servicehub;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class FetchServicesTest {

    @Rule
    public ActivityScenarioRule<FetchServices> activityRule = new ActivityScenarioRule<>(FetchServices.class);

    @Before
    public void setUp() {
        Intents.init();
        // Assuming that the user ID and lister ID are successfully fetched
        setUserIdInPreferences(ApplicationProvider.getApplicationContext(), 1);
        setListerIdInPreferences(ApplicationProvider.getApplicationContext(), 1);
    }

    @Test
    public void testShowAllListings() {
        // Click the "Show All Listings" button
        Espresso.onView(ViewMatchers.withId(R.id.showAllListingsButton)).perform(click());

        // Check that the listings TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.listingsTextView)).check(matches(isDisplayed()));
    }

    // Add other test cases as needed

    @Before
    public void tearDown() {
        Intents.release();
    }

    private void setUserIdInPreferences(Context context, int userId) {
        TestUtils.setUserIdInPreferences(context, userId);
    }

    private void setListerIdInPreferences(Context context, int listerId) {
        SharedPreferences preferences = context.getSharedPreferences("service_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lister_id", listerId);
        editor.apply();
    }
}*/

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.SharedPreferences;

@RunWith(AndroidJUnit4.class)
public class FetchServicesTest {

    @Rule
    public ActivityScenarioRule<FetchServices> activityRule = new ActivityScenarioRule<>(FetchServices.class);

    private IdlingResource idlingResource;

    @Before
    public void setUp() {
        // Register the idling resource to wait for network requests
        idlingResource = FetchServicesIdlingResource.getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);

        // Assuming that the user ID and lister ID are successfully fetched
        setUserIdInPreferences(ApplicationProvider.getApplicationContext(), 1);
        setListerIdInPreferences(ApplicationProvider.getApplicationContext(), 1);
    }

    @Test
    public void testShowAllListings() {
        // Click the "Show All Listings" button
        Espresso.onView(ViewMatchers.withId(R.id.showAllListingsButton)).perform(click());

        // Check that the listings TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.listingsTextView)).check(matches(isDisplayed()));
    }

   /*@Test
public void testDisplayedListingsFormat() {
    // Mock the network response with a single listing
    String mockResponse = "[{\"lister\":{\"id\":34,\"name\":\"sus\",\"email\":\"sus@gmail.com\",\"password\":\"newPassword\",\"confirmPassword\":\"123\",\"ifActive\":false,\"status\":\"PROVIDER\",\"notification\":null,\"rating\":0.0},\"id\":34,\"serviceType\":\"\",\"description\":\"\",\"price\":0.0,\"user\":null}]";
    FetchServicesIdlingResource.mockResponse(mockResponse);

    // Click the "Show All Listings" button
    Espresso.onView(ViewMatchers.withId(R.id.showAllListingsButton)).perform(click());

    // Check that the formatted listings are displayed
    Espresso.onView(ViewMatchers.withId(R.id.listingsTextView)).check(matches(withText(
            "Lister ID: 34\n" +
                    "Lister Name: sus\n" +
                    "Email: sus@gmail.com\n" +
                    "Password: newPassword\n" +
                    "Confirm Password: 123\n" +
                    "Active: false\n" +
                    "Status: PROVIDER\n" +
                    "Rating: 0.0\n" +
                    "Service Type: \n" +
                    "Description: \n" +
                    "Price: $0.0\n\n"
    )));
}*/


    @Test
    public void testNoListingsFound() {
        // Mock the network response with an empty list
        String mockEmptyResponse = "[]";
        FetchServicesIdlingResource.mockResponse(mockEmptyResponse);

        // Click the "Show All Listings" button
        Espresso.onView(ViewMatchers.withId(R.id.showAllListingsButton)).perform(click());

        // Check that the "No listings found." message is displayed
        Espresso.onView(ViewMatchers.withId(R.id.listingsTextView)).check(matches(withText("No listings found.")));
    }

    @After
    public void tearDown() {
        // Unregister the idling resource after the test
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    private void setUserIdInPreferences(Context context, int userId) {
        TestUtils.setUserIdInPreferences(context, userId);
    }

    private void setListerIdInPreferences(Context context, int listerId) {
        SharedPreferences preferences = context.getSharedPreferences("service_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lister_id", listerId);
        editor.apply();
    }
}
