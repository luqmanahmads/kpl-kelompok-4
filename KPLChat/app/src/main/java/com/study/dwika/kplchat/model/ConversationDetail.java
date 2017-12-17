package com.study.dwika.kplchat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dwika on 17-Dec-17.
 */

public class ConversationDetail{
    @SerializedName("id")
    private int id;

    @SerializedName("isGroup")
    private String isGroup;

    @SerializedName("title")
    private String title;

    @SerializedName("participants")
    private List<Users> participants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Users> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Users> participants) {
        this.participants = participants;
    }
}
