package com.study.dwika.kplchat.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.study.dwika.kplchat.model.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.I on 15/12/2017.
 */

public class DatabaseHelper implements BaseDatabaseHelper {

    private DatabaseSource databaseSource;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        this.context = context;
        databaseSource = new DatabaseSource(context);
        db = databaseSource.getWritableDatabase();
    }


    @Override
    public List<Messages> getMessages() {
        List<Messages> messagesList = new ArrayList<Messages>();
        String selectQuery = "SELECT  * FROM " + DatabaseSource.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("Debug", "get message cursor " + cursor.getCount());
        if (cursor.getCount() == 0){
            Messages messages = new Messages();
            messages.setMessage("No Chat Yet!");
            messages.setUserId(0);
            messages.setConversationId(0);
            messagesList.add(messages);
        }else{
            if (cursor.moveToFirst()) {
                do {
                    Messages messages = new Messages();
                    messages.setConversationId(Integer.parseInt(cursor.getString(2)));
                    messages.setUserId(Integer.parseInt(cursor.getString(3)));
                    messages.setMessage(cursor.getString(1));

                    messagesList.add(messages);
                } while (cursor.moveToNext());
            }
        }


        return messagesList;
    }

    @Override
    public void saveMessages(Messages messages) {
        Log.d("Debug", "Db Helper messages " + messages.getMessage());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSource.COLUMN_NAME_MESSAGE, messages.getMessage());
        contentValues.put(DatabaseSource.COLUMN_NAME_CONVERSATION_ID, messages.getConversationId());
        contentValues.put(DatabaseSource.COLUMN_NAME_USER_ID, messages.getUserId());
        long success = db.insert(DatabaseSource.TABLE_NAME, null, contentValues);
        Log.d("Debug", "insert " + success);
        db.close();
    }
}
