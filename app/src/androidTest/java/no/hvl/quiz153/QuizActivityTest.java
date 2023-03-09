package no.hvl.quiz153;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.widget.Button;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizActivityTest {

    private QuizActivity quizActivity;

    @Before
    public void setUp() {
        ActivityScenario<QuizActivity> scenario = ActivityScenario.launch(QuizActivity.class);
        scenario.onActivity(activity -> {
            quizActivity = activity;
        });
    }

    @Test
    public void testScoreUpdateOnCorrectAnswer() {
        int initialScore = quizActivity.score;
        int expectedScore = initialScore + 1;
        int initalTotal = quizActivity.total;
        int expectedTotal = initalTotal + 1;
        String correctTxt = quizActivity.curr_answer.getText();

        List<Button> btnList = quizActivity.button_list;
        Button correctButton = null;

        for (Button button : btnList) {
            if (button.getText().toString().contains(correctTxt)) {
                correctButton = button;
                break;
            }
        }

        assertThat(correctButton, is(notNullValue()));

        Button finalCorrectButton = correctButton;
        quizActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finalCorrectButton.performClick();
            }
        });

        Espresso.onView(ViewMatchers.withId(R.id.text_score))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedScore + " / " + (expectedTotal))));

    }

    @Test
    public void testScoreUpdateOnIncorrectAnswer() {
        int initialScore = quizActivity.score;
        int initalTotal = quizActivity.total;
        int expectedTotal = initalTotal + 1;
        String correctTxt = quizActivity.curr_answer.getText();

        List<Button> btnList = quizActivity.button_list;
        Button correctButton = null;

        for (Button button : btnList) {
            if (!button.getText().toString().contains(correctTxt)) {
                correctButton = button;
                break;
            }
        }

        assertThat(correctButton, is(notNullValue()));

        Button finalCorrectButton = correctButton;
        quizActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                finalCorrectButton.performClick();
            }
        });

        Espresso.onView(ViewMatchers.withId(R.id.text_score))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialScore + " / " + (expectedTotal))));

    }
}