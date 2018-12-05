package com.example.mdazmal.travel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment {

    private EditText emailET, passET;
    private Button loginBTN, signupBTN;

    public LoginFragment() {
        // Required empty public constructor
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
        return view;
    }

}
