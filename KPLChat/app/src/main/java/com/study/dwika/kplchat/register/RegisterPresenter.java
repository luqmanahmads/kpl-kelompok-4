package com.study.dwika.kplchat.register;

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

        /** Show progress bar when waiting data **/
        registerView.showProgress();

        /** Ask model for data **/
        boolean success = registerModel.requestRegister(username, email, password);

        /** Stop the progress bar **/
        registerView.hideProgress();

        if(success){
            registerView.onSuccess();
        }
        else {
            registerView.showError("Register Failed");
        }
    }
}
