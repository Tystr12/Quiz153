package no.hvl.quiz153;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "score_table")
public class ScoreEntity {



        @PrimaryKey(autoGenerate = true)
        @NonNull
        private int qId;

        private int score;
        private int total;

        private QuizEntry curr_answer;
        private List<QuizEntry> curr_wrongs;


        public ScoreEntity(int score, int total, List<QuizEntry> curr_wrongs, QuizEntry curr_answer) {
            this.score = score;
            this.total = total;
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

    public void setCurr_wrongs(ArrayList<QuizEntry> curr_wrongs) {
        this.curr_wrongs = curr_wrongs;
    }
}
