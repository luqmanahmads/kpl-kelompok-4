package com.study.dwika.kplchat.addmember;

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
    public void getAvailableFriends(String id) {
        addMemberActivityContract.showLoading();
        compositeDisposable.add(baseDataManager
                .getAvailableFriends(new ApiHeader(baseDataManager.getAccessToken()), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersResponse>() {
                    @Override
                    public void accept(UsersResponse usersResponse) throws Exception {
                        Log.d("Debug", "Status " + usersResponse.getStatus());
                        Log.d("Debug", "User " + usersResponse.getUsersData().size());
                        addMemberActivityContract.hideLoading();
                        addMemberActivityContract.showUserFound(usersResponse.getUsersData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Debug", "getAvailableFriends error " + throwable.getLocalizedMessage());
                        addMemberActivityContract.hideLoading();
                    }
                })
        );
    }

    @Override
    public void addMemberToConversation(String userId, String convId) {
        compositeDisposable.add(baseDataManager
                .addMember(new ApiHeader(baseDataManager.getAccessToken()), convId, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        Log.d("Debug", "Response " + baseResponse.getStatus());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Debug", "addMemberToConversation error " + throwable.getLocalizedMessage());
                    }
                })
        );
    }

}
