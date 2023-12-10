package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SettingsFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("YourPrefsFile", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("userId", 72).apply();
    }

    @Test
    public void testFetchNotificationsSuccess() {
        // Launch the fragment
        activityRule.getScenario().onActivity(activity -> {
            Fragment fragment = new settings_fragment();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss();
        });


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the dialog with the title "New Notification" is displayed
        Espresso.onView(ViewMatchers.withText("New Notification"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
