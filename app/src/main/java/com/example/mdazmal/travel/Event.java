    package com.example.mdazmal.travel;


    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.os.Bundle;
    import android.support.annotation.NonNull;
    import android.support.annotation.Nullable;
    import android.support.design.widget.FloatingActionButton;
    import android.support.v4.app.Fragment;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

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


    public class Event extends Fragment implements EventAdapter.eventdAuthdialogue{

    private Context context;
    private EventAuth eventListener;
    private FloatingActionButton floatButton;
    private Button signoutbtn;

    private CreateEvent createEvent;
    private List<EventInfo>eventInfolist = new ArrayList<>();
    private List<EventInfo>showdialoglist = new ArrayList<>();

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

   // private SharedPrefarence sharedPrefarence;
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

                    }
                    recyleView.setAdapter(eventAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

      /* ==========Start Manue Item====== */

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.manuitem,menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int itemId = item.getItemId();

            switch (itemId){
                case  R.id.signoutItem:
                    if(user != null){
                        auth.signOut();
                        eventListener.signoutAuth();
                    }
                    break;
            }

            return super.onOptionsItemSelected(item);

        }

         /*===============End Manue=============*/

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View view = inflater.inflate(R.layout.fragment_event, container, false);
            floatButton = view.findViewById(R.id.fab);
            recyleView = view.findViewById(R.id.recyleView);
            eventAdapter = new EventAdapter(getActivity(),eventInfolist,this);

            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyleView.setLayoutManager(layoutManager);
            recyleView.setAdapter(eventAdapter);


            floatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventListener.eventauth();
                }
            });
            return view;
        }


        @Override
        public void geteventDialog(String name, String located, String destination, String date, String budget) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View v = getLayoutInflater().inflate(R.layout.info_show_dialog,null);
            TextView nameTv = v.findViewById(R.id.event_nameTv);
            TextView locTv  = v.findViewById(R.id.locTv);
            TextView destTv = v.findViewById(R.id.destTv);
            TextView dateTv = v.findViewById(R.id.dateTv);
            TextView budgetTv = v.findViewById(R.id.budgetTv);
            //nameTv.setText("sayeed");
            nameTv.setText(name);
            locTv.setText(located);
            destTv.setText(destination);
            dateTv.setText(date);
            budgetTv.setText(budget);

            builder.setView(v);
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





        @Override
        public void eventEdit(String eventID) {

        }

        @Override
        public void eventDelete(String eventID) {
          eventIdRef.child(eventID).removeValue();
        }


        interface EventAuth{
            void eventauth();
            void signoutAuth();
        }
    }
