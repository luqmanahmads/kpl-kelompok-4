package com.study.dwika.kplchat.conversationdetail;

import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.model.ConversationDetailResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dwika on 17-Dec-17.
 */

public class ConversationDetailPresenter implements ConversationDetailPresenterContract {

    private ConversationDetailActivityContract conversationDetailActivityContract;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeDisposable compositeDisposable;

    public ConversationDetailPresenter(ConversationDetailActivityContract conversationDetailActivityContract, BaseDataManager baseDataManager, BaseSchedulerProvider baseSchedulerProvider) {
        this.conversationDetailActivityContract = conversationDetailActivityContract;
        this.baseDataManager = baseDataManager;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void findConversationDetail(String id) {
        conversationDetailActivityContract.showLoading();
        compositeDisposable.add(baseDataManager
                .conversationDetail(new ApiHeader(baseDataManager.getAccessToken()), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ConversationDetailResponse>() {
                    @Override
                    public void accept(ConversationDetailResponse conversationDetailResponse) throws Exception {
                        conversationDetailActivityContract.showConversationDetail(conversationDetailResponse.getData().get(0));
                        conversationDetailActivityContract.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Debug","findConversationDetail error " + throwable.getLocalizedMessage());
                        conversationDetailActivityContract.hideLoading();
                    }
                })
        );
    }
}
