package com.study.dwika.kplchat.data.network;

/**
 * Created by Dwika on 15-Dec-17.
 */

public class ApiHeader {

    private String Authorization;
    private String Accept;

    public ApiHeader(String authorization, String accept) {
        Authorization = "Bearer " + authorization;
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