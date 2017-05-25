package com.samar.sampleappmvpnetwork.Detail.Interface;

import android.content.Context;

/**
 * Created by samar on 25/05/17.
 */

public interface IDetailInteractor {


    interface OnLoadFinishListener{

        void setData(String title, String body);
    }


    void loadData(OnLoadFinishListener onLoadFinishListener, Context context);
}
