package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RequestTutorActivityTest {

    @Rule
    public ActivityScenarioRule<request_tutor> activityRule = new ActivityScenarioRule<>(request_tutor.class);

    @Test
    public void testSearchFunctionality() {
        // Type in the search query
        Espresso.onView(ViewMatchers.withId(R.id.searchNameField))
                .perform(ViewActions.typeText("raghu"), ViewActions.closeSoftKeyboard());

        // Click the search button
        Espresso.onView(ViewMatchers.withId(R.id.searchButton))
                .perform(ViewActions.click());

        // Add assertions to check if the RecyclerView updates as expected
    }

   /* @Test
    public void testRecyclerViewInteraction() {

        Espresso.onView(ViewMatchers.withId(R.id.tutorRecyclerView))
                .perform(RecyclerViewActions.scrollToPosition(5));


        Espresso.onView(ViewMatchers.withId(R.id.tutorRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));


    }*/

    /*@Test
    public void testNavigationToBooking() {

        Espresso.onView(ViewMatchers.withId(R.id.tutorRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));


    }*/
}
