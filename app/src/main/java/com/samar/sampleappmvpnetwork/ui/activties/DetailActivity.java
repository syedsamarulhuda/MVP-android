package com.samar.sampleappmvpnetwork.ui.activties;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samar.sampleappmvpnetwork.Detail.Class.DetailPresenter;
import com.samar.sampleappmvpnetwork.Detail.Interface.IDetailView;
import com.samar.sampleappmvpnetwork.R;

public class DetailActivity extends BaseActivity implements IDetailView {

    TextView tvTitle, tvBody;

    DetailPresenter detailPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initViews() {

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvBody = (TextView) findViewById(R.id.tvBody);


    }

    @Override
    protected void initData() {

        detailPresenter = new DetailPresenter(this, DetailActivity.this);
        detailPresenter.addData();

    }


    @Override
    public ViewGroup getProgress() {
        return getProgressView();
    }

    @Override
    public ViewGroup getContainer() {
        return getContentView();
    }

    @Override
    public ViewGroup getHeader() {
        return getHeaderView();
    }

    @Override
    public void setViewData(String title, String body) {

        tvTitle.setText(title);
        tvBody.setText(body);

    }
}
