package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.After;

@RunWith(AndroidJUnit4.class)
public class AdminTest {

    @Rule
    public ActivityScenarioRule<admin> activityRule = new ActivityScenarioRule<>(admin.class);

    @Before
    public void setUp() {
        Intents.init(); // Initialize Intents before the tests
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after tests are complete
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && ((ViewGroup) parent).getChildCount() > position
                        && ((ViewGroup) parent).getChildAt(position).equals(view);
            }
        };
    }

    @Test
    public void testNavigateToHomeAdmin() {
        Espresso.onView(childAtPosition(
                Matchers.allOf(ViewMatchers.withId(R.id.banIcon),
                        ViewMatchers.isDisplayed()), 0)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(ban_user.class.getName()));
    }

    @Test
    public void testNavigateToViewAccounts() {
        Espresso.onView(childAtPosition(
                Matchers.allOf(ViewMatchers.withId(R.id.viewIcon),
                        ViewMatchers.isDisplayed()), 0)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(ViewAccountsActivity.class.getName()));
    }

    @Test
    public void testNavigateToAnnouncement() {
        Espresso.onView(childAtPosition(
                Matchers.allOf(ViewMatchers.withId(R.id.announcementsIcon),
                        ViewMatchers.isDisplayed()), 0)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(AnnouncementsActivity.class.getName()));
    }

    @Test
    public void testNavigateToLogOut() {
        Espresso.onView(childAtPosition(
                Matchers.allOf(ViewMatchers.withId(R.id.logoutIcon),
                        ViewMatchers.isDisplayed()), 0)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(first_page.class.getName()));
    }


}
