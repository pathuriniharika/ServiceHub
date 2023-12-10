package com.example.servicehub;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import android.view.View;
import android.widget.RatingBar;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.StringDescription;
import java.util.Iterator;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;

public class RatingsBarAction {

    public static ViewAction setRating(final float rating) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(
                        isAssignableFrom(RatingBar.class),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE));
            }

            @Override
            public String getDescription() {
                return "Set rating on RatingBar";
            }

            @Override
            public void perform(UiController uiController, View view) {
                RatingBar ratingBar = (RatingBar) view;
                ratingBar.setRating(rating);
            }
        };
    }
}
