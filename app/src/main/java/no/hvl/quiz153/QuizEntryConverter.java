package no.hvl.quiz153;

import android.net.Uri;
import android.util.Log;

import androidx.room.TypeConverter;

public class QuizEntryConverter {

    @TypeConverter
    public static String fromQuizEntry(QuizEntry quizEntry) {
        if (quizEntry == null) {
            return null;
        }
        return quizEntry.getEId() + ":" + quizEntry.getText() + ":" + quizEntry.getImg();
    }

    @TypeConverter
    public static QuizEntry toQuizEntry(String quizEntryString) {
        if (quizEntryString == null || quizEntryString.isEmpty()) {
            return null;
        }
        String[] parts = quizEntryString.split(":",3);
        Log.d("FULLLLLLL", quizEntryString);
        Log.d("EIIIIIIN", parts[1]);
        Log.d("TOOOOOOOO", parts[2]);

        String text = parts[1];
        Uri img = Uri.parse(parts[2]);
        return new QuizEntry(text, img);
    }
}
