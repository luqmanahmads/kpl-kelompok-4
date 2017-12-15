package com.study.dwika.kplchat.menu.Friend;

import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.model.UsersResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by A.I on 15/12/2017.
 */

public class FriendPresenter implements FriendPresenterContract{

    private BaseDataManager baseDataManager;
    private CompositeDisposable compositeDisposable;
    private FriendViewContract friendViewContract;
    private BaseSchedulerProvider baseSchedulerProvider;

    public FriendPresenter(BaseDataManager baseDataManager, FriendViewContract friendViewContract, BaseSchedulerProvider baseSchedulerProvider) {
        this.baseDataManager = baseDataManager;
        this.friendViewContract = friendViewContract;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getFriend(ApiHeader apiHeader) {
        Log.d("debug", "getfriend");
        compositeDisposable.add(baseDataManager.getFriend(apiHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersResponse>() {
                    @Override
                    public void accept(UsersResponse usersResponse) throws Exception {
                        Log.d("Debug", "friend list " + usersResponse.getUsersData().get(0).getName());
                        friendViewContract.displayFriend(usersResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error", "friend list error " + throwable.getStackTrace());
                    }
                }));

    }
}
