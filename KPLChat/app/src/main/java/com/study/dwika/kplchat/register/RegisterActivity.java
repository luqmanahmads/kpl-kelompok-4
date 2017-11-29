package com.study.dwika.kplchat.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.study.dwika.kplchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luqman Ahmad on 11/29/2017.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    /** Presenter **/

    private RegisterPresenter presenter;


    /** Attribute of activity_register.xml **/

    @BindView(R.id.et_reg_username)
    public EditText mUsernameEditText;

    @BindView(R.id.et_reg_email)
    public EditText mEmailEditText;

    @BindView(R.id.et_reg_password)
    public EditText mPasswordEditText;

    @BindView(R.id.pb_reg)
    public ProgressBar mRegisterProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        /** Binding presenter **/
        presenter = new RegisterPresenter(this);
    }

    public void onRegisterClick(View view) {

        /** Pass data to presenter if register clicked **/
        presenter.onRegisterClicked(mUsernameEditText.getText().toString() , mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());

    }

    public void showProgress(){

        /** Set progress bar to visible **/
        mRegisterProgressBar.setVisibility(View.VISIBLE);

    }

    public void hideProgress(){

        /** Set progress bar to invisible **/
        mRegisterProgressBar.setVisibility(View.INVISIBLE);

    }

    public void showError(String error){

        /** Show register error **/
        Toast.makeText(getBaseContext(), "Unknown error", Toast.LENGTH_LONG).show();

    }

    public void onSuccess(){

        /** Go to menu **/
        Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG).show();

    }
}
