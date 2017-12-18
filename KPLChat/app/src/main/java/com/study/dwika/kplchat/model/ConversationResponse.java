package com.study.dwika.kplchat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by A.I on 18/12/2017.
 */

public class ConversationResponse extends BaseResponse {

    @SerializedName("data")
    private List<Conversation> conversationList;

    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }
}
