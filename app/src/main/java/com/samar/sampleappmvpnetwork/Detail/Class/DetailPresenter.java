package com.samar.sampleappmvpnetwork.Detail.Class;

import android.content.Context;

import com.samar.sampleappmvpnetwork.Detail.Interface.IDetailInteractor;
import com.samar.sampleappmvpnetwork.Detail.Interface.IDetailPresenter;
import com.samar.sampleappmvpnetwork.Detail.Interface.IDetailView;
import com.samar.sampleappmvpnetwork.Login.Interface.ILoginPresenter;

/**
 * Created by samar on 24/05/17.
 */


public class DetailPresenter implements IDetailPresenter ,IDetailInteractor.OnLoadFinishListener{

    private IDetailView detailView;
    private DetailInteractor detailInteractor;
    private Context context;

    public DetailPresenter(IDetailView detailView,Context context) {
        this.detailView = detailView;
        this.detailInteractor= new DetailInteractor();
        this.context=context;
    }


    @Override
    public void addData() {
        if(detailView!=null)
        detailInteractor.loadData(detailView.getProgress(),detailView.getContainer(),detailView.getHeader(),this,context);
    }


    @Override
    public void setData(String title, String body) {

        detailView.setViewData(title,body);
    }
}
