package com.example.mdazmal.travel;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdazmal.travel.pojoclass.ExpendablelistviewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */

public class Expenditure extends Fragment {

    ExpendablelistviewAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public Expenditure() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.expenditure, container, false);

        // get the listview
        expListView = view.findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpendablelistviewAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                switch (listDataHeader.get(groupPosition)){

                    case "Expenditure":
                        switch (childPosition){
                            case 0:
                                dialoguebox();
                                break;
                            case 1:
                                dialoguebox();
                                break;
                            case 2:
                                dialoguebox();
                                break;
                            default:
                        }
                        break;

                    case "Moment":

                        break;
                    case "More or Event":
                        break;

                }



//                Toast.makeText(
//                       getActivity(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();

                return false;
         }

            private void dialoguebox() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.info_show_dialog,null);
                builder.setView(view1);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return view;
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Expenditure");
        listDataHeader.add("Moment");
        listDataHeader.add("More or Event");

        // Adding child data
        List<String> expenditure = new ArrayList<String>();
        expenditure.add("Add New Expense");
        expenditure.add("View All Exapense");
        expenditure.add("Add More Budget");


        List<String> Moment = new ArrayList<String>();
        Moment.add("Take a Photo");
        Moment.add("View Gallery");
        Moment.add("View ALl Moments");


        List<String> Event = new ArrayList<String>();
        Event.add("Edit Event ");
        Event.add("Delete Event");


        listDataChild.put(listDataHeader.get(0), expenditure); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Moment);
        listDataChild.put(listDataHeader.get(2), Event);
    }

}
