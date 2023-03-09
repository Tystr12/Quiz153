package no.hvl.quiz153;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizActivityTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(QuizActivity.class);
        Espresso.onView(ViewMatchers.withId(R.id.button_answer1)).perform(ViewActions.click());
    }

    @Test
    public void testScoreUpdateOnCorrectAnswer() {
        int initialScore = 0;
        int expectedScore = initialScore + 1;
        Espresso.onView(ViewMatchers.withId(R.id.text_score))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialScore + " / " + initialScore)));
        Espresso.onView(ViewMatchers.withId(R.id.button_answer1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.text_score))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedScore + " / " + (initialScore + 1))));
    }

    @Test
    public void testScoreUpdateOnIncorrectAnswer() {
        int initialScore = 0;
        int expectedScore = initialScore;
        Espresso.onView(ViewMatchers.withId(R.id.text_score))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialScore + " / " + initialScore)));
        Espresso.onView(ViewMatchers.withId(R.id.button_answer2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.text_score))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedScore + " / " + (initialScore + 1))));
    }
}