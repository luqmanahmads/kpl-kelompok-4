package com.study.dwika.kplchat.register;

import android.content.Context;
import android.util.Log;

import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.network.ApiEndPoint;
import com.study.dwika.kplchat.model.BaseResponse;

import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import io.reactivex.schedulers.Schedulers;

import io.reactivex.functions.Consumer;

/**
 * Created by Luqman Ahmad on 11/29/2017.
 */

public class RegisterPresenter implements RegisterPresenterContract {

    /** View **/
    private RegisterActivityContract registerActivityContract;


    /** Data **/

    private BaseDataManager baseDataManager;

    private BaseSchedulerProvider baseSchedulerProvider;

    /** Init **/
    private CompositeDisposable compositeDisposable;
    private Context context;

    public RegisterPresenter(RegisterActivityContract view, BaseDataManager baseDataManager, BaseSchedulerProvider baseSchedulerProvider){
        this.registerActivityContract = view;
        this.baseDataManager = baseDataManager;
        this.baseSchedulerProvider = baseSchedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void register(String name, String email, String password, String phonenumber){

        System.out.println("ON PRESENTER");
        System.out.println("name : "+name);
        System.out.println("email : "+email);
        System.out.println("password : "+password);
        System.out.println("phonenumber : "+phonenumber);

        registerActivityContract.showLoading();
        compositeDisposable.add(baseDataManager.doRegister(new Users(phonenumber, email, password, name))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                               @Override
                               public void accept(BaseResponse baseResponse) throws Exception {
                                   System.out.println("BERHASIL");
                                   Log.d("Debug", "token " + baseResponse.getToken());
                                   registerActivityContract.hideLoading();
                                   registerActivityContract.onSuccess();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("Debug" , "error " + throwable.getLocalizedMessage());
                                   System.out.println("ERROR");
                                   registerActivityContract.hideLoading();
                                   registerActivityContract.onError();
                               }
                           }
                ));

    }
}
