    package com.example.mdazmal.travel;


    import android.content.Context;
    import android.os.Bundle;
    import android.support.annotation.NonNull;
    import android.support.annotation.Nullable;
    import android.support.design.widget.FloatingActionButton;
    import android.support.v4.app.Fragment;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;

    import com.example.mdazmal.travel.pojoclass.EventAdapter;
    import com.example.mdazmal.travel.pojoclass.EventInfo;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;
    import java.util.List;


    /**
     * A simple {@link Fragment} subclass.
     */
    public class Event extends Fragment {

    private Context context;
    private EventAuth eventListener;
    private FloatingActionButton floatButton;
    private Button signoutbtn;

    private CreateEvent createEvent;
    private List<EventInfo>eventInfolist = new ArrayList<>();
    private RecyclerView recyleView;
    private LinearLayoutManager layoutManager;
    private EventAdapter eventAdapter;
    private  EventInfo eventInfo;

    private DatabaseReference rootref;
    private DatabaseReference userRef;
    private DatabaseReference eventRef;
    private DatabaseReference eventIdRef;
    private FirebaseAuth auth;
    private FirebaseUser user;

        public Event() {
            // Required empty public constructor
        }



        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            this.context = context;
            eventListener = (EventAuth) context;
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

            eventIdRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    eventInfolist.clear();
                    for(DataSnapshot d: dataSnapshot.getChildren()){
                        EventInfo eventInfo = d.getValue(EventInfo.class);
                        eventInfolist.add(eventInfo);
                        eventAdapter  = new EventAdapter(getActivity(),eventInfolist)
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_event, container, false);
            floatButton = view.findViewById(R.id.fab);
            recyleView = view.findViewById(R.id.recyleView);
            signoutbtn = view.findViewById(R.id.signoutbtn);

            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyleView.setLayoutManager(layoutManager);
            recyleView.setAdapter(eventAdapter);

            signoutbtn.setOnClickListener(new View.OnClickListener() {
                         @Override
                     public void onClick(View v) {
                             FirebaseAuth auth = FirebaseAuth.getInstance();
                             FirebaseUser user = auth.getCurrentUser();
                             if(user != null){
                                 auth.signOut();
                             }
                     }
                 }
            );


            floatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.eventauth();
                }
            });
            return view;
        }


        interface EventAuth{
            void eventauth();
        }
    }
