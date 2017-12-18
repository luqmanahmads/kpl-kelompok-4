package com.study.dwika.kplchat.menu.Conversation;

import com.study.dwika.kplchat.data.network.ApiHeader;

/**
 * Created by A.I on 15/12/2017.
 */

public interface ConversationPresenterContract {

    void setPresenter(ConversationPresenterContract conversationPresenterContract);

    void getConversation(ApiHeader apiHeader);

}
