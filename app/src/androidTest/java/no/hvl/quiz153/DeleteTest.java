package no.hvl.quiz153;

import static org.junit.Assert.assertEquals;

import android.widget.ListView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeleteTest {

    private DatabaseActivity databaseActivity;

    @Before
    public void setUp() {
        ActivityScenario<DatabaseActivity> scenario = ActivityScenario.launch(DatabaseActivity.class);
        scenario.onActivity(activity -> {
            databaseActivity = activity;
        });
    }

    @Test
    public void testItemRemovedFromDb() {
        ListView listView = databaseActivity.listView;
        if (listView.getAdapter().getCount() <= 3) {
return;
        }
        int initialSize = listView.getAdapter().getCount();

        int expectedSize = initialSize-1;

        listView.performItemClick(listView.getChildAt(0),0,listView.getChildAt(0).getId());

        // Check that the item has been removed from the ListView
        int newSize = listView.getAdapter().getCount();
        assertEquals(expectedSize, newSize);

    }
    
    
}
