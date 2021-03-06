package com.study.dwika.kplchat.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.addmember.AddMemberActivity;
import com.study.dwika.kplchat.conversationdetail.ConversationDetailActivity;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.network.RabbitMQSender;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.model.Messages;
import com.study.dwika.kplchat.service.ReceiverService;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChatRoomActivity extends AppCompatActivity implements ChatRoomActivityContract {

    @BindView(R.id.etChat)
    EditText etChat;

    @BindView(R.id.btnSendChat)
    Button btnSendChat;

    @BindView(R.id.rvChat)
    RecyclerView rvChat;

    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private BaseDataManager baseDataManager;
    private ChatRoomPresenterContract chatRoomPresenterContract;
    private BaseSchedulerProvider baseSchedulerProvider;
    private ChatRoomAdapter chatRoomAdapter;
    private Handler handler;
    private RabbitMQSender rabbitMQSender;
    private int conversationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        conversationId = intent.getIntExtra("conversationId", 0);

        startService(new Intent(this, ReceiverService.class));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvChat.setLayoutManager(layoutManager);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(this), new SharedPreferenceHelper(this));
        chatRoomPresenterContract = new ChatRoomPresenter(baseDataManager, this, baseSchedulerProvider);

//        rabbitMQSender = new RabbitMQSender();

        handler =new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                chatRoomPresenterContract.getMessage(conversationId);
                handler.postDelayed(this, 5000);
            }
        }, 5000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conversation_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.ac_conversation_detail) {
            Intent intent = new Intent(this, ConversationDetailActivity.class);
            intent.putExtra("conversationId",conversationId);
            startActivity(intent);
        }else if(id == R.id.ac_add_member){
            Intent intent = new Intent(this, AddMemberActivity.class);
            intent.putExtra("conversationId",conversationId);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void displayChat(List<Messages> messagesList) {
        Log.d("Debug", "chat number 0 " + messagesList.get(0).getMessage());
        chatRoomAdapter = new ChatRoomAdapter( messagesList,this,  Integer.parseInt(baseDataManager.getId()));

        rvChat.setAdapter(chatRoomAdapter);
    }

    @OnClick(R.id.btnSendChat)
    public void sendChat(){
        chatRoomPresenterContract.sendMessage(etChat.getText().toString(), conversationId);
//        rabbitMQSender.publishToAMQP();
    }
}
