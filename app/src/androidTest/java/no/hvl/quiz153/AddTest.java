package no.hvl.quiz153;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

    @RunWith(AndroidJUnit4.class)
    public class AddTest {

        private NewEntryActivity newEntryActivity;


        @Before
        public void setUp() {
            ActivityScenario<NewEntryActivity> scenario = ActivityScenario.launch(NewEntryActivity.class);
            scenario.onActivity(activity -> {
                newEntryActivity = activity;
            });
        }


}
