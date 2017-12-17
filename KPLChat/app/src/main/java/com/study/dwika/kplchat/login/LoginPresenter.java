package com.study.dwika.kplchat.login;

import android.content.Context;
import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.model.UsersResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dwika on 15-Dec-17.
 */

public class LoginPresenter implements LoginPresenterContract {

    private LoginActivityContract loginActivityContract;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeDisposable compositeDisposable;
    private Context context;

    public LoginPresenter(LoginActivityContract loginActivityContract, BaseDataManager baseDataManager, BaseSchedulerProvider baseSchedulerProvider) {
        this.loginActivityContract = loginActivityContract;
        this.baseDataManager = baseDataManager;
        this.baseSchedulerProvider = baseSchedulerProvider;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void serverLogin(String email, String password) {
        loginActivityContract.showLoading();
        Log.d("Debug", "string " + email + " " + password);
        compositeDisposable.add(baseDataManager.doLogin(new Login(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                               @Override
                               public void accept(BaseResponse baseResponse) throws Exception {
                                   Log.d("Debug", "token " + baseResponse.getToken());
                                   baseDataManager.setAccessToken(baseResponse.getToken());
                                   authenticatedUserDetail();
                                   loginActivityContract.hideLoading();
                                   loginActivityContract.loginSuccess();

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("Debug", "serverLogin error - " + throwable.getLocalizedMessage());
                                   loginActivityContract.hideLoading();
                               }
                           }
                ));
    }

    @Override
    public void authenticatedUserDetail() {
        loginActivityContract.showLoading();
        compositeDisposable.add(baseDataManager.authenticatedUser(new ApiHeader(baseDataManager.getAccessToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersResponse>() {
                               @Override
                               public void accept(UsersResponse usersResponse) throws Exception {
                                   Log.d("Debug", "usersResponse " + usersResponse.getUsersData().get(0).getId());
                                   Log.d("Debug", "usersResponse " + usersResponse.getUsersData().get(0).getName());
                                   Log.d("Debug", "usersResponse " + usersResponse.getUsersData().get(0).getPhone());
                                   Log.d("Debug", "usersResponse " + usersResponse.getUsersData().get(0).getEmail());
                                   baseDataManager.setId(String.valueOf(usersResponse.getUsersData().get(0).getId()));
                                   baseDataManager.setName(usersResponse.getUsersData().get(0).getName());
                                   baseDataManager.setPhone(usersResponse.getUsersData().get(0).getPhone());
                                   baseDataManager.setEmail(usersResponse.getUsersData().get(0).getEmail());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("Debug", "authenticatedUserDetail error - " + throwable.getLocalizedMessage());
                               }
                           }
                ));
    }

}
