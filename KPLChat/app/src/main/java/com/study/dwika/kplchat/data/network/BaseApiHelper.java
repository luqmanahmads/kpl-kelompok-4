package com.study.dwika.kplchat.data.network;

import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.model.Users;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseApiHelper {
    Observable<BaseResponse> doLogin(Login login);
//    Observable<Users> authenticatedUser(APIHeader header);
}
