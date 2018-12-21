package com.example.mdazmal.travel.pojoclass;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mdazmal.travel.R;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private Context context;
    private List<EventInfo> eventinfo;
    private eventdAuthdialogue eventdialoglistener;
    private eventdAuthdialogue eventpasslistener;
    private eventdAuthdialogue eventeditlistener;
    private eventdAuthdialogue eventdelelistener;
    private eventdAuthdialogue editdialogulistener;
    private Fragment fragment;

    public EventAdapter(Context context, List<EventInfo> eventinfo, Fragment fragment) {
        this.context = context;
        this.eventinfo = eventinfo;
       this.eventdialoglistener = (eventdAuthdialogue) fragment;
       this.eventpasslistener = (eventdAuthdialogue) fragment;
       this.eventeditlistener = (eventdAuthdialogue) fragment;
       this.eventdelelistener = (eventdAuthdialogue) fragment;
       this.editdialogulistener = (eventdAuthdialogue) fragment;
    }


    @NonNull
    @Override
        public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(context)
                    .inflate(R.layout.rowitem,viewGroup,false);
            EventViewHolder EVH = new EventViewHolder(v);

            return EVH;
        }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, final int i) {
       eventViewHolder.evenTv.setText(eventinfo.get(i).getName());
       eventViewHolder.evenTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name = eventinfo.get(i).getName();
               String located = eventinfo.get(i).getLocation();
               String destination = eventinfo.get(i).getDestination();
               String date = eventinfo.get(i).getDate();
               String budget = String.valueOf(eventinfo.get(i).getBudget());
               eventpasslistener.geteventDialog(name,located,destination,date,budget);
              // eventdialoglistener.showeventdialog();

           }
       });

       eventViewHolder.menuTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu  = new PopupMenu(context,v);
               popupMenu.getMenuInflater().inflate(R.menu.menuactionitem,popupMenu.getMenu());
               popupMenu.show();
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       int itemId = item.getItemId();
                      final String eventid = eventinfo.get(i).getEventId();
                       switch (itemId){
                           case R.id.row_editItem:

                               break;
                           case R.id.row_deleteItem:
                               eventdelelistener.eventDelete(eventid);
                               break;
                       }
                       return false;
                   }
               });

           }
       });

    }


    public interface eventdAuthdialogue{
        //void showeventdialog();
    void geteventDialog(String name,String located, String destination, String date, String budget );

        void eventEdit(String eventId);
        void eventDelete(String eventId);
    }

    @Override
    public int getItemCount() {
        return eventinfo.size();
    }

    public void updateList(List<EventInfo> eventinfo) {
        this.eventinfo = eventinfo;
        notifyDataSetChanged();
    }


}
