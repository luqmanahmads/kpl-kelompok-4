package com.study.dwika.kplchat.data.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.model.Login;

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
}
