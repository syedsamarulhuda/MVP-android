package com.samar.sampleappmvpnetwork.ui.activties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samar.sampleappmvpnetwork.R;

public abstract class BaseActivity extends AppCompatActivity {

    public ViewGroup header;
    public ViewGroup progress;
    public ViewGroup content;
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

        content = (ViewGroup) findViewById(R.id.content_frame_layout);
        progress = (ViewGroup) findViewById(R.id.progress_layout);
        header = (ViewGroup) findViewById(R.id.header_frame);

        if (getLayoutId() != 0 && getLayoutId() != -1) {
            View layoutView = LayoutInflater.from(this).inflate(getLayoutId(), null);
            content.addView(layoutView);
        } else {
            if (getLayoutId() == -1) {
                content.setVisibility(View.GONE);
            }
        }

        initViews();
        initData();

    }

    public ViewGroup getHeaderView() {
        return header;
    }



    public ViewGroup getProgressView() {
        return progress;
    }



    public ViewGroup getContentView() {
        return content;
    }


}
