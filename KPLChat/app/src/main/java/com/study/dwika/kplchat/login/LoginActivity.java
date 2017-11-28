package com.study.dwika.kplchat.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.study.dwika.kplchat.R;

import butterknife.BindView;

/**
 * Created by Dwika on 28-Nov-17.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_username)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}