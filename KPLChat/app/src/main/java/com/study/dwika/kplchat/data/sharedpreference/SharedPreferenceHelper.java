package com.study.dwika.kplchat.data.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by A.I on 15/12/2017.
 */

public class SharedPreferenceHelper implements BaseSharedPreferenceHelper {

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String PHONE = "PHONE";
    private static final String EMAIL = "EMAIL";

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

    @Override
    public String getId() {
        return mPrefs.getString(ID, null);
    }

    @Override
    public void setId(String id) {
        mPrefs.edit().putString(ID, id).apply();
    }

    @Override
    public String getName() {
        return mPrefs.getString(NAME, null);
    }

    @Override
    public void setName(String name) {
        mPrefs.edit().putString(NAME, name).apply();
    }

    @Override
    public String getPhone() {
        return mPrefs.getString(PHONE, null);
    }

    @Override
    public void setPhone(String phone) {
        mPrefs.edit().putString(PHONE, phone).apply();
    }

    @Override
    public String getEmail() {
        return mPrefs.getString(EMAIL, null);
    }

    @Override
    public void setEmail(String email) {
        mPrefs.edit().putString(EMAIL, email).apply();
    }
}
