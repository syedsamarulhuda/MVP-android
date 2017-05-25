package com.samar.sampleappmvpnetwork.Login.Class;

import com.samar.sampleappmvpnetwork.Login.Interface.ILoginPresenter;
import com.samar.sampleappmvpnetwork.Login.Interface.ILoginView;

/**
 * Created by samar on 24/05/17.
 */

public class LoginPresenter implements ILoginPresenter,LoginInteractor.OnLoginFinishListener {

    private ILoginView iLoginView;

    private LoginInteractor interactor;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.interactor=new LoginInteractor();
    }


    @Override
    public void validateCredentials(String username, String password) {

        if(iLoginView!=null)
            iLoginView.showProgress();

        interactor.login(username,password,this);

    }

    @Override
    public void onUsernameError() {

        if(iLoginView!=null)
        {
            iLoginView.setUsernameError();
            iLoginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if(iLoginView!=null)
        {
            iLoginView.setPasswordError();
            iLoginView.hideProgress();
        }
    }

    @Override
    public void onInvalidCredentials(String msg) {
        if(iLoginView!=null)
        {
            iLoginView.setInvalidCredentials(msg);
        }
    }

    @Override
    public void onSuccess() {

        if(iLoginView!=null)
        {
            iLoginView.navToDetailActivity();
        }
    }
}

