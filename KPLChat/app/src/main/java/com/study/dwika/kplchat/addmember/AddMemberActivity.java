package com.study.dwika.kplchat.addmember;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Dwika on 18-Dec-17.
 */

public class AddMemberActivity extends AppCompatActivity implements AddMemberActivityContract {

    @BindView(R.id.lv_add_member)
    ListView lvAddMember;

    private AddMemberPresenterContract mPresenter;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        ButterKnife.bind(this);

        // Hardcode Conversation ID
        mPresenter.getAvailableFriends("1");
    }

    @OnItemClick(R.id.lv_add_member)
    public void onFriendClick(int position) {
        Toast.makeText(this, "Clicked position " + position + "!", Toast.LENGTH_SHORT).show();
    }

}
