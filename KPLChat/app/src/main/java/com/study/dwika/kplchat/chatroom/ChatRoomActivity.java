package com.study.dwika.kplchat.chatroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatRoomActivity extends AppCompatActivity implements ChatRoomActivityContract {

    @BindView(R.id.etChat)
    EditText etChat;

    @BindView(R.id.btnSendChat)
    Button btnSendChat;

    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private BaseDataManager baseDataManager;
    private ChatRoomPresenterContract chatRoomPresenterContract;
    private BaseSchedulerProvider baseSchedulerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ButterKnife.bind(this);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(), new SharedPreferenceHelper());
        chatRoomPresenterContract = new ChatRoomPresenter(baseDataManager, this, baseSchedulerProvider);

    }
}
