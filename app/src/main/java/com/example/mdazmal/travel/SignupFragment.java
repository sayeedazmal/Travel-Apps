package com.example.mdazmal.travel;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private EditText nameET,emailET,phoneET,passET;
    private Button regBTN;
    private FirebaseAuth firebaseAuth;
    @Nullable private FirebaseUser currentUser;


    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        nameET= view.findViewById(R.id.nameInput);
        emailET= view.findViewById(R.id.emailInput);
        phoneET= view.findViewById(R.id.phoneInput);
        passET= view.findViewById(R.id.PassInput);
        regBTN= view.findViewById(R.id.regBtn);

        regBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passET.getText().toString();

                if(name.isEmpty()||email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getActivity(),"please fill up the blank space",Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(),"successfull load to database",Toast.LENGTH_SHORT).show();
                                        nameET.setText(null);
                                        emailET.setText(null);
                                        phoneET.setText(null);
                                        passET.setText(null);

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        return view;
    }



}
