package com.dicoding.picodiploma.roomwordsample.data;

import android.app.Application;
import android.arch.paging.DataSource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordRepository {

    private WordDao wordDao;

    private ExecutorService executorService;

    public WordRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        WordDatabase wordDatabase = WordDatabase.getDatabase(application);
        wordDao = wordDatabase.wordDao();
    }

    public DataSource.Factory<Integer, Word> getAllWords() {

        return wordDao.getAllWords();
    }

    public void insert(final Word word) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.insert(word);
            }
        });
    }

    public void delete(final Word word) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.delete(word);
            }
        });
    }
}
