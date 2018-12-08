package com.example.mdazmal.travel;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdazmal.travel.pojoclass.EventInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEvent extends Fragment {

private EditText nameEt,locationEt, destinationEt, dateEt, budgetEt;
private Button createEventEt;

private DatabaseReference rootref;
private DatabaseReference userRef;
private DatabaseReference eventRef;
private DatabaseReference eventIdRef;

private FirebaseAuth auth;
private FirebaseUser user;


    public CreateEvent() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        rootref = FirebaseDatabase.getInstance().getReference();
        userRef = rootref.child("Users");
        eventRef = userRef.child("event");
        eventIdRef = eventRef.child(user.getUid());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_create_event, container, false);
       nameEt = view.findViewById(R.id.enameEt);
       locationEt = view.findViewById(R.id.elocationEt);
       destinationEt = view.findViewById(R.id.edestEt);
       dateEt = view.findViewById(R.id.edateEt);
       budgetEt = view.findViewById(R.id.ebudgetEt);




       createEventEt = view.findViewById(R.id.createEventbtn);
       createEventEt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String name = nameEt.getText().toString();
              String locate = locationEt.getText().toString();
              String destination = destinationEt.getText().toString();
              String date = dateEt.getText().toString();
              int budget = Integer.parseInt(budgetEt.getText().toString());

             try{
                 if(name.isEmpty() || locate.isEmpty() || destination.isEmpty() || date.isEmpty() || String.valueOf(budget).isEmpty()){
                     Toast.makeText(getActivity(),"invalide field",Toast.LENGTH_LONG).show();

                 }else{

                     String keyId = eventIdRef.push().getKey();
                     EventInfo eventInfo = new EventInfo(keyId,name,locate,destination,date,budget);
                     eventIdRef.child(keyId).setValue(eventInfo);
                     nameEt.setText(null);
                     locationEt.setText(null);
                     destinationEt.setText(null);
                     dateEt.setText(null);
                     budgetEt.setText(null);
                     Toast.makeText(getActivity(),"success",Toast.LENGTH_SHORT).show();
                 }

             }catch (Exception e){
                 Toast.makeText(getActivity(),""+e,Toast.LENGTH_SHORT).show();
             }
          }
      });



        return view;
    }

}
