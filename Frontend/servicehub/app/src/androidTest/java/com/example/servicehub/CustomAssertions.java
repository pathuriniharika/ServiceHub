package com.example.servicehub;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

public class CustomAssertions {

    public static ViewAssertion withErrorText(String expectedErrorText) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }

                assertThat(view, allOf(isDisplayed(), ViewMatchers.withText(expectedErrorText)));
            }
        };
    }
}
