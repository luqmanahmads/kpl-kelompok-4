package com.study.dwika.kplchat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.I on 28/11/2017.
 */

public class Messages {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("conversation_id")
    private int conversationId;

    @SerializedName("message")
    private String message;

    public Messages() {
    }

    public Messages(int userId, int conversationId, String message) {
        this.userId = userId;
        this.conversationId = conversationId;
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
