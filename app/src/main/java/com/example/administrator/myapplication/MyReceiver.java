package com.example.administrator.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/9/8.
 */

public class MyReceiver extends BroadcastReceiver{

    private onNetworkChanged changed;



    public interface onNetworkChanged {
        void isOnline();
        void isOffline();
    }

    public MyReceiver() {

    }

    public MyReceiver(onNetworkChanged changed) {
        this.changed = changed;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean state = false;
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] infos = manager.getAllNetworks();
        for(Network info : infos){
            NetworkInfo n = manager.getNetworkInfo(info);
            if(n.isConnected())
                state = true;
        }
        if(state){
            changed.isOnline();
        }else{
            changed.isOffline();
        }
    }
}
