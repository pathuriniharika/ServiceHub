package com.example.servicehub;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
    }

    @Test
    public void testTitleTextViewIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.titleTextView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testAnnouncementTextViewIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.announcementTextView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSearchEditTextIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testServiceCardsAreDisplayed() {
        // Assuming you have three service cards with ids service1Card, service2Card, and service3Card
        Espresso.onView(ViewMatchers.withId(R.id.service1Card))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.service2Card))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.service3Card))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testBottomNavigationItemClick() {

        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation))
                .perform(ViewActions.click());

    }

    @Test
    public void testSharedPreferencesUpdate() {
        // Update SharedPreferences values for testing
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hasNotifications", true);
        editor.putString("notification", "Sample Notification");
        editor.apply();


        boolean hasNotifications = sharedPreferences.getBoolean("hasNotifications", false);
        String notificationText = sharedPreferences.getString("notification", null);

        assertEquals(true, hasNotifications);
        assertEquals("Sample Notification", notificationText);
    }
}
