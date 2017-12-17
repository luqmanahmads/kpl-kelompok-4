package com.study.dwika.kplchat.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.model.Messages;
import com.study.dwika.kplchat.service.ReceiverService;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ChatRoomActivity extends AppCompatActivity implements ChatRoomActivityContract {

    @BindView(R.id.etChat)
    EditText etChat;

    @BindView(R.id.btnSendChat)
    Button btnSendChat;
    @BindView(R.id.tvChat)
            TextView tvChat;

    @BindView(R.id.rvChat)
    RecyclerView rvChat;

    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private BaseDataManager baseDataManager;
    private ChatRoomPresenterContract chatRoomPresenterContract;
    private BaseSchedulerProvider baseSchedulerProvider;

    private ReceiverService receiverService;
    private ChatRoomAdapter chatRoomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ButterKnife.bind(this);

        startService(new Intent(this, ReceiverService.class));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvChat.setLayoutManager(layoutManager);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(this), new SharedPreferenceHelper(this));
        chatRoomPresenterContract = new ChatRoomPresenter(baseDataManager, this, baseSchedulerProvider);
        chatRoomPresenterContract.getMessage();
    }


    @Override
    public void displayChat(List<Messages> messagesList) {
        Log.d("Debug", "chat number 0 " + messagesList.get(0).getMessage());
        chatRoomAdapter = new ChatRoomAdapter( messagesList,this);

        rvChat.setAdapter(chatRoomAdapter);
    }
}
