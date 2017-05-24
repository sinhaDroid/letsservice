package com.sinhadroid.letsservice.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by deepanshu on 24/5/17.
 */
public abstract class Adapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> mItems = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    Adapter(Context context) {
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    LayoutInflater getLayoutInflater() {
        return this.mLayoutInflater;
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addAll(Collection<T> items) {
        for (T item : items) {
            mItems.add(item);
        }
        notifyDataSetChanged();
    }

    public void add(T item, int position) {
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }
}