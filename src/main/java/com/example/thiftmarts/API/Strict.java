package com.example.thiftmarts.API;

import android.os.StrictMode;

public class Strict {

    public static void strictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
