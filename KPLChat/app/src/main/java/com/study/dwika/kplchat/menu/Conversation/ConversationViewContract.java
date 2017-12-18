package com.study.dwika.kplchat.menu.Conversation;

import com.study.dwika.kplchat.model.ConversationResponse;

/**
 * Created by A.I on 15/12/2017.
 */

public interface ConversationViewContract {

    void getConversation();

    void displayConversation(ConversationResponse conversationResponse);
}
