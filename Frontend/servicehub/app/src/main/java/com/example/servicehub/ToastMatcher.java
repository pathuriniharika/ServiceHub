package com.example.servicehub;
import android.view.View;
import android.widget.TextView;
import androidx.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ToastMatcher {

    public static Matcher<Object> withText(final String message) {
        // Use a BoundedMatcher to match the text content of the Toast
        return new BoundedMatcher<Object, android.widget.Toast>(android.widget.Toast.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("is Toast with text: " + message);
            }

            @Override
            protected boolean matchesSafely(android.widget.Toast toast) {
                try {
                    // Get the toast's view
                    View view = toast.getView();

                    // Find the TextView inside the toast's layout
                    TextView textView = view.findViewById(android.R.id.message);

                    // Check if the text matches the expected message
                    return textView != null && textView.getText().toString().equals(message);
                } catch (Exception e) {
                    // Exception may occur if toast is not displayed
                    return false;
                }
            }
        };
    }
}
