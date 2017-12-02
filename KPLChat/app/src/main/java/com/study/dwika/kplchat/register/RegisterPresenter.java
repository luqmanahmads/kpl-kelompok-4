package com.study.dwika.kplchat.register;

import java.io.IOException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Luqman Ahmad on 11/29/2017.
 */

public class RegisterPresenter {
    private RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenter(RegisterView view){
        this.registerView = view;
        this.registerModel = new RegisterModel();
    }

    public void onRegisterClicked(String username, String email, String password){

        System.out.println("IN PRESENTER");

        /** Show progress bar when waiting data **/
        registerView.showProgress();

        /** Ask model for data **/
//        boolean success = registerModel.requestRegister(username, email, password);

        final String param_username = username;
        final String param_email = email;
        final String param_password = password;

        new Thread() {
            public void run() {
                boolean success = false;
                Message msg = Message.obtain();
                Bundle b = new Bundle();
                msg.what = 1;
                try {
                    success = registerModel.requestRegister(param_username, param_email, param_password);
                    b.putBoolean("success", success);
                    msg.setData(b);
                }catch (Exception e1) {
                    e1.printStackTrace();
                    success = false;
                }

                handler.sendMessage(msg);
            }
        }.start();
    }

    private Handler handler= new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            boolean success = msg.getData().getBoolean("success");

            /** Stop the progress bar **/
            registerView.hideProgress();

            if(success){
                registerView.onSuccess();
            }
            else {
                registerView.showError("Register Failed");
            }
        }
    };
}
