package com.study.dwika.kplchat.menu.Friend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHeader;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

/**
 * Created by A.I on 27/11/2017.
 */

public class FriendFragment extends Fragment implements FriendViewContract {

    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private BaseDataManager baseDataManager;
    private FriendPresenterContract friendPresenterContract;
    private BaseSchedulerProvider baseSchedulerProvider;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(), new SharedPreferenceHelper());

        friendPresenterContract = new FriendPresenter(baseDataManager, this, baseSchedulerProvider);
        Log.d("Debug", "FriendFragment");
        getFriend();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

    @Override
    public void getFriend() {
        friendPresenterContract.getFriend(new ApiHeader("Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly9jbGFzc2lmaWVkNS5tZS9jaGF0LWFwaS9hcGkvYXV0aGVudGljYXRlIiwiaWF0IjoxNTEzMzUxNjMzLCJleHAiOjE1MTM0MzgwMzMsIm5iZiI6MTUxMzM1MTYzMywianRpIjoiNzlTN21GVHZCWFVHT1UwUyJ9.jpBWJ_98Z6-_TuoatAIirebb3PHfIp820y1F-CjusqQ", "application/json"));
    }
}
