package no.hvl.quiz153;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuizEntryRepository {
    private entryDAO mEntryDao;
    private LiveData<List<QuizEntry>> mAllEntries;

    public QuizEntryRepository(Application application) {
        QuizDatabase db = QuizDatabase.getInstance(application);
        mEntryDao = db.quizDao();
        mAllEntries = mEntryDao.getAllLive();
    }

    public LiveData<List<QuizEntry>> getAllEntries() {
        return mAllEntries;
    }

    public void insert(QuizEntry entry) {
        new InsertAsyncTask(mEntryDao).execute(entry);
    }

    public void delete(QuizEntry entry) {
        new DeleteAsyncTask(mEntryDao).execute(entry);
    }

    private static class InsertAsyncTask extends AsyncTask<QuizEntry, Void, Void> {
        private entryDAO mAsyncTaskDao;

        InsertAsyncTask(entryDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuizEntry... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<QuizEntry, Void, Void> {
        private entryDAO mAsyncTaskDao;

        DeleteAsyncTask(entryDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuizEntry... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}