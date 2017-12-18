package com.study.dwika.kplchat.menu.Conversation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.model.ConversationResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A.I on 27/11/2017.
 */

public class ConversationFragment extends Fragment implements ConversationViewContract{

    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private BaseDataManager baseDataManager;
    private ConversationPresenterContract conversationPresenterContract;
    private BaseSchedulerProvider baseSchedulerProvider;
    private ConversationAdapter conversationAdapter;

    @BindView(R.id.rvConversation)
    RecyclerView rvConversation;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(getContext()), new SharedPreferenceHelper(getContext()));

        conversationPresenterContract = new ConversationPresenter(baseDataManager, this, baseSchedulerProvider);

        getConversation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvConversation.setLayoutManager(layoutManager);
        rvConversation.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        return view;
    }


    @Override
    public void getConversation() {

        conversationPresenterContract.getConversation(new ApiHeader(baseDataManager.getAccessToken()));


    }

    @Override
    public void displayConversation(ConversationResponse conversationResponse) {
        Log.d("Debug", "display conversation " + conversationResponse.getConversationList().get(0).getTitle());
        conversationAdapter = new ConversationAdapter(conversationResponse.getConversationList(), getContext());
        rvConversation.setAdapter(conversationAdapter);

    }
}
