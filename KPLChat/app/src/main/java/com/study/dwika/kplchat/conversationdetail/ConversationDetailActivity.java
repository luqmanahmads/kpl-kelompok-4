package com.study.dwika.kplchat.conversationdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.model.ConversationDetail;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dwika on 17-Dec-17.
 */

public class ConversationDetailActivity extends AppCompatActivity implements ConversationDetailActivityContract {

    @BindView(R.id.tv_conversation_title)
    TextView tvConversationTitle;

    @BindView(R.id.lv_participants)
    ListView lvParticipants;

    private ConversationDetailPresenterContract mPresenter;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private int conversationId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_detail);

        Intent intent = getIntent();
        conversationId = intent.getIntExtra("conversationId", 0);

        ButterKnife.bind(this);

        baseApiHelper = new ApiHelper();
        baseSharedPreferenceHelper = new SharedPreferenceHelper(this);
        baseDataManager = new DataManager(baseApiHelper, baseDatabaseHelper, baseSharedPreferenceHelper);

        mPresenter = new ConversationDetailPresenter(this, baseDataManager,baseSchedulerProvider);

        mPresenter.findConversationDetail(String.valueOf(conversationId));
    }

    @Override
    public void showConversationDetail(ConversationDetail conversationDetail) {
        ArrayList<String> list = new ArrayList<>();
        for (Users user : conversationDetail.getParticipants()){
            list.add(user.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.list_conversation_participants,list);
        lvParticipants.setAdapter(adapter);
    }

}
