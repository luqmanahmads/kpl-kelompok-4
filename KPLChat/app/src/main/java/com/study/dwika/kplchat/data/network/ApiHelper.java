package com.study.dwika.kplchat.data.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Users;

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
}
