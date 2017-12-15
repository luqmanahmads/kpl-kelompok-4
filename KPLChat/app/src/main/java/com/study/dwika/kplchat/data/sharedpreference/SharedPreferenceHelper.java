package com.study.dwika.kplchat.data.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by A.I on 15/12/2017.
 */

public class SharedPreferenceHelper implements BaseSharedPreferenceHelper {

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    private final SharedPreferences mPrefs;

    public SharedPreferenceHelper(Context context) {
        mPrefs = context.getSharedPreferences("KPL-Chat", Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(ACCESS_TOKEN, accessToken).apply();
    }
}
