package com.example.mdazmal.travel;


import android.content.Context;
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
import com.google.firebase.database.FirebaseDatabase;


public class LoginFragment extends Fragment {

    private EditText emailET, passET;
    private Button loginBTN, signupBTN;
    private Context context;
    private UserAuthentication listener;

    private FirebaseAuth auth;
    @Nullable private FirebaseUser currentUser;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (UserAuthentication) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailET= view.findViewById(R.id.emailInput);
        passET= view.findViewById(R.id.passInput);
        loginBTN= view.findViewById(R.id.loginBtn);
        signupBTN= view.findViewById(R.id.signupBtn);


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passET.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please Enter all info...", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "login successfull", Toast.LENGTH_SHORT).show();
                                emailET.setText(null);
                                passET.setText(null);
                                listener.onloginAuth();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });


        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAuthComplete();
            }
        });

        return view;
    }

    interface UserAuthentication{
        void onAuthComplete();
        void onloginAuth();
    }

}
