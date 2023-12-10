package com.example.servicehub;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CarpoolHomeTest {

    @Rule
    public ActivityScenarioRule<carpool_home> activityRule = new ActivityScenarioRule<>(carpool_home.class);

    @Test
    public void testButton2OpensCarpoolSearch() {
        Intents.init();
        onView(withId(R.id.button2)).perform(click());
        intended(IntentMatchers.hasComponent(carpool_search.class.getName()));
        Intents.release();
    }

    @Test
    public void testButton3OpensBookingHistory() {
        Intents.init();
        onView(withId(R.id.button3)).perform(click());
        intended(IntentMatchers.hasComponent(booking_history.class.getName()));
        Intents.release();
    }

    @Test
    public void testButton4OpensRatings() {
        Intents.init();
        onView(withId(R.id.button4)).perform(click());
        intended(IntentMatchers.hasComponent(Ratings.class.getName()));
        Intents.release();
    }
}
