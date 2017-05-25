package com.samar.sampleappmvpnetwork.ui.activties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samar.sampleappmvpnetwork.R;

public abstract class BaseActivity extends AppCompatActivity {

    public ViewGroup frameHeader;
    public ViewGroup progressContent;
    public ViewGroup contentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }


    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initData();


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        contentLayout = (ViewGroup) findViewById(R.id.content_frame_layout);
        progressContent = (ViewGroup) findViewById(R.id.progress_layout);
        frameHeader = (ViewGroup) findViewById(R.id.header_frame);

        if (getLayoutId() != 0 && getLayoutId() != -1) {
            View layoutView = LayoutInflater.from(this).inflate(getLayoutId(), null);
            contentLayout.addView(layoutView);
        } else {
            if (getLayoutId() == -1) {
                contentLayout.setVisibility(View.GONE);
            }
        }

        initViews();
        initData();

    }

    public ViewGroup getFrameHeader() {
        return frameHeader;
    }



    public ViewGroup getProgressContent() {
        return progressContent;
    }



    public ViewGroup getContentLayout() {
        return contentLayout;
    }


}
