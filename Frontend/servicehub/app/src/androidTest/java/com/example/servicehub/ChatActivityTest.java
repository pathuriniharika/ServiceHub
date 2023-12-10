package com.example.servicehub;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ChatActivityTest {

    @Rule
    public ActivityScenarioRule<ChatActivity> activityScenarioRule = new ActivityScenarioRule<>(ChatActivity.class);

    @Test
    public void testSendMessage() {
        // Replace these with the actual resource IDs of your UI elements
        // For example, R.id.msg and R.id.send
        String message = "Hello, this is a test message";

        // Find the message input field and type the message
        Espresso.onView(ViewMatchers.withId(R.id.msg)).perform(ViewActions.typeText(message));

        // Find the send button and click it
        Espresso.onView(ViewMatchers.withId(R.id.send)).perform(ViewActions.click());

        // Add any assertions based on the expected behavior of your app
        // For example, you might check if the message is displayed in the chat view
        // using onView(withId(R.id.msgdisplay)).check(matches(withText(message)));
    }

    /*@Test
    public void testTypingIndicator() {
        // Replace with the actual resource ID of your typing indicator
        // For example, R.id.typingIndicator
        Espresso.onView(ViewMatchers.withId(R.id.typingIndicator)).check(ViewAssertions.matches(ViewMatchers.withText("User id: Typing...")));
    }*/

    @Test
    public void testCharCount() {
        // Replace with the actual resource ID of your char count TextView
        // For example, R.id.charCount
        Espresso.onView(ViewMatchers.withId(R.id.charCount)).check(ViewAssertions.matches(ViewMatchers.withText("0/1000")));
    }

    @Test
    public void testEndConversationButton() {
        // Replace with the actual resource ID of your End Conversation button
        // For example, R.id.endConversation
        Espresso.onView(ViewMatchers.withId(R.id.endConversation)).perform(ViewActions.click());

        // Add any assertions based on the expected behavior of your app
        // For example, you might check if the chat has ended and you are redirected to the expected activity
        // using intended(hasComponent(HomeCleaning.class.getName()));
    }
}
