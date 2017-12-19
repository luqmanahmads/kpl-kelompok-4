package com.study.dwika.kplchat.addfriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.menu.MenuActivity;
import com.study.dwika.kplchat.model.Users;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dwika on 16-Dec-17.
 */

public class AddFriendActivity extends AppCompatActivity implements AddFriendActivityContract {

    @BindView(R.id.et_add_friend_email)
    EditText etEmail;
    @BindView(R.id.tv_name_add_friend)
    TextView tvName;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Users userFound;

    private AddFriendPresenterContract mPresenter;
    private BaseDataManager baseDataManager;
    private BaseSchedulerProvider baseSchedulerProvider;
    private BaseApiHelper baseApiHelper;
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ButterKnife.bind(this);

        baseApiHelper = new ApiHelper();
        baseSharedPreferenceHelper = new SharedPreferenceHelper(this);
        baseDataManager = new DataManager(baseApiHelper, baseDatabaseHelper, baseSharedPreferenceHelper);

        mPresenter = new AddFriendPresenter(this, baseDataManager, baseSchedulerProvider);
    }

    @OnClick(R.id.btn_search_add_friend)
    void onSearchAddFriendClick(View v) {
        try {
            Log.d("Debug", "search by email " + etEmail.getText().toString());
            mPresenter.searchByEmail(etEmail.getText().toString());
        } catch (Exception e) {
            Log.d("Debug", "onSearchAddFriendClick error ");
        }
    }

    @OnClick(R.id.btn_add_friend)
    void onAddFriendClick(View v) {
        Log.d("Debug", "Clicked add friend with id " + userFound.getId());
        try {
            mPresenter.addById(String.valueOf(userFound.getId()));

            Intent menu = new Intent(this, MenuActivity.class);
            startActivity(menu);

        } catch (Exception e) {
            Log.d("Debug", "onAddFriendClick error ");
        }
    }

    @Override
    public void showUserFound(Users user) {
        try {
            userFound = user;
            tvName.setText(userFound.getName().toString());
        } catch (Exception e) {
            Log.d("Debug", "showUserFound error ");
        }
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
