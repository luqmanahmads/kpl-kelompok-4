package com.study.dwika.kplchat.data.network;

import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.model.Login;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseApiHelper {
    io.reactivex.Observable<BaseResponse> doRegister(Users users);
    Observable<BaseResponse> doLogin(Login login);
}
