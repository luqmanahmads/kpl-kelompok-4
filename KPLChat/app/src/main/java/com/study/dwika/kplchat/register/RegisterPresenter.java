package com.study.dwika.kplchat.register;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.study.dwika.kplchat.Model.Messages;
import com.study.dwika.kplchat.Model.MessagesResponse;
import com.study.dwika.kplchat.Network.APIEndPoint;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Luqman Ahmad on 11/29/2017.
 */

public class RegisterPresenter {
    private RegisterView registerView;

    public RegisterPresenter(RegisterView view){
        this.registerView = view;

    }

    public void onRegisterClicked(String username, String email, String password) {

        System.out.println("IN PRESENTER");

        /** Show progress bar when waiting data **/
        registerView.showProgress();

        /** Ask model for data **/
//        Contoh Fast android network + rx java 2 get request
        Rx2AndroidNetworking.get(APIEndPoint.BASE_URL)
                .build()
                .getObjectObservable(MessagesResponse.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessagesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MessagesResponse messagesResponse) {
                        List<Messages> messagesList = messagesResponse.getMessagesList();
                        System.out.println("message name " + messagesList.get(0).getMessage());
                        registerView.hideProgress();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
