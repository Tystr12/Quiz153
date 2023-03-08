package no.hvl.quiz153;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private QuizEntryRepository repository;
    private LiveData<List<QuizEntry>> allQuizEntrys;

    public MainViewModel(@NonNull Application application) {

        super(application);
        this.repository = new QuizEntryRepository(application);
        this.allQuizEntrys = repository.getAllEntries();
    }


    LiveData<List<QuizEntry>> getAllQuizEntrys() {
        return allQuizEntrys;
    }

    public void insertQuizEntry(QuizEntry QuizEntry) {
        repository.insert(QuizEntry);
    }


    public void deleteQuizEntry(QuizEntry quizEntry) {
        repository.delete(quizEntry);
    }
}
