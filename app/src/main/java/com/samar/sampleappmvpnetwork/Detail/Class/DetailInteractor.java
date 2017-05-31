package com.samar.sampleappmvpnetwork.Detail.Class;

import android.content.Context;
import android.view.ViewGroup;

import com.google.gson.JsonSyntaxException;
import com.samar.sampleappmvpnetwork.Detail.Interface.IDetailInteractor;
import com.samar.sampleappmvpnetwork.util.WebservicePostJsonAsyn;
import com.samar.sampleappmvpnetwork.util.WebserviceResponseListener;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by samar on 24/05/17.
 */

public class DetailInteractor implements WebserviceResponseListener, IDetailInteractor {

    private OnLoadFinishListener onLoadFinishListener;
    private String title;
    private String body;

    @Override
    public void loadData(ViewGroup progress, ViewGroup header, ViewGroup content, OnLoadFinishListener onLoadFinishListener, Context context) {
        this.onLoadFinishListener = onLoadFinishListener;

        String url = "https://jsonplaceholder.typicode.com/posts/1";
        new WebservicePostJsonAsyn(context, this, url, 111, "get", null, progress, content, header).execute();

    }

    @Override
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException, NullPointerException {
        try {
            JSONObject jsonObject = new JSONObject(strresponse);
            title = jsonObject.getString("title");
            body = jsonObject.getString("body");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        onLoadFinishListener.setData(title, body);
    }

    @Override
    public void onError() throws NullPointerException {

    }

    @Override
    public void response(String response, String via) throws JsonSyntaxException, NullPointerException {

    }



}
