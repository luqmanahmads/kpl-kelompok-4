package com.study.dwika.kplchat.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A.I on 16/12/2017.
 */

public class DatabaseSource extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "messages";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_MESSAGE = "message";
    public static final String COLUMN_NAME_USER_ID = "userid";
    public static final String COLUMN_NAME_CONVERSATION_ID = "conversationid";
    public static final String COLUMN_NAME_TIMESTAMP = "timestamp";


    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "kpl-chat.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String TIMESTAMP_TYPE = " TIMESTAMP";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_MESSAGE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_CONVERSATION_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_USER_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NAME_TIMESTAMP + TIMESTAMP_TYPE + "DEFAULT CURRENT_TIMESTAMP" +
                    " )";

    public DatabaseSource(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
