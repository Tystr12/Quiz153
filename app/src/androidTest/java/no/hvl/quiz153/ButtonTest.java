package no.hvl.quiz153;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ButtonTest {

    @Test
    public void testButtonDatabaseClick() {
        // Launch the MenuActivity
        ActivityScenario.launch(MenuActivity.class);

        // Click the button_Database
        Espresso.onView(ViewMatchers.withId(R.id.button_db)).perform(ViewActions.click());

        // Verify that the DatabaseActivity is launched
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DatabaseActivity.class);
        ActivityScenario<DatabaseActivity> activityScenario = ActivityScenario.launch(intent);
        activityScenario.onActivity(activity -> {
            // Assert that the activity is an instance of DatabaseActivity
            assertThat(activity, instanceOf(DatabaseActivity.class));
        });
    }
}

