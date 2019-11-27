package com.example.myapplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.WIFI_SERVICE;

public class NetworkChangeReceiver extends BroadcastReceiver {
private TextView tvw;
    public NetworkChangeReceiver(TextView tv){
        super();
        this.tvw=tv;

    }
    @Override
    public void onReceive(final Context context, final Intent intent) {

        String status = NetworkUtil.getConnectivityStatusString(context);


        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }

    public void setW(String s){
        tvw.setText(s);
    }
}