package com.example.erlaljiyadav.mobitask.utility;

/**
 * Created by android on 15/2/18.
 */

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
   // ConnectivityReceiver networkStateReceiver = new ConnectivityReceiver();

    private static BaseActivity mInstance;


    @Override
    public void onStart() {
        super.onStart();


    //    this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));



    }
    @Override
    protected void onStop()
    {
       // unregisterReceiver(networkStateReceiver);
        super.onStop();
    }

}