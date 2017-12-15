package com.study.dwika.kplchat.data.network;

import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.study.dwika.kplchat.model.UsersResponse;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public class ApiHelper implements BaseApiHelper {
    @Override
    public Observable<UsersResponse> getFriend(ApiHeader apiHeader) {
        Log.d("debug", "apihelper friend");
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_FRIEND)
                .addHeaders(apiHeader)
                .build()
                .getObjectObservable(UsersResponse.class);
    }
}
