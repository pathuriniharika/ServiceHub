package com.example.servicehub;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.widget.EditText;
import android.widget.RatingBar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AppRateActivityTest {

    @Rule
    public ActivityScenarioRule<AppRateActivity> activityScenarioRule = new ActivityScenarioRule<>(AppRateActivity.class);

    @Before
    public void setUp() {
        // Set up any necessary data or mocks
    }

    @Test
    public void testSubmitRatingWithValidData() {
        // Enter a double rating in the doubleRatingEditText
        onView(withId(R.id.doubleRatingEditText))
                .perform(typeText("4.5"));

        // Set a rating using the RatingBar
        onView(withId(R.id.ratingBar))
                .perform(RatingsBarAction.setRating(4.5f));

        // Click the Submit Rating button
        onView(withId(R.id.rateNowBtn))
                .perform(click());

        // Add assertions to check if the desired behavior occurs after submitting a rating
        // For example, you can check if a success message is displayed or navigate to another activity
    }

    @Test
    public void testSubmitRatingWithEmptyDoubleRating() {
        // Do not enter anything in the doubleRatingEditText

        // Set a rating using the RatingBar
        onView(withId(R.id.ratingBar))
                .perform(RatingsBarAction.setRating(3.0f));

        // Click the Submit Rating button
        onView(withId(R.id.rateNowBtn))
                .perform(click());

        // Add assertions to check if the desired behavior occurs after submitting a rating with an empty double rating
        // For example, you can check if an error message is displayed
    }

    @Test
    public void testSubmitRatingWithEmptyRatingBar() {
        // Enter a double rating in the doubleRatingEditText
        onView(withId(R.id.doubleRatingEditText))
                .perform(typeText("4.0"));

        // Do not set a rating using the RatingBar (leave it at 0)

        // Click the Submit Rating button
        onView(withId(R.id.rateNowBtn))
                .perform(click());

        // Add assertions to check if the desired behavior occurs after submitting a rating with an empty RatingBar
        // For example, you can check if an error message is displayed
    }

    // Add more test cases as needed to cover different scenarios
}
