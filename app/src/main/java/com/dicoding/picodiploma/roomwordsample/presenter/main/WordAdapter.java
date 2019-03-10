package com.dicoding.picodiploma.roomwordsample.presenter.main;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.picodiploma.roomwordsample.R;
import com.dicoding.picodiploma.roomwordsample.data.Word;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends PagedListAdapter<Word, WordAdapter.ViewHolder> {

    protected WordAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Word word = getItem(i);
        if (word != null) {
            viewHolder.textView.setText(word.getWord());
        }
    }

    public Word getItemById(int i) {
        return getItem(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    private static DiffUtil.ItemCallback<Word> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Word>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Word oldBookmark, Word newBookmark) {
                    return oldBookmark.equals(newBookmark);
                }

                @Override
                public boolean areContentsTheSame(Word oldBookmark, @NonNull Word newBookmark) {
                    return oldBookmark.equals(newBookmark);
                }
            };
}
