package no.hvl.quiz153;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoreRepository {

    private ScoreDao scoreDao;
    private LiveData<List<ScoreEntity>> allScores;

    public ScoreRepository(Application application) {
        ScoreDatabase database = ScoreDatabase.getInstance(application);
        scoreDao = database.scoreDao();
        allScores = scoreDao.getAllScores();
    }

    public void insert(ScoreEntity scoreEntity) {
        new InsertScoreAsyncTask(scoreDao).execute(scoreEntity);
    }

    public void updateScore() {
        new UpdateScoreAsyncTask(scoreDao).execute();
    }

    public void updateTotal() {
        new UpdateTotalAsyncTask(scoreDao).execute();
    }

    public void delete(ScoreEntity scoreEntity) {
        new DeleteScoreAsyncTask(scoreDao).execute(scoreEntity);
    }



    public LiveData<List<ScoreEntity>> getAllScores() {
        return allScores;
    }

    private static class InsertScoreAsyncTask extends AsyncTask<ScoreEntity, Void, Void> {
        private ScoreDao scoreDao;

        private InsertScoreAsyncTask(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(ScoreEntity... scoreEntities) {
            scoreDao.insertScore(scoreEntities[0]);
            return null;
        }
    }

    private static class UpdateScoreAsyncTask extends AsyncTask<ScoreEntity, Void, Void> {
        private ScoreDao scoreDao;

        private UpdateScoreAsyncTask(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(ScoreEntity... scoreEntities) {
            scoreDao.updateScore();
            return null;
        }
    }

    private static class UpdateTotalAsyncTask extends AsyncTask<ScoreEntity, Void, Void> {
        private ScoreDao scoreDao;

        private UpdateTotalAsyncTask(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(ScoreEntity... scoreEntities) {
            scoreDao.updateTotal();
            return null;
        }
    }

    private static class DeleteScoreAsyncTask extends AsyncTask<ScoreEntity, Void, Void> {
        private ScoreDao scoreDao;

        private DeleteScoreAsyncTask(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(ScoreEntity... scoreEntities) {
            scoreDao.deleteScore(scoreEntities[0]);
            return null;
        }
    }

    }