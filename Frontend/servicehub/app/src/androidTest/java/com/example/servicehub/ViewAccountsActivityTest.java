package com.example.servicehub;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.example.servicehub.ToastMatcher.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ViewAccountsActivityTest {

    @Before
    public void setUp() {
        // Launch the activity under test
        ActivityScenario.launch(ViewAccountsActivity.class);
    }

    @Test
    public void testEmptySearchShowsAllUsers() {
        // Simulate an empty search (no input in the search field)
        onView(withId(R.id.searchButton)).perform(ViewActions.click());

        // Verify that the responseTextView contains user information (assuming there are users in the response)
        onView(withId(R.id.responseTextView))
                .check(matches(Matchers.not(ViewMatchers.withText("No users found."))));
    }

    @Test
    public void testKeyboardActions() {

        onView(withId(R.id.searchEditText)).perform(typeText("John Doe"), pressImeActionButton());


    }

    @Test
    public void testSuccessfulSearch() {
        // Simulate a successful search for a specific user (replace "John" with a valid user name)
        onView(withId(R.id.searchEditText)).perform(typeText("John"));
        onView(withId(R.id.searchButton)).perform(ViewActions.click());

        // Verify that the responseTextView contains the user's information (assuming "John" exists)
        onView(withId(R.id.responseTextView))
                .check(matches(ViewMatchers.withText(Matchers.containsString("John"))));
    }

    @Test
    public void testUnsuccessfulSearch() {
        // Simulate an unsuccessful search for a user that doesn't exist
        onView(withId(R.id.searchEditText)).perform(typeText("NonExistentName"));
        onView(withId(R.id.searchButton)).perform(ViewActions.click());

        // Verify that the responseTextView displays "No matching users found."
        onView(withId(R.id.responseTextView))
                .check(matches(ViewMatchers.withText("No matching users found.")));
    }

    @Test
    public void testSearchAndClear() {
        // Simulate a search and then clear the search field
        onView(withId(R.id.searchEditText)).perform(typeText("leha"));
        onView(withId(R.id.searchButton)).perform(ViewActions.click());

        // Verify that the responseTextView contains user information (assuming "Alice" exists)
        onView(withId(R.id.responseTextView))
                .check(matches(ViewMatchers.withText(Matchers.containsString("Leha"))));

        // Clear the search field
        onView(withId(R.id.searchEditText)).perform(ViewActions.clearText());
        onView(withId(R.id.searchButton)).perform(ViewActions.click());


    }
}

