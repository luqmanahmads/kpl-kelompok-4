package com.study.dwika.kplchat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dwika on 17-Dec-17.
 */

public class ConversationDetailResponse extends BaseResponse {
    @SerializedName("data")
    private List<ConversationDetail> data;

    public List<ConversationDetail> getData() {
        return data;
    }

    public void setData(List<ConversationDetail> data) {
        this.data = data;
    }
}
