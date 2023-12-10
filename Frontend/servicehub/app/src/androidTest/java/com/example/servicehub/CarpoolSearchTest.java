package com.example.servicehub;

import androidx.cardview.widget.CardView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CarpoolSearchTest {

    @Rule
    public ActivityScenarioRule<carpool_search> activityRule = new ActivityScenarioRule<>(carpool_search.class);

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences preferences = context.getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        preferences.edit().putInt("userId", 72).apply();

        Intents.init();
    }

    @Test
    public void testRecyclerViewVisibility() {
        Espresso.onView(Matchers.allOf(withId(R.id.recyclerView), isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testRefreshButtonFunctionality() {
        Espresso.onView(withId(R.id.refreshButton))
                .perform(ViewActions.click());
    }

    @Test
    public void testSearchFunctionality() {
        Espresso.onView(withId(R.id.searchEditText))
                .perform(ViewActions.typeText("Des Moines"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.searchButton))
                .perform(ViewActions.click());
    }

    @Test
    public void testEmptySearchQuery() {
        Espresso.onView(withId(R.id.searchEditText))
                .perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.searchButton))
                .perform(ViewActions.click());
    }


   /* @Test
    public void testRecyclerViewItemClick() {
        Espresso.onView(Matchers.allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
    }*/

    @Test
    public void testRefreshButtonState() {
        Espresso.onView(withId(R.id.refreshButton))
                .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

//    @Test
//    public void testJoinButtonFunctionality() {
//        // Perform the click action on the join button within the first item of the RecyclerView
//        Espresso.onView(withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickOnJoinButtonInCard()));
//
//        // Verify that MapActivity is started
//        intended(hasComponent(MapActivity.class.getName()));
//    }

    // Clean up after each test
    @After
    public void tearDown() {
        Intents.release();
    }

    // Helper method to perform click on the join button inside a card view in the RecyclerView item
    private static ViewAction clickOnJoinButtonInCard() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on join button inside card view.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                // Assuming the card view is the root of the RecyclerView item layout
                CardView cardView = (CardView) view;
                Button joinButton = cardView.findViewById(R.id.joinButton); // replace with your join button ID
                if (joinButton != null) {
                    joinButton.performClick();
                }
            }
        };
    }
}
