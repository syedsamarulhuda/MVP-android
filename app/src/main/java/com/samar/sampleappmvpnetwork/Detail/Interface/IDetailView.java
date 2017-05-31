package com.samar.sampleappmvpnetwork.Detail.Interface;

import android.view.ViewGroup;

/**
 * Created by samar on 24/05/17.
 */

public interface IDetailView {

    ViewGroup getProgress();
    ViewGroup getContainer();
    ViewGroup getHeader();
    void setViewData(String title,String body);
}
