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


        public ScoreEntity(List<QuizEntry> curr_wrongs, QuizEntry curr_answer) {
            this.curr_wrongs = curr_wrongs;
            this.curr_answer = curr_answer;
        }

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

    public QuizEntry getCurr_answer() {
        return curr_answer;
    }

    public void setCurr_answer(QuizEntry curr_answer) {
        this.curr_answer = curr_answer;
    }

    public List<QuizEntry> getCurr_wrongs() {
        return curr_wrongs;
    }

    public void setCurr_wrongs(List<QuizEntry> curr_wrongs) {
        this.curr_wrongs = curr_wrongs;
    }
}
