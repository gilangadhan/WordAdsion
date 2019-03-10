package com.dicoding.picodiploma.roomwordsample.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordDatabase INSTANCE;

    public static WordDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordDatabase.class,
                            "word_database"
                    ).addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Word[] words = {
                            new Word("cat", "kucing"),
                            new Word("cat2", "kucing"),
                            new Word("cat3", "kucing"),
                            new Word("cat4", "kucing"),
                            new Word("cat5", "kucing"),
                            new Word("cat6", "kucing"),
                            new Word("cat7", "kucing"),
                            new Word("cat8", "kucing"),

                            new Word("mouse", "tikus")
                    };

                    for (Word word : words) {
                        WordDao dao = INSTANCE.wordDao();
                        dao.insert(word);
                    }
                }
            });
        }
    };

}
