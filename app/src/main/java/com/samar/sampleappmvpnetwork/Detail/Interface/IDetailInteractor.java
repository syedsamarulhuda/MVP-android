package com.samar.sampleappmvpnetwork.Detail.Interface;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by samar on 25/05/17.
 */

public interface IDetailInteractor {


    interface OnLoadFinishListener{

        void setData(String title, String body);
    }


    void loadData(ViewGroup progress,ViewGroup header,ViewGroup content, OnLoadFinishListener onLoadFinishListener, Context context);
}
