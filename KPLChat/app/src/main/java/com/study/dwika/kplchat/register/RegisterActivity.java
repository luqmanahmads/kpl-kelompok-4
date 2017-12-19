package com.study.dwika.kplchat.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.data.BaseDataManager;
import com.study.dwika.kplchat.data.DataManager;
import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.database.DatabaseHelper;
import com.study.dwika.kplchat.data.network.ApiHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.data.sharedpreference.SharedPreferenceHelper;
import com.study.dwika.kplchat.login.LoginActivity;
import com.study.dwika.kplchat.utils.BaseSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luqman Ahmad on 11/29/2017.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterActivityContract{

    /** Presenter **/

    private RegisterPresenter presenter;

    /** Data **/

    private BaseDataManager baseDataManager;//data manager

    private BaseApiHelper baseApiHelper;//all data source
    private BaseDatabaseHelper baseDatabaseHelper;
    private BaseSharedPreferenceHelper baseSharedPreferenceHelper;

    private BaseSchedulerProvider baseSchedulerProvider;

    /** Attribute of activity_register.xml **/

    @BindView(R.id.et_reg_name)
    public EditText mNameEditText;

    @BindView(R.id.et_reg_email)
    public EditText mEmailEditText;

    @BindView(R.id.et_reg_password)
    public EditText mPasswordEditText;

    @BindView(R.id.et_reg_phonenumber)
    public EditText mPhonenumberEditText;

    @BindView(R.id.pb_reg)
    public ProgressBar mRegisterProgressBar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        ButterKnife.bind(this);

        AndroidNetworking.initialize(getApplicationContext());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        /** Data Manager Init **/
        baseApiHelper = new ApiHelper();

        baseDatabaseHelper = new DatabaseHelper(this);

        baseSharedPreferenceHelper = new SharedPreferenceHelper(this);
        baseDataManager = new DataManager(baseApiHelper, baseDatabaseHelper, baseSharedPreferenceHelper);

        /** Binding presenter **/
        presenter = new RegisterPresenter(this, baseDataManager, baseSchedulerProvider);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_reg)
    public void onRegisterClick(View view) {
        /** Pass data to presenter if register clicked **/
        System.out.println("ON REGISTER CLICK");
        presenter.register(mNameEditText.getText().toString(), mEmailEditText.getText().toString(), mPasswordEditText.getText().toString(), mPhonenumberEditText.getText().toString());
    }

    @Override
    public void showLoading(){
        progressDialog.show();
    }

    @Override
    public void hideLoading(){
        progressDialog.dismiss();

    }

    @Override
    public void onSuccess(){
        Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(register);
        finish();
    }

    @Override
    public void onError(){
        Toast.makeText(getBaseContext(), "Unknown error", Toast.LENGTH_LONG).show();

    }
}
