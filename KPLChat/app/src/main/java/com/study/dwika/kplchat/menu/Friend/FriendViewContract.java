package com.study.dwika.kplchat.menu.Friend;

import com.study.dwika.kplchat.model.UsersResponse;

/**
 * Created by A.I on 15/12/2017.
 */

public interface FriendViewContract {

    void getFriend();

    void displayFriend(UsersResponse usersResponse);

}
