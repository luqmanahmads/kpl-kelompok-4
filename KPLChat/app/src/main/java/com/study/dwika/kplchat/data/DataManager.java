package com.study.dwika.kplchat.data;

import android.util.Log;

import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.model.UsersResponse;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public class DataManager implements BaseDataManager{

    private final BaseApiHelper baseApiHelper;
    private final BaseDatabaseHelper baseDatabaseHelper;
    private final BaseSharedPreferenceHelper baseSharedPreferenceHelper;

    public DataManager(BaseApiHelper baseApiHelper, BaseDatabaseHelper baseDatabaseHelper, BaseSharedPreferenceHelper baseSharedPreferenceHelper) {
        this.baseApiHelper = baseApiHelper;
        this.baseDatabaseHelper = baseDatabaseHelper;
        this.baseSharedPreferenceHelper = baseSharedPreferenceHelper;
    }

    @Override
    public Observable<BaseResponse> doLogin(Login login) {
        return baseApiHelper.doLogin(login);
    }

    @Override
    public Observable<UsersResponse> authenticatedUser(ApiHeader header) {
        return baseApiHelper.authenticatedUser(header);
    }

    @Override
    public String getAccessToken() {
        return baseSharedPreferenceHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        Log.d("Debug","Ngeset token nih : "+accessToken);
        baseSharedPreferenceHelper.setAccessToken(accessToken);
    }
}
