package com.example.mdazmal.travel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private EditText nameET,emailET,phoneET,passET;
    private Button regBTN;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        nameET= view.findViewById(R.id.nameInput);
        emailET= view.findViewById(R.id.emailInput);
        phoneET= view.findViewById(R.id.phoneInput);
        passET= view.findViewById(R.id.passInput);
        regBTN= view.findViewById(R.id.regBtn);

        return view;
    }

    interface UserAuthentication{
        void onAuthComplete();
    }
}
