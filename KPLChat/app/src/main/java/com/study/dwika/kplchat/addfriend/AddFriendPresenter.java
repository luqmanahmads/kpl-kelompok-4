package com.study.dwika.kplchat.addfriend;

import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.UsersResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

    @Override
    public void searchByEmail(String email) {
        compositeDisposable.add(baseDataManager
                .searchUserByEmail(new ApiHeader(baseDataManager.getAccessToken()), email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersResponse>() {
                               @Override
                               public void accept(UsersResponse usersResponse) throws Exception {
                                   Log.d("Debug", "user name " + usersResponse.getUsersData().get(0).getName());
                                   addFriendActivityContract.showUserFound(usersResponse.getUsersData().get(0));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("Debug", "searchByEmail error " + throwable.getLocalizedMessage());
                               }
                           }
                )
        );
    }

    @Override
    public void addById(String id) {
        compositeDisposable.add(baseDataManager
                .addFriend(new ApiHeader(baseDataManager.getAccessToken()), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                               @Override
                               public void accept(BaseResponse baseResponse) throws Exception {
                                   Log.d("Debug", "response " + baseResponse.getStatus());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("Debug", "addById error - " + throwable.getLocalizedMessage());
                               }
                           }
                )
        );
    }

}
