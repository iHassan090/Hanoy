package com.hassan.hanoy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.hassan.hanoy.R;
import com.hassan.hanoy.preferences.PreferenceManager;
import com.hassan.hanoy.utils.Constants;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.hassan.hanoy.Hanoy.mFirebaseRemoteConfig;
import static com.hassan.hanoy.utils.Constants.SPLASH_DURATION;

public class SplashActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initValues() {
        super.initValues();
        Date startTime = new Date();

        mFirebaseRemoteConfig.fetch(3600)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        mFirebaseRemoteConfig.activateFetched();
                        onConfigFetch(startTime);
                    } else {
                        Toast.makeText(SplashActivity.this, "Something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

        PreferenceManager.getInstance(this).saveOpenCount();
    }

    private void onConfigFetch(Date startTime) {
        Date now = new Date();

        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - startTime.getTime());

        if (diffInSec < 1) {
            // Using cached data
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(SPLASH_DURATION);
                        startNextActivity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            PreferenceManager.getInstance(SplashActivity.this).saveRemoteConfig(mFirebaseRemoteConfig.getString(Constants.TEXT2));
            startNextActivity();
        }
    }

    private void startNextActivity() {
        if (mFirebaseRemoteConfig.getString(Constants.TEXT1).equals("aaa"))
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        else
            startActivity(new Intent(SplashActivity.this, WebViewActivity.class)
                    .putExtra("url", mFirebaseRemoteConfig.getString(Constants.URL)));
        finish();
    }
}