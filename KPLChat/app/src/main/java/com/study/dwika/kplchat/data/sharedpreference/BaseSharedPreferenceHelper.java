package com.study.dwika.kplchat.data.sharedpreference;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseSharedPreferenceHelper {

    String getAccessToken();
    void setAccessToken(String accessToken);
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getPhone();
    void setPhone(String phone);
    String getEmail();
    void setEmail(String email);

}
