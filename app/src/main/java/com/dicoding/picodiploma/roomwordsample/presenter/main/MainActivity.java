package com.dicoding.picodiploma.roomwordsample.presenter.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.picodiploma.roomwordsample.R;
import com.dicoding.picodiploma.roomwordsample.data.Word;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    CoordinatorLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.main);
        final MainViewModel mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        final WordAdapter adapter = new WordAdapter();
        mainViewModel.getAllWords().observe(this, new Observer<PagedList<Word>>() {
            @Override
            public void onChanged(@Nullable PagedList<Word> words) {
                //secara otomatis meneri data
                adapter.submitList(words);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT| ItemTouchHelper.LEFT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int swipe = viewHolder.getAdapterPosition();
                final Word word = adapter.getItemById(swipe);

                mainViewModel.delete(word);

                Snackbar snackbar = Snackbar.make(layout, "Undo", Snackbar.LENGTH_SHORT);

                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainViewModel.insert(word);
                    }
                });

                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
