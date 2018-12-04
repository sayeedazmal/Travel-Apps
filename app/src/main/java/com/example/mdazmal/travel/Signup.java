package com.example.mdazmal.travel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends Fragment {

private EditText nameEt, emailEt,phoneEt,passwordEt;
private Button regbtn;


    public Signup() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        nameEt = v.findViewById(R.id.nameEt);
        emailEt = v.findViewById(R.id.emailEt);
        phoneEt = v.findViewById(R.id.phoneEt);
        regbtn  = v.findViewById(R.id.regbtn);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }

}
