package com.study.dwika.kplchat.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Login;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dwika on 15-Dec-17.
 */

public class LoginPresenter implements LoginPresenterContract {

    private BaseDataManager baseDataManager;
    private LoginActivityContract loginActivityContract;
    private BaseSchedulerProvider baseSchedulerProvider;
    private CompositeDisposable compositeDisposable;
    private Context context;

    public LoginPresenter(BaseDataManager baseDataManager, LoginActivityContract loginActivityContract, BaseSchedulerProvider baseSchedulerProvider) {
        this.baseDataManager = baseDataManager;
        this.loginActivityContract = loginActivityContract;
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
                                   loginActivityContract.hideLoading();
                                   baseDataManager.setAccessToken(baseResponse.getToken());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("Debug", "error " + throwable.getLocalizedMessage());
                               }
                           }
                ));
    }

}
