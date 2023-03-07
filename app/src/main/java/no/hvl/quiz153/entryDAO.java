package no.hvl.quiz153;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface entryDAO {

    @Query("SELECT * FROM quiz_database")
    List<QuizEntry> getAll();

    @Query("SELECT * FROM quiz_database")
    LiveData<List<QuizEntry>> getAllLive();

    @Insert
    void insert(QuizEntry quizEntry);

    @Delete
    void delete(QuizEntry quizEntry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuizEntry> entries);}