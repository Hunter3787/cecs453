package com.cecs453.project3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomCarAdapter extends RecyclerView.Adapter
        <CustomCarAdapter.ViewHolder> {

    private List<> mCars;

    @NonNull
    @Override
    public CustomCarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCarAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        SongUtils.Song mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView =  view.findViewById(R.id.id);
            mContentView =  view.findViewById(R.id.content);
        }
    }
}
