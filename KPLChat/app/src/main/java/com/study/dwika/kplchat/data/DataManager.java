package com.study.dwika.kplchat.data;

import android.util.Log;

import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.ConversationDetailResponse;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.model.Login;

import com.study.dwika.kplchat.model.UsersResponse;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public class DataManager implements BaseDataManager {

    private final BaseApiHelper baseApiHelper;
    private final BaseDatabaseHelper baseDatabaseHelper;
    private final BaseSharedPreferenceHelper baseSharedPreferenceHelper;

    public DataManager(BaseApiHelper baseApiHelper, BaseDatabaseHelper baseDatabaseHelper, BaseSharedPreferenceHelper baseSharedPreferenceHelper) {
        this.baseApiHelper = baseApiHelper;
        this.baseDatabaseHelper = baseDatabaseHelper;
        this.baseSharedPreferenceHelper = baseSharedPreferenceHelper;
    }

    @Override
    public Observable<BaseResponse> doRegister(Users users) {
        return baseApiHelper.doRegister(users);
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
    public Observable<UsersResponse> searchUserByEmail(ApiHeader header, String email) {
        Log.d("Debug","Calls dataManager.searchUserByEmail");
        return baseApiHelper.searchUserByEmail(header,email);
    }

    @Override
    public Observable<BaseResponse> addFriend(ApiHeader header, String id) {
        Log.d("Debug","Calls dataManager.addFriend");
        return baseApiHelper.addFriend(header,id);
    }

    @Override
    public Observable<ConversationDetailResponse> conversationDetail(ApiHeader header, String id) {
        return baseApiHelper.conversationDetail(header,id);
    }

    @Override
    public String getAccessToken() {
        return baseSharedPreferenceHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        baseSharedPreferenceHelper.setAccessToken(accessToken);
    }

    @Override
    public String getId() {
        return baseSharedPreferenceHelper.getId();
    }

    @Override
    public void setId(String id) {
        baseSharedPreferenceHelper.setId(id);
    }

    @Override
    public String getName() {
        return baseSharedPreferenceHelper.getName();
    }

    @Override
    public void setName(String name) {
        baseSharedPreferenceHelper.setName(name);
    }

    @Override
    public String getPhone() {
        return baseSharedPreferenceHelper.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        baseSharedPreferenceHelper.setPhone(phone);
    }

    @Override
    public String getEmail() {
        return baseSharedPreferenceHelper.getEmail();
    }

    @Override
    public void setEmail(String email) {
        baseSharedPreferenceHelper.setEmail(email);
    }

    @Override
    public Observable<UsersResponse> getFriend(ApiHeader apiHeader) {
        return baseApiHelper.getFriend(apiHeader);
    }

}
