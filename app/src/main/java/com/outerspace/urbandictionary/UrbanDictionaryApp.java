package com.outerspace.urbandictionary;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

public class UrbanDictionaryApp extends Application {
    private static UrbanDictionaryApp instance;
    public static UrbanDictionaryApp getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
