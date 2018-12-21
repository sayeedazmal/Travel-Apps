package com.example.mdazmal.travel;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginFragment.UserAuthentication, Event.EventAuth,CreateEvent.createEvent {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        Fragment fragment = null;
        if(user !=null){
            fragment = new Event();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.fragmentContainer, fragment);
            ft.commit();
        }else{
            fragment = new LoginFragment();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.fragmentContainer, fragment);
            ft.commit();
        }


    }


    @Override
    public void onAuthComplete(){
        SignupFragment signupFragment = new SignupFragment();
        manager.beginTransaction().replace(R.id.fragmentContainer,signupFragment).addToBackStack("signup").commit();

    }

    @Override
    public void onloginAuth() {
        Event event = new Event();
        manager.beginTransaction().replace(R.id.fragmentContainer,event).addToBackStack("event").commit();
    }

    @Override
    public void eventauth() {
        CreateEvent createEvent = new CreateEvent();
        manager.beginTransaction().replace(R.id.fragmentContainer,createEvent).addToBackStack("create Event").commit();

    }

    @Override
    public void signoutAuth() {
        LoginFragment loginFragment = new LoginFragment();
        manager.beginTransaction().replace(R.id.fragmentContainer,loginFragment).addToBackStack("signout").commit();
    }

    @Override
    public void authCreate() {
        Event eventpage = new Event();
        manager.beginTransaction().replace(R.id.fragmentContainer,eventpage).addToBackStack("create Event").commit();
    }
}
