package com.example.gudiya.moviesdemo;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;



/**
 * Created by Android on 12/14/2015.
 */
public class Constants {




    public static boolean isInternetWorking(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        NetworkInfo mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {

            WifiManager wifiManger = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
// in Activity replace getActivity() by Context in above line
            WifiInfo wifiInfo = wifiManger.getConnectionInfo();
            int speedMbps = wifiInfo.getLinkSpeed();
            int myspeed = 2000;  //mention your speed

            Log.d("speedMbps", "speedMbps" + speedMbps);
            if (speedMbps > 2000) {
                // start  AsyncTask
            } else {
//

                // Do whatever
            }

        }

        return (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable());


    }


}
