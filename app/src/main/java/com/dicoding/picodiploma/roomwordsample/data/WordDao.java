package com.dicoding.picodiploma.roomwordsample.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    DataSource.Factory<Integer, Word> getAllWords();
}
