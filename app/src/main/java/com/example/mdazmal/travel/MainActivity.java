package com.example.mdazmal.travel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SignupFragment.UserAuthentication {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        Fragment fragment = null;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragmentContainer, fragment);
        ft.commit();

    }

    @Override
    public void onAuthComplete() {
        FragmentTransaction ft = manager.beginTransaction();
        SignupFragment signupFragment = new SignupFragment();
        ft.replace(R.id.fragmentContainer, signupFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
