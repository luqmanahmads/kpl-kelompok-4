package com.study.dwika.kplchat.menu.Friend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.study.dwika.kplchat.model.UsersResponse;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private FriendAdapter friendAdapter;

    @BindView(R.id.rvFriend)
    RecyclerView rvFriend;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        baseDataManager = new DataManager(new ApiHelper(), new DatabaseHelper(getContext()), new SharedPreferenceHelper(getContext()));

        friendPresenterContract = new FriendPresenter(baseDataManager, this, baseSchedulerProvider);
//        Log.d("Debug", "FriendFragment");
        getFriend();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvFriend.setLayoutManager(layoutManager);
        rvFriend.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        return view;
    }

    @Override
    public void getFriend() {
        friendPresenterContract.getFriend(new ApiHeader(baseDataManager.getAccessToken()));
    }

    @Override
    public void displayFriend(UsersResponse usersResponse) {
        friendAdapter = new FriendAdapter(getContext(), usersResponse.getUsersData());
        rvFriend.setAdapter(friendAdapter);

    }

}
