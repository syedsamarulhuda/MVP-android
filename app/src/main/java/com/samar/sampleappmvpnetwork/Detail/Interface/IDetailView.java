package com.samar.sampleappmvpnetwork.Detail.Interface;

/**
 * Created by samar on 24/05/17.
 */

public interface IDetailView {

    void showProgress();
    void hideProgress();
    void setViewData(String title,String body);
}
