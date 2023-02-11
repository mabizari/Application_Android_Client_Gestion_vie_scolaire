package com.zerophi.gestionvie.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public  class isConnectedInternet {

    public boolean isConnected(Context context){

        ConnectivityManager manager =(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo current = manager.getActiveNetworkInfo();

        if(current==null){
            return false;
        }else {
            return (current.getState()==NetworkInfo.State.CONNECTED);
        }
    }
}
