package no.hvl.quiz153;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ScoreEntity.class}, version = 2, exportSchema = false)
@TypeConverters({QuizEntryConverter.class, QuizEntryListConverter.class})

public abstract class ScoreDatabase extends RoomDatabase {
    public abstract ScoreDao scoreDao();

    private static ScoreDatabase instance;

    public static synchronized ScoreDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ScoreDatabase.class, "score_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}