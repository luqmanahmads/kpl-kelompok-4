package com.study.dwika.kplchat.addmember;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Dwika on 18-Dec-17.
 */

public class AddMemberPresenter implements AddMemberPresenterContract {

    private AddMemberActivityContract addMemberActivityContract;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeDisposable compositeDisposable;

    public AddMemberPresenter(AddMemberActivityContract addMemberActivityContract, BaseDataManager baseDataManager, BaseSchedulerProvider baseSchedulerProvider) {
        this.addMemberActivityContract = addMemberActivityContract;
        this.baseDataManager = baseDataManager;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void findAvailableFriend() {

    }

    @Override
    public void addMemberToConversation(String userId, String convId) {

    }
}
