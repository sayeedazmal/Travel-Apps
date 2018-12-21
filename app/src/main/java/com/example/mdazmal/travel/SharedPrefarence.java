package com.example.mdazmal.travel;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPrefarence {

    private SharedPreferences sharedPrefarence;
    private SharedPreferences.Editor editor;
    private final String IS_LOG_IN = "is_log_in";

    public SharedPrefarence(Context context) {
        sharedPrefarence = context.getSharedPreferences("Prefarence",Context.MODE_PRIVATE);
        editor = sharedPrefarence.edit();
    }
    public void setLoginpreference(boolean status){
        editor.putBoolean(IS_LOG_IN,status);
        editor.commit();

    }
    public boolean getLoginData(){
        return sharedPrefarence.getBoolean(IS_LOG_IN,false) ;

    }
}
