package com.example.servicehub;

import android.content.Intent;
import android.util.Log;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MapActivityTest {
    private static final String TAG = "MapActivityTest";




    @Rule
    public ActivityScenarioRule<MapActivity> activityScenarioRule = new ActivityScenarioRule<>(MapActivity.class);

    @Test
    public void testMapActivityUIElements() {
        Log.d(TAG, "Starting testMapActivityUIElements");



            Espresso.onView(ViewMatchers.withId(R.id.mapFragment));

        Log.d(TAG, "Finished testNavigateFunctionality");

    }

//    @Test
//    public void testNavigateButtonIsDisplayed() {
//        Espresso.onView(ViewMatchers.withId(R.id.navigationButton))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//    }
    private class WaitForResumedIdlingResource implements IdlingResource {
        private boolean idle = false;
        private ResourceCallback resourceCallback;

        @Override
        public String getName() {
            return "WaitForResumedIdlingResource";
        }

        @Override
        public boolean isIdleNow() {
            if (idle) {
                resourceCallback.onTransitionToIdle();
            }
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
            this.resourceCallback = resourceCallback;
        }

        public void setIdle(boolean idle) {
            this.idle = idle;
        }
    }
}
