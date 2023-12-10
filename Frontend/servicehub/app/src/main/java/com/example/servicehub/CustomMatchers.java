package com.example.servicehub;

import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


public class CustomMatchers {
        public static TypeSafeMatcher<View> isVisible() {
            return new TypeSafeMatcher<View>() {
                @Override
                protected boolean matchesSafely(View item) {
                    return item != null && item.getVisibility() == View.VISIBLE;
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("View is visible");
                }
            };
        }
    }




