package com.sinhadroid.letsservice.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinhadroid.letsservice.R;
import com.sinhadroid.letsservice.model.UserResponse;

/**
 * Created by deepanshu on 24/5/17.
 */

public class DetailAdapter extends Adapter<UserResponse, DetailAdapter.MyViewHolder> {

    public DetailAdapter(Context context) {
        super(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(getLayoutInflater().inflate(R.layout.inflater_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lat;
        TextView longt;
        TextView time;
        MyViewHolder(View itemView) {
            super(itemView);
            lat = (TextView) itemView.findViewById(R.id.lat);
            longt = (TextView) itemView.findViewById(R.id.longt);
            time = (TextView) itemView.findViewById(R.id.time);
        }

        @SuppressLint("SetTextI18n")
        void bind(UserResponse item) {
            lat.setText("lat: " + item.getLat());
            longt.setText("long: " + item.getLon());
            time.setText("time: " + item.getTime());
        }
    }
}
