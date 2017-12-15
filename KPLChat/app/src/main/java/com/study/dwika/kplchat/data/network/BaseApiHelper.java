package com.study.dwika.kplchat.data.network;

import com.study.dwika.kplchat.model.UsersResponse;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseApiHelper {

    Observable<UsersResponse> getFriend(ApiHeader apiHeader);
}
