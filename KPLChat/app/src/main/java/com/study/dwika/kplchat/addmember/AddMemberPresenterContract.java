package com.study.dwika.kplchat.addmember;

/**
 * Created by Dwika on 18-Dec-17.
 */

public interface AddMemberPresenterContract {

    void findAvailableFriend();
    void addMemberToConversation(String userId, String convId);
}
