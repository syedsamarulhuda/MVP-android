package com.samar.sampleappmvpnetwork.Login.Interface;

/**
 * Created by samar on 24/05/17.
 */

public interface ILoginInteractor {

    interface OnLoginFinishListener{

        void onUsernameError();
        void onPasswordError();
        void onInvalidCredentials(String msg);
        void onSuccess();
    }


    void login(String name, String password,OnLoginFinishListener onLoginFinishListener);

}
