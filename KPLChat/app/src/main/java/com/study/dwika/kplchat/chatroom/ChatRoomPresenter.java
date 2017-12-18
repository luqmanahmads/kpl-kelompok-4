package com.study.dwika.kplchat.chatroom;

import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Messages;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by A.I on 16/12/2017.
 */

public class ChatRoomPresenter implements ChatRoomPresenterContract {

    private BaseDataManager baseDataManager;
    private CompositeDisposable compositeDisposable;
    private ChatRoomActivityContract chatRoomActivityContract;
    private BaseSchedulerProvider baseSchedulerProvider;

    public ChatRoomPresenter(BaseDataManager baseDataManager, ChatRoomActivityContract chatRoomActivityContract, BaseSchedulerProvider baseSchedulerProvider) {
        this.baseDataManager = baseDataManager;
        this.chatRoomActivityContract = chatRoomActivityContract;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getMessage() {
//        Log.d("Debug", "Presenter message 0 " + baseDataManager.getMessages().get(0).getMessage());
        chatRoomActivityContract.displayChat(baseDataManager.getMessages());
    }

    @Override
    public void sendMessage(String message) {
        Messages messages = new Messages(1, message);

        ApiHeader apiHeader = new ApiHeader("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3QvY2hhdC1hcGkvYXBpL2F1dGhlbnRpY2F0ZSIsImlhdCI6MTUxMzU5MTEwNywiZXhwIjoxNTEzNjc3NTA3LCJuYmYiOjE1MTM1OTExMDcsImp0aSI6InpLWWxUZ0ZBMkFuM3VydWwifQ.xaMUcc0DaTM3_EzGwqtuNa9GjS8_ZVM50pDJJtjI_k4");

        compositeDisposable.add(baseDataManager.sendChat(messages, apiHeader)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        Log.d("Debug", "send message " + baseResponse.getCode());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Debug", "send message error " + throwable.getLocalizedMessage());
                    }
            }));

    }

}
