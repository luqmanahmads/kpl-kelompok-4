package com.study.dwika.kplchat.data.database;

import com.study.dwika.kplchat.model.Messages;

import java.util.List;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseDatabaseHelper {

    List<Messages> getMessages();

    void saveMessages(Messages messages);
}
