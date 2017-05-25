package com.samar.sampleappmvpnetwork.util;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by samar on 25/05/17.
 */

public class OkHttpResponseJsonAsyn extends AsyncTask<String, String, String> {

    private IOkHttpResponseListner listner;
    private Context mContext;
    private String url;
    private int apiId;
    private OkHttpClient client;

    public OkHttpResponseJsonAsyn(String url, int apiId, IOkHttpResponseListner listner, Context mContext) {
        this.url = url;
        this.apiId=apiId;
        this.listner = listner;
        this.mContext = mContext;
        this.client = new OkHttpClient();
        this.listner=listner;
    }

    @Override
    protected String doInBackground(String... strings) {

        String SetServerString = "";
        try {

            SetServerString = run(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SetServerString;
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            return "404";
        } else if (response.code() == 500) {
            return "500";
        } else {
            return response.body().string();
        }
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        listner.getResponseWithId(response,apiId);
    }
}
