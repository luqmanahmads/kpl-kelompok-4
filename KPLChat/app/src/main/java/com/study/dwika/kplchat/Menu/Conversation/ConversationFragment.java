package com.study.dwika.kplchat.menu.Conversation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

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


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(), new SharedPreferenceHelper());

        conversationPresenterContract = new ConversationPresenter(baseDataManager, this, baseSchedulerProvider);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversation, container, false);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
