package com.samar.sampleappmvpnetwork.Login.Interface;

/**
 * Created by samar on 24/05/17.
 */

public interface ILoginPresenter {

    void onResume();
    void onDestroy();
    void validateCredentials(String username, String password);
}
