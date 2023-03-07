package no.hvl.quiz153;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {QuizEntry.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class QuizDatabase extends RoomDatabase {

    private static volatile QuizDatabase INSTANCE;

    public abstract entryDAO quizDao();

    public static QuizDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuizDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    QuizDatabase.class, "quiz_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}