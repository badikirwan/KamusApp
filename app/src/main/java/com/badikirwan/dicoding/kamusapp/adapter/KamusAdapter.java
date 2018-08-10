package com.badikirwan.dicoding.kamusapp.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.badikirwan.dicoding.kamusapp.R;
import com.badikirwan.dicoding.kamusapp.model.KamusModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.SearchViewHolder> {

    private ArrayList<KamusModel> list = new ArrayList<>();

    public void replaceAll(ArrayList<KamusModel> items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_main, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.tv_word.setText(list.get(position).getWord());
        holder.tv_translate.setText(list.get(position).getTranslate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_word)
        TextView tv_word;

        @BindView(R.id.tv_translate)
        TextView tv_translate;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
