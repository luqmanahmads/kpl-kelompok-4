package com.study.dwika.kplchat.data.network;

import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Users;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseApiHelper {
    io.reactivex.Observable<BaseResponse> doRegister(Users users);
}
