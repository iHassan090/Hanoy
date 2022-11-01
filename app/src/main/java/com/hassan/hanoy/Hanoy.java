package com.hassan.hanoy;

import android.app.Application;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class Hanoy extends Application {
    public static FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    }
}
