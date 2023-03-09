package no.hvl.quiz153;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private QuizEntryRepository quizRepository;
    private ScoreRepository scoreRepository;
    private LiveData<List<QuizEntry>> allQuizEntrys;

    private LiveData<List<ScoreEntity>> allScores;

    public MainViewModel(@NonNull Application application) {

        super(application);
        this.quizRepository = new QuizEntryRepository(application);
        this.allQuizEntrys = quizRepository.getAllEntries();
        this.scoreRepository = new ScoreRepository(application);
        this.allScores = scoreRepository.getAllScores();
    }


    LiveData<List<QuizEntry>> getAllQuizEntrys() {
        return allQuizEntrys;
    }

    public void insertQuizEntry(QuizEntry QuizEntry) {
        quizRepository.insert(QuizEntry);
    }


    public void deleteQuizEntry(QuizEntry quizEntry) {
        quizRepository.delete(quizEntry);
    }



    LiveData<List<ScoreEntity>> getAllScores() {
        return allScores;
    }

    public void insertScore(ScoreEntity scoreEntity) {
        scoreRepository.insert(scoreEntity);
    }


    public void deleteScore(ScoreEntity scoreEntity) {
        scoreRepository.delete(scoreEntity);
    }

    public void updateScore() {scoreRepository.updateScore();}
    public void updateTotal() {scoreRepository.updateTotal();}
}
