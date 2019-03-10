package com.dicoding.picodiploma.roomwordsample.presenter.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.dicoding.picodiploma.roomwordsample.data.Word;
import com.dicoding.picodiploma.roomwordsample.data.WordRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<PagedList<Word>> words;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new WordRepository(application);

        words = new LivePagedListBuilder<Integer, Word>(
                repository.getAllWords(), 20
        ).build();
    }

    public LiveData<PagedList<Word>> getAllWords() {
        return words;
    }

    public void insert(Word word) {
        repository.insert(word);
    }

    public void delete(Word word) {
        repository.delete(word);
    }


}
