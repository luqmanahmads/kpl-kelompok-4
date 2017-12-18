package com.study.dwika.kplchat.chatroom;

import com.study.dwika.kplchat.model.Messages;

import java.util.List;

/**
 * Created by A.I on 16/12/2017.
 */

public interface ChatRoomActivityContract {

    void displayChat(List<Messages> messagesList);
}
