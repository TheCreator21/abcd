package com.example.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.WIFI_SERVICE;


public class BlankFragment extends Fragment implements LocationListener {


    View view;
    private static final int LOCATION = 1;
     String ssidName1 = "";
     TextView wifiTv1;
     WebView myWebView1;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat1;
    String lat1;
    String provider1;
    protected String latitude1,longitude1;
    protected boolean gps_enabled1,network_enabled1;
    WifiInfo wifiInfo;
    WifiManager wifiManager1;
    ConnectivityManager cm;
    NetworkInfo netInfo ;
    private NetworkChangeReceiver nc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        wifiTv1 = (TextView) view.findViewById(R.id.wifiStatus1);
        nc = new NetworkChangeReceiver(wifiTv1);

        ssidName1 = tryToReadSSID();


        wifiTv1.setText(ssidName1);
        txtLat1 = (TextView) view.findViewById(R.id.gpsCordinatesTV1);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            gps_enabled1 = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        catch(SecurityException secex){

        }

        myWebView1 =  (WebView) view.findViewById(R.id.webview1);
        myWebView1.setWebChromeClient(new WebChromeClient());
        myWebView1.setWebViewClient(new WebViewClient());
        myWebView1.getSettings().setJavaScriptEnabled(true);
        myWebView1.getSettings().setDomStorageEnabled(true);
        myWebView1.getSettings().setJavaScriptEnabled(true);
        myWebView1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        myWebView1.getSettings().setBuiltInZoomControls(true);
        myWebView1.setInitialScale(50);
        myWebView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

//        myWebView1.loadUrl("http://10.0.2.2:8000");//localhost on port 8000
        myWebView1.loadUrl("http://192.168.2.249:8000");//localhost on port 8000

        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == LOCATION) {
            //User allowed the location and you can read it now
            ssidName1 = tryToReadSSID();
            if (!ssidName1.equals("")) {
                wifiTv1.setText(ssidName1);
//                myWebView1.setWebChromeClient(new WebChromeClient());
//                myWebView1.setWebViewClient(new WebViewClient());
//                myWebView1.getSettings().setJavaScriptEnabled(true);
//                myWebView1.getSettings().setDomStorageEnabled(true);
//                myWebView1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//
//                myWebView1.getSettings().setBuiltInZoomControls(true);
//                myWebView1.setInitialScale(50);
//
//                myWebView1.loadUrl("https://www.google.com");
            }
        }
    }

    /**
     * method that ask permtion for location from device and returns the name of ssid if permition granted.
     *
     * @return
     */
    private String tryToReadSSID() {
        String ssid = "";
        //If requested permission isn't Granted yet
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Request permission from user
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
        } else {//Permission already granted
            wifiManager1 = (WifiManager) getActivity().getApplicationContext().getSystemService(WIFI_SERVICE);
            wifiInfo = wifiManager1.getConnectionInfo();
            if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                ssid = wifiInfo.getSSID();//Here you can access your SSID

            }
        }
        return ssid;
    }
    @Override
    public void onLocationChanged(Location location) {

        txtLat1.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        wifiManager1 = (WifiManager) getActivity().getApplicationContext().getSystemService(WIFI_SERVICE);
        wifiInfo = wifiManager1.getConnectionInfo();
        wifiTv1.setText(wifiInfo.getSSID());
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){


    }
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }
}
