package com.study.dwika.kplchat.addfriend;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Dwika on 16-Dec-17.
 */

public class AddFriendPresenter implements AddFriendPresenterContract {

    private AddFriendActivityContract addFriendActivityContract;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeDisposable compositeDisposable;

    public AddFriendPresenter(AddFriendActivityContract addFriendActivityContract, BaseDataManager baseDataManager, BaseSchedulerProvider baseSchedulerProvider) {
        this.addFriendActivityContract = addFriendActivityContract;
        this.baseDataManager = baseDataManager;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }
}
