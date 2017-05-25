package com.samar.sampleappmvpnetwork.util;

import com.google.gson.JsonSyntaxException;

/**
 * Created by samar on 24/11/16.
 */

public interface WebserviceResponseListener {

    /**
     * method to catch response
     *
     * @param strresponse
     *            response get from any web-service
     */
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException,NullPointerException;
    /***
     * If Error or Exception occured
     */
    public void onError() throws NullPointerException;

    public void response(String response, String via) throws JsonSyntaxException,NullPointerException;


}