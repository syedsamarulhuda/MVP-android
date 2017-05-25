package com.samar.sampleappmvpnetwork.Login.Interface;

/**
 * Created by samar on 24/05/17.
 */

public interface ILoginView {

    void showProgress();
    void hideProgress();
    void setUsernameError();
    void setPasswordError();
    void setInvalidCredentials(String msg);
    void navToDetailActivity();

}
