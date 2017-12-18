package com.study.dwika.kplchat.menu.Conversation;

import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.model.ConversationResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by A.I on 15/12/2017.
 */

public class ConversationPresenter implements ConversationPresenterContract{

    private BaseDataManager baseDataManager;
    private CompositeDisposable compositeDisposable;
    private ConversationViewContract conversationViewContract;
    private BaseSchedulerProvider baseSchedulerProvider;

    public ConversationPresenter(BaseDataManager baseDataManager, ConversationViewContract conversationViewContract, BaseSchedulerProvider baseSchedulerProvider) {
        this.baseDataManager = baseDataManager;
        this.conversationViewContract = conversationViewContract;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }



    @Override
    public void setPresenter(ConversationPresenterContract conversationPresenterContract) {

    }

    @Override
    public void getConversation(ApiHeader apiHeader) {
        compositeDisposable.add(baseDataManager.getConversation(apiHeader)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ConversationResponse>() {
                                    @Override
                                    public void accept(ConversationResponse conversationResponse) throws Exception {
                                        Log.d("Debug", "Conversation status " + conversationResponse.getConversationList().get(0).getTitle());
                                        conversationViewContract.displayConversation(conversationResponse);

                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Log.d("Debug", "Conversation error " + throwable.getLocalizedMessage());
                                    }
                                }
                            ));
    }


}
