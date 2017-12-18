package com.study.dwika.kplchat.addmember;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Dwika on 18-Dec-17.
 */

public class AddMemberActivity extends AppCompatActivity implements AddMemberActivityContract {

    @BindView(R.id.lv_add_member)
    ListView lvAddMember;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private AddMemberPresenterContract mPresenter;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;
    private AddMemberAdapter addMemberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        ButterKnife.bind(this);

        baseApiHelper = new ApiHelper();
        baseSharedPreferenceHelper = new SharedPreferenceHelper(this);
        baseDataManager = new DataManager(baseApiHelper, baseDatabaseHelper, baseSharedPreferenceHelper);

        mPresenter = new AddMemberPresenter(this, baseDataManager, baseSchedulerProvider);

        // Hardcode Conversation ID
        mPresenter.getAvailableFriends("1");
    }

    @OnItemClick(R.id.lv_add_member)
    public void onFriendClick(int position) {

        final Users clickedUser = addMemberAdapter.findClicked(position);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add Member?");
        alert.setMessage("Add " + clickedUser.getName() + " to conversation?");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Hardcode Conversation ID
                mPresenter.addMemberToConversation(String.valueOf(clickedUser.getId()), "1");
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();

    }

    @Override
    public void showUserFound(List<Users> usersList) {
        addMemberAdapter = new AddMemberAdapter(this, usersList);
        lvAddMember.setAdapter(addMemberAdapter);

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
