package com.hassan.hanoy.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import static com.hassan.hanoy.preferences.PreferenceKeys.APP_OPEN_COUNT;
import static com.hassan.hanoy.preferences.PreferenceKeys.REMOTE_CONFIG_KEY;
import static com.hassan.hanoy.preferences.PreferenceKeys.SHOW_RATE_US;

public class PreferenceManager {
    private static PreferenceManager mPreferenceManager;
    private final SharedPreferences mSharedPrefs;
    private final SharedPreferences.Editor mEditor;

    private PreferenceManager(Context context) {
        this.mSharedPrefs = context.getSharedPreferences("ApplicationPreferences", 0);
        this.mEditor = this.mSharedPrefs.edit();
    }

    public static PreferenceManager getInstance(Context context) {
        if (mPreferenceManager == null)
            mPreferenceManager = new PreferenceManager(context);
        return mPreferenceManager;
    }

    public void saveRemoteConfig(String value) {
        this.mEditor.putString(REMOTE_CONFIG_KEY, value);
        this.mEditor.commit();
    }

    public String getRemoteConfig() {
        return this.mSharedPrefs.getString(REMOTE_CONFIG_KEY, null);
    }

    public void saveOpenCount() {
        this.mEditor.putInt(APP_OPEN_COUNT, getOpenCount() + 1);
        this.mEditor.commit();
    }

    public int getOpenCount() {
        return this.mSharedPrefs.getInt(APP_OPEN_COUNT, 0);
    }

    public void saveShowRateUs(boolean isShow) {
        this.mEditor.putBoolean(SHOW_RATE_US, isShow);
        this.mEditor.commit();
    }

    public boolean isShowRateUs() {
        return this.mSharedPrefs.getBoolean(SHOW_RATE_US, true);
    }
}
