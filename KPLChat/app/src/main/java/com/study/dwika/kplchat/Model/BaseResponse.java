package com.study.dwika.kplchat.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.I on 02/12/2017.
 */

public class BaseResponse {

    @SerializedName("code")
    private String code;

    @SerializedName("status")
    private String status;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
