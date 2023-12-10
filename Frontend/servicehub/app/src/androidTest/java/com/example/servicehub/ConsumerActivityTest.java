package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ConsumerActivityTest {

    @Rule
    public ActivityScenarioRule<ConsumerActivity> activityRule = new ActivityScenarioRule<>(ConsumerActivity.class);

    @Before
    public void setUp() {
        Intents.init(); // Initialize Intents before the tests
    }



    @Test
    public void testNewBoxCardClick() {

        onView(withId(R.id.newBoxCard)).perform(click());
        intended(IntentMatchers.hasComponent(SignUpActivity.class.getName()));

    }

    @Test
    public void testCardView1Click() {

        onView(withId(R.id.cardView1)).perform(click());
        intended(IntentMatchers.hasComponent(ListingService.class.getName()));

    }

    @Test
    public void testCardView2Click() {

        onView(withId(R.id.cardView2)).perform(click());
        intended(IntentMatchers.hasComponent(tutor_become.class.getName()));

    }

    @Test
    public void testCardView3Click() {

        onView(withId(R.id.cardView3)).perform(click());
        intended(IntentMatchers.hasComponent(carpool_listers.class.getName()));

    }

    @Test
    public void testSearchButtonClick() {
        onView(withId(R.id.searchButton)).perform(click());
    }

    @After
    public void tearDown() {
        Intents.release();
    }
}
