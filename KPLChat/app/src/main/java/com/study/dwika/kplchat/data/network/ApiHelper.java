package com.study.dwika.kplchat.data.network;

import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.model.UsersResponse;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public class ApiHelper implements BaseApiHelper {
    @Override
    public Observable<BaseResponse> doRegister(Users users) {
        return Rx2AndroidNetworking.post(APIEndPoint.BASE_URL+APIEndPoint.REGISTER)
                .addBodyParameter(users)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> doLogin(Login login) {
        return Rx2AndroidNetworking.post(APIEndPoint.LOGIN)
                .addBodyParameter(login)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<UsersResponse> authenticatedUser(ApiHeader header) {
        return Rx2AndroidNetworking.get(APIEndPoint.AUTHENTICATED_USER)
                .addHeaders(header)
                .build()
                .getObjectObservable(UsersResponse.class);
    }

    @Override
    public Observable<UsersResponse> searchUserByEmail(ApiHeader header, String email) {
        Log.d("Debug","Calls apiHelper.searchUserByEmail");
        return Rx2AndroidNetworking.get(APIEndPoint.SEARCH_USER_BY_EMAIL)
                .addHeaders(header)
                .addPathParameter("email",email)
                .build()
                .getObjectObservable(UsersResponse.class);
    }

    @Override
    public Observable<BaseResponse> addFriend(ApiHeader header, String id) {
        Log.d("Debug","Calls apiHelper.addFriend");
        return Rx2AndroidNetworking.post(APIEndPoint.ADD_FRIEND)
                .addHeaders(header)
                .addPathParameter("id",id)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

}
