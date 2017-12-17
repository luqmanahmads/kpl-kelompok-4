package com.study.dwika.kplchat.chatroom;

import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

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
        Log.d("Debug", "Presenter message 0 " + baseDataManager.getMessages().get(0).getMessage());
        chatRoomActivityContract.displayChat(baseDataManager.getMessages());
    }
}
