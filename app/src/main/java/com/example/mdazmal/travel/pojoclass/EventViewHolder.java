package com.example.mdazmal.travel.pojoclass;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mdazmal.travel.R;


public class EventViewHolder extends RecyclerView.ViewHolder {

    TextView evenTv,menuTv;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
       evenTv  = itemView.findViewById(R.id.row_event);
       menuTv = itemView.findViewById(R.id.menuTv);
    }
}
