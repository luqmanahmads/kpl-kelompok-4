package com.study.dwika.kplchat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.I on 28/11/2017.
 */

public class Conversation {

    @SerializedName("id")
    private int conversationId;

    @SerializedName("title")
    private String title;

    @SerializedName("isGroup")
    private Boolean isGroup;

    public Conversation() {
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getGroup() {
        return isGroup;
    }

    public void setGroup(Boolean group) {
        isGroup = group;
    }
}
