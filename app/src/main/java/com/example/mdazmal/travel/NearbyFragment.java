package com.example.mdazmal.travel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment {
    private Spinner resturentSp,distSp;
    private Button searchBtn;
    private List<String>collectionList = new ArrayList<>();

    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        resturentSp  = view.findViewById(R.id.resSp);
        distSp  = view.findViewById(R.id.disSp);
        rsturentList();

       ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,collectionList);
       resturentSp.setAdapter(adapter);

        return view;
    }

    public void  rsturentList(){
        collectionList.add("bank");
        collectionList.add("hirajheel");
        collectionList.add("Gharoa");
        collectionList.add("gangchil");
        collectionList.add("hotelsheraton");
    }

}
