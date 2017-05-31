package com.samar.sampleappmvpnetwork.ui.activties;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samar.sampleappmvpnetwork.Login.Class.LoginPresenter;
import com.samar.sampleappmvpnetwork.Login.Interface.ILoginView;
import com.samar.sampleappmvpnetwork.R;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;

    TextView tvErrorMsg;

    LoginPresenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        Intent intentToDetail = new Intent(this, DetailActivity.class);
        startActivity(intentToDetail);
        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        tvErrorMsg=(TextView)findViewById(R.id.tvInvalidUserMsg);
        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
       presenter= new LoginPresenter(this);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public String getUsername() {
        return edtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void setUsernameError() {

        edtUsername.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {

        edtPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void setInvalidCredentials(String msg) {

        tvErrorMsg.setText(msg);
        tvErrorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void navToDetailActivity() {
        Intent intentToDetail = new Intent(this, DetailActivity.class);
        startActivity(intentToDetail);
        finish();
    }

    @Override
    public void onClick(View view) {

        presenter.validateCredentials(edtUsername.getText().toString(),edtPassword.getText().toString());

    }
}
