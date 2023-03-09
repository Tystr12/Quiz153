package no.hvl.quiz153;


import android.net.Uri;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "score_table")
public class ScoreEntity {



        @PrimaryKey(autoGenerate = true)
        @NonNull
        private int qId;

        private int score = 0 ;
        private int total = 0;

        private QuizEntry curr_answer;
        private List<QuizEntry> curr_wrongs;


    public int getQId() {
        return qId;
    }

    public void setQId(int qId) {
        this.qId = qId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
