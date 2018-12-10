package com.example.mdazmal.travel.pojoclass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mdazmal.travel.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.zip.Inflater;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
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

        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {


    }
    @Override
    public int getItemCount() {

        return eventinfo.size();
    }


    class EventViewHolder extends RecyclerView.ViewHolder{
            TextView evenTv;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            evenTv = itemView.findViewById(R.id.row_event);

        }
    }

}
