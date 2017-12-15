package com.study.dwika.kplchat.menu.Conversation;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

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

//    @Override
//    public void getFriend(ApiHeader apiHeader) {
//        compositeDisposable.add(baseDataManager.getFriend(apiHeader)
//                            .subscribeOn(baseSchedulerProvider.io())
//                            .observeOn(baseSchedulerProvider.ui())
//                            .subscribe(new Consumer<BaseResponse>() {
//                                @Override
//                                public void accept(BaseResponse baseResponse) throws Exception {
//
//                                }
//                            }, new Consumer<Throwable>() {
//                                @Override
//                                public void accept(Throwable throwable) throws Exception {
//
//                                }
//                            }));
//
//    }
}