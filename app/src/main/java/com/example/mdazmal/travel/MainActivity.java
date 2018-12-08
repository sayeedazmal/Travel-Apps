package com.example.mdazmal.travel;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.UserAuthentication {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragmentContainer, loginFragment);
        ft.commit();
    }




    @Override
    public void onAuthComplete(){
        SignupFragment signupFragment = new SignupFragment();
        manager.beginTransaction().replace(R.id.fragmentContainer,signupFragment).commit();
    }

    @Override
    public void onloginAuth() {
        CreateEvent createEvent = new CreateEvent();
        manager.beginTransaction().replace(R.id.fragmentContainer,createEvent).commit();
    }
}
