package com.study.dwika.kplchat.data.network;

/**
 * Created by A.I on 15/12/2017.
 */

public class ApiHeader {

    private String Authorization, Accept;

    public ApiHeader(String authorization, String accept) {
        Authorization = authorization;
        Accept = accept;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getAccept() {
        return Accept;
    }

    public void setAccept(String accept) {
        Accept = accept;
    }
}
