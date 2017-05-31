package com.samar.sampleappmvpnetwork.Login.Class;

import android.text.TextUtils;

import com.samar.sampleappmvpnetwork.Login.Interface.ILoginInteractor;

/**
 * Created by samar on 24/05/17.
 */

public class LoginInteractor implements ILoginInteractor {


    private String uName="samar";
    private String pass="samar123";
    private String msg="Wrong Credentials";
     OnLoginFinishListener finishListener;

    @Override
    public void login(String name, String password, OnLoginFinishListener onLoginFinishListener) {

        this.finishListener=onLoginFinishListener;
        boolean error = false;
        if (TextUtils.isEmpty(name)){
            onLoginFinishListener.onUsernameError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(password)){
            onLoginFinishListener.onPasswordError();
            error = true;
            return;
        }

        if(!name.equalsIgnoreCase(uName) ||!password.equalsIgnoreCase(pass) )
        {
            onLoginFinishListener.onInvalidCredentials(msg);
            error = true;
            return;
        }


        if (!error){
            onLoginFinishListener.onSuccess();
        }
    }

    @Override
    public OnLoginFinishListener getOnLogin() {
        return finishListener;
    }

}
