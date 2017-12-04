package com.study.dwika.kplchat.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by A.I on 04/12/2017.
 */
// Contoh Model Message response yang inherit base response
public class MessagesResponse extends BaseResponse{

    @SerializedName("data")
    private List<Messages> messagesList;

    public List<Messages> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }
}
