package no.hvl.quiz153;

import android.util.Log;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizEntryListConverter {



    @TypeConverter
    public static List<QuizEntry> toQuizEntryList(String value) {
        if (value == null) {
            return null;
        }
        List<QuizEntry> list = new ArrayList<>();
        String[] entries = value.split(",");
        for (String entry : entries) {
            list.add(QuizEntryConverter.toQuizEntry(entry));
        }
        return list;
    }

    @TypeConverter
    public static String fromQuizEntryList(List<QuizEntry> list) {
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (QuizEntry entry : list) {
            sb.append(QuizEntryConverter.fromQuizEntry(entry)).append(",");
        }
        return sb.toString();
    }
}