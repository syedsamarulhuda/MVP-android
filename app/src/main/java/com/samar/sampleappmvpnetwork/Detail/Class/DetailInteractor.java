package com.samar.sampleappmvpnetwork.Detail.Class;

import android.content.Context;

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

    OnLoadFinishListener onLoadFinishListener;

    private String url = "https://jsonplaceholder.typicode.com/posts/1";
    private String title ;
    private String body ;
    @Override
    public void loadData(OnLoadFinishListener onLoadFinishListener, Context context) {

        this.onLoadFinishListener = onLoadFinishListener;

        new WebservicePostJsonAsyn(context, this, url, null, null, "get", null, null, 111).execute();

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
