package com.study.dwika.kplchat.data.network;

import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.ConversationDetailResponse;
import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.model.UsersResponse;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseApiHelper {

    io.reactivex.Observable<BaseResponse> doRegister(Users users);
    Observable<BaseResponse> doLogin(Login login);
    Observable<UsersResponse> authenticatedUser(ApiHeader header);
    Observable<UsersResponse> searchUserByEmail(ApiHeader header, String email);
    Observable<BaseResponse> addFriend(ApiHeader header, String id);
    Observable<ConversationDetailResponse> conversationDetail(ApiHeader header, String id);
    Observable<UsersResponse> getFriend(ApiHeader apiHeader);

}
