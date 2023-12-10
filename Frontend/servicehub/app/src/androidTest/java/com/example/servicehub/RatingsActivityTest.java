package com.example.servicehub;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import android.widget.RatingBar;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

@RunWith(AndroidJUnit4.class)
public class RatingsActivityTest {

    @Rule
    public ActivityScenarioRule<Ratings> activityScenarioRule = new ActivityScenarioRule<>(Ratings.class);

    @Before
    public void setUp() {

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), Ratings.class);
        intent.putExtra("carpoolListingId", 1); //


        activityScenarioRule.getScenario().onActivity(activity -> {
            activity.startActivity(intent);
        });
    }


    @Test
    public void testSubmitReviewWithValidData() {
        // Enter a review in the userReviewEditText
        onView(withId(R.id.userReviewEditText))
                .perform(ViewActions.click()) // Focus on the EditText
                .perform(ViewActions.typeText("This is a great carpool service"));

        // Set a rating using the custom action
        onView(withId(R.id.ratingBar)).perform(RatingsBarAction.setRating(4.0f));

        // Click the Submit Review button
        onView(withId(R.id.submitReviewButton)).perform(ViewActions.click());


    }

    @Test
    public void testSubmitReviewWithEmptyReview() {
        // Do not enter anything in the review EditText

        // Set a rating using the RatingBar
        onView(withId(R.id.ratingBar)).perform(RatingsBarAction.setRating(4.0f)); // Set the rating to 4 stars

        // Click the Submit Review button
        onView(withId(R.id.submitReviewButton)).perform(click());

        // Check if a toast message is displayed indicating that a review is required
        onView(withId(R.id.averageRatingTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Average Rating: Not available")));
    }

    @Test
    public void testSubmitReviewWithNoRating() {
        // Enter a review in the EditText
        onView(withId(R.id.userReviewEditText)).perform(typeText("This is a great carpool service"));

        // Set no rating using the RatingBar (rating is 0)

        // Click the Submit Review button
        onView(withId(R.id.submitReviewButton)).perform(click());

        // Check if a toast message is displayed indicating that a rating is required
        onView(withId(R.id.averageRatingTextView)).check(matches(withText("Average Rating: Not available")));
    }

    // You can add more test cases to cover other scenarios as needed
}
