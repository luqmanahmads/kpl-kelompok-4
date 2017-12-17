package com.study.dwika.kplchat.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dwika on 28-Nov-17.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_username)
    EditText mUsernameEditText;
    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void onLoginClick(View view) {
        Toast.makeText(getBaseContext(), "Login.\nUsername:" + mUsernameEditText.getText() + ".\nPassword:" + mPasswordEditText.getText() + ".", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_to_register)
    void onRegisterClick(View view) {
        Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(register);
        finish();
    }
}