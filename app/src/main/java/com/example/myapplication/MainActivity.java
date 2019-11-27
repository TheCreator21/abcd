package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private static final int LOCATION = 1;
    private String ssidName = "";
    private TextView wifiTv;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("user");

//        wifiTv = (TextView) findViewById(R.id.wifiStatus);
//        WebView myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.setWebViewClient(new WebViewClient());
//        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
////        myWebView.loadUrl("http://10.0.2.2:8000");//localhost on port 8000
//        myWebView.loadUrl("192.168.2.249:8000");//localhost on port 8000
//        ssidName = tryToReadSSID();
//        wifiTv.setText(ssidName);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        try {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//        }
//        catch(SecurityException secex){
//
//        }


        final Button button = findViewById(R.id.PlayButton);
        android.widget.LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 130); // 60 is height you can set it as u need

        button.setLayoutParams(lp);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                myIntent.putExtra("user",name);
                MainActivity.this.startActivity(myIntent);

            }
        });
        final Button button2 = findViewById(R.id.RecordButton);
        button2.setLayoutParams(lp);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, Records.class);
                myIntent.putExtra("user",name);
                myIntent.putExtra("result","main");
                MainActivity.this.startActivity(myIntent);

            }
        });
        final Button button3 = findViewById(R.id.LogsButton);
        button3.setLayoutParams(lp);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, LogsActivity.class);
                MainActivity.this.startActivity(myIntent);

            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == LOCATION) {
//            //User allowed the location and you can read it now
//            ssidName = tryToReadSSID();
//            if (!ssidName.equals("")) {
//                wifiTv.setText(ssidName);
//            }
//        }
//    }
//
//    /**
//     * method that ask permtion for location from device and returns the name of ssid if permition granted.
//     *
//     * @return
//     */
//    private String tryToReadSSID() {
//        String ssid = "";
//        //If requested permission isn't Granted yet
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            //Request permission from user
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
//        } else {//Permission already granted
//            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
//                ssid = wifiInfo.getSSID();//Here you can access your SSID
//
//            }
//        }
//        return ssid;
//    }
//    @Override
//    public void onLocationChanged(Location location) {
//        txtLat = (TextView) findViewById(R.id.gpsCordinatesTV);
//        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras){
//
//    }
}