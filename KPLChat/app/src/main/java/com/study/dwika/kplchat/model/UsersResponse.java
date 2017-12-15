package com.study.dwika.kplchat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse extends BaseResponse{

    @SerializedName("data")
    private List<Users> usersData;

    public List<Users> getUsersData() {
        return usersData;
    }

    public void setUsersData(List<Users> usersData) {
        this.usersData = usersData;
    }
}
