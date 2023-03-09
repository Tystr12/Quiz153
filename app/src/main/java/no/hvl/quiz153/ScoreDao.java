package no.hvl.quiz153;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreDao {
    @Insert
    void insertScore(ScoreEntity score);

    @Query("SELECT * FROM score_table")
    LiveData<List<ScoreEntity>> getAllScores();

    @Delete
    void deleteScore(ScoreEntity score);

    @Query("UPDATE score_table SET score = score + 1")
    void updateScore();

    @Query("UPDATE score_table SET total = total + 1")
    void updateTotal();
}
