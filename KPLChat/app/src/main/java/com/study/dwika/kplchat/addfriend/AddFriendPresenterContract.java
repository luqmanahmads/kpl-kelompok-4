package com.study.dwika.kplchat.addfriend;

import com.study.dwika.kplchat.model.Users;

/**
 * Created by Dwika on 16-Dec-17.
 */

public interface AddFriendPresenterContract {
    void searchByEmail(String email);
    void addById(String id);

}
