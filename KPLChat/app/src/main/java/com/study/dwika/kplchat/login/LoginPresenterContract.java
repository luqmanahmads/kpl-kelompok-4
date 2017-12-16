package com.study.dwika.kplchat.login;

import com.study.dwika.kplchat.data.network.ApiHeader;

/**
 * Created by Dwika on 15-Dec-17.
 */

public interface LoginPresenterContract {
    void serverLogin(String email, String Password);
    void authenticatedUserDetail();

}