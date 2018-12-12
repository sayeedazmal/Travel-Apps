package com.example.mdazmal.travel.pojoclass;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mdazmal.travel.CreateEvent;
import com.example.mdazmal.travel.Event;
import com.example.mdazmal.travel.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.zip.Inflater;

    public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private Context context;

    private List<EventInfo> eventinfo;

    public EventAdapter(Context context, List<EventInfo> eventinfo) {
        this.context = context;
        this.eventinfo = eventinfo;
    }

    @NonNull
    @Override
        public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(context)
                    .inflate(R.layout.eventview,viewGroup,false);
            EventViewHolder EVH = new EventViewHolder(v);
            return EVH;
        }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
       eventViewHolder.evenTv.setText(eventinfo.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return eventinfo.size();
    }



    public void updateList(List<EventInfo> eventinfo){
            this.eventinfo = eventinfo;
            notifyDataSetChanged();
    }

}
