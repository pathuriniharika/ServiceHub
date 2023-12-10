package com.example.servicehub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

public class TutorBecomeActivityTest {

    @Rule
    public ActivityScenarioRule<tutor_become> activityRule = new ActivityScenarioRule<>(tutor_become.class);

    @Test
    public void testSuccessfulFormSubmission() {
        Intents.init();

        // Enter valid input data in the form
        Espresso.onView(ViewMatchers.withId(R.id.editTextTutorName))
                .perform(ViewActions.typeText("John Doe"));
        Espresso.onView(ViewMatchers.withId(R.id.Subject))
                .perform(ViewActions.typeText("Math"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextTutorSubject))
                .perform(ViewActions.typeText("Ph.D. in Mathematics"));
        Espresso.onView(ViewMatchers.withId(R.id.description))
                .perform(ViewActions.typeText("Experienced math tutor"));

        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit))
                .perform(ViewActions.click());




        Intents.release();
    }


    @Test
    public void testFormSubmissionWithNetworkResponse() {

        String testName = "Test Name";
        String testSubject = "Test Subject";
        String testQualification = "Test Qualification";
        String testDescription = "Test Description";

        Espresso.onView(ViewMatchers.withId(R.id.editTextTutorName)).perform(ViewActions.typeText(testName), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.Subject)).perform(ViewActions.typeText(testSubject), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextTutorSubject)).perform(ViewActions.typeText(testQualification), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.description)).perform(ViewActions.typeText(testDescription), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit)).perform(ViewActions.click());


    }
    @Test
    public void testEmptyFormSubmission() {

        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit))
                .perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(android.R.id.content))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }



}

