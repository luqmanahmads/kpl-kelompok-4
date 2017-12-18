package com.study.dwika.kplchat.data.network;

import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.study.dwika.kplchat.model.BaseResponse;

import com.study.dwika.kplchat.model.ConversationDetailResponse;

import com.study.dwika.kplchat.model.ConversationResponse;


import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.model.Messages;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.model.UsersResponse;


import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public class ApiHelper implements BaseApiHelper {
    @Override
    public Observable<BaseResponse> doRegister(Users users) {
        return Rx2AndroidNetworking.post(ApiEndPoint.BASE_URL+ ApiEndPoint.REGISTER)
                .addBodyParameter(users)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> doLogin(Login login) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN)
                .addBodyParameter(login)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<UsersResponse> authenticatedUser(ApiHeader header) {
        return Rx2AndroidNetworking.get(ApiEndPoint.AUTHENTICATED_USER)
                .addHeaders(header)
                .build()
                .getObjectObservable(UsersResponse.class);
    }

    @Override
    public Observable<UsersResponse> searchUserByEmail(ApiHeader header, String email) {
        Log.d("Debug","Calls apiHelper.searchUserByEmail");
        return Rx2AndroidNetworking.get(ApiEndPoint.SEARCH_USER_BY_EMAIL)
                .addHeaders(header)
                .addPathParameter("email",email)
                .build()
                .getObjectObservable(UsersResponse.class);
    }

    @Override
    public Observable<BaseResponse> addFriend(ApiHeader header, String id) {
        Log.d("Debug","Calls apiHelper.addFriend");
        return Rx2AndroidNetworking.post(ApiEndPoint.ADD_FRIEND)
                .addHeaders(header)
                .addPathParameter("id",id)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<ConversationDetailResponse> conversationDetail(ApiHeader header, String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CONVERSATION_DETAIL)
                .addHeaders(header)
                .addPathParameter("id",id)
                .build()
                .getObjectObservable(ConversationDetailResponse.class);
    }

    @Override
    public Observable<UsersResponse> getFriend(ApiHeader apiHeader) {
        Log.d("debug", "apihelper friend");
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_FRIEND)
                .addHeaders(apiHeader)
                .build()
                .getObjectObservable(UsersResponse.class);
    }

    @Override
    public Observable<UsersResponse> getAvailableFriends(ApiHeader header, String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.AVAILABLE_FRIENDS_TO_ADD)
                .addHeaders(header)
                .addPathParameter("convId",id)
                .build()
                .getObjectObservable(UsersResponse.class);
    }

    @Override
    public Observable<BaseResponse> sendChat(Messages messages, ApiHeader apiHeader) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SEND_CHAT)
                .addPathParameter("conversation_id", Integer.toString(messages.getConversationId()))
                .addHeaders(apiHeader)
                .addBodyParameter(messages)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> addMember(ApiHeader header, String convId, String userId) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ADD_TO_CONVERSATION)
                .addHeaders(header)
                .addPathParameter("convId",convId)
                .addPathParameter("userId",userId)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<ConversationResponse> getConversation(ApiHeader apiHeader) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_CHAT)
                .addHeaders(apiHeader)
                .build()
                .getObjectObservable(ConversationResponse.class);
    }

}
