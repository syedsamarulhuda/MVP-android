package com.samar.sampleappmvpnetwork.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.samar.logutil.LogUtil;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.zip.GZIPInputStream;

/**
 * Created by samar on 24/11/16.
 */

public class WebservicePostJsonAsyn extends AsyncTask<String, String, String> {

    private static final String TAG = "HttpClient";
    Context mContext;
    /*
     * Listener to catch and parse response
     */
    WebserviceResponseListener mListener;
    /*
     * URl of Web service
     */
    ProgressDialog mProgressDialog;
    String url;
    JSONObject mJsonObject;
    boolean isProgressDialog;
    String via;
    protected ViewGroup mContentContainer;
    protected ViewGroup mFrameHeader;
    protected ViewGroup progressContent;
    protected ViewGroup contentLayout;
    protected int url_id;

    String accessToken = "dashboard";

    public WebservicePostJsonAsyn(Context mContext, WebserviceResponseListener mListenerRoot, String url, int url_id, String via, JSONObject mJsonObject, ViewGroup progressContent, ViewGroup mContentContainer, ViewGroup mFrameHeader) {
        this.mContext = mContext;
        this.mJsonObject = mJsonObject;
        this.mListener = mListenerRoot;
        this.url = url;
        this.url_id = url_id;
        this.via = via;
        this.progressContent = progressContent;
        this.mContentContainer = mContentContainer;
        this.mFrameHeader = mFrameHeader;
    }


    @Override
    protected void onPreExecute() {

        super.onPreExecute();


        LogUtil.d(true, "#CALLED", "1");
        if (progressContent != null) {
            mFrameHeader.setVisibility(View.GONE);
            mContentContainer.setVisibility(View.GONE);
            progressContent.setVisibility(View.VISIBLE);

        }


    }

    @Override
    protected void onPostExecute(String response) {

        super.onPostExecute(response);

        LogUtil.d(true, "###RES###", response == null ? "" : response);

        if (response != null) {
            if (response == "") {
                mListener.onError();
                // return;
            }

        }
        if (response != null) {

            mListener.responseWithId(response, via, url_id);
            mListener.response(response, via);


        } else {

               /* Toast.makeText(mContext, R.string.something_went_wrong,
                        Toast.LENGTH_SHORT).show();*/
            //  mListener.slowInternetConnction();


        }

        if (progressContent != null) {
            progressContent.setVisibility(View.GONE);
            mFrameHeader.setVisibility(View.VISIBLE);
            mContentContainer.setVisibility(View.VISIBLE);

        }


    }


    /***
     * method to post json to server
     *
     * @param URL
     *            -URL of web service
     * @param jsonObjSend
     *            -JSON Object
     * @return - response from web service
     */
    public String SendHttpPost(String URL, JSONObject jsonObjSend) {

        LogUtil.d(true, "#CALLED", "2");
        String resultString = "";
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPostRequest = new HttpPost(URL);

            if (jsonObjSend != null) {
                StringEntity se;
                se = new StringEntity(jsonObjSend.toString());

                httpPostRequest.setEntity(se);
            }

            //sudatt

            //HttpUrlConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setConnectTimeout(7000); //set the timeout in milliseconds

            //sudatt
            httpPostRequest.setHeader("Accept", "application/json");
            httpPostRequest.setHeader("Content-type", "application/json");
            httpPostRequest.setHeader("Accept-Encoding", "utf-8");
            httpPostRequest.setHeader("Authorization", "bearer " + accessToken);

            //httpPostRequest.setHeader("Authorization", "Bearer "+ PrefUtils.getString(mContext, Constants.API_ACCESS_TOKEN,Constants.BEARER_CONSTANT));


            long t = System.currentTimeMillis();
            HttpResponse response = (HttpResponse) httpclient
                    .execute(httpPostRequest);
            Log.i(TAG,
                    "##HTTPResponse received in ["
                            + (System.currentTimeMillis() - t) + "ms]");


            // Get hold of the response entity (-> the data):
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Read the content stream
                InputStream instream = entity.getContent();
                Header contentEncoding = response
                        .getFirstHeader("Content-Encoding");
                if (contentEncoding != null
                        && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    instream = new GZIPInputStream(instream);
                }

                // convert content stream to a String
                resultString = convertStreamToString(instream);
                instream.close();
                System.out.println("Above the response string httpPost" + resultString);
                return resultString;
            }

        } catch (UnknownHostException uhe) {
            //mListener.slowInternetConnction();
            return null;
        } catch (IOException e) {
            // More about HTTP exception handling in another tutorial.
            // For now we just print the stack trace.
            e.printStackTrace();
            if (isProgressDialog && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mListener.onError();

        }

        return resultString;
    }


    public String DeleteHttpRequest(String url, String requestBody) {

        URL urls = null;
        String resultString = null;
        try {
            urls = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        HttpURLConnection httpURLConnection = null;


        try {
            httpURLConnection = (HttpURLConnection) urls.openConnection();
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("Accept-Encoding", "utf-8");
            httpURLConnection.setRequestProperty("Authorization", "bearer " + accessToken);
            //httpURLConnection.setRequestProperty("Authorization", "Bearer "+Constants.BEARER_CONSTANT);
            //  httpURLConnection.setRequestProperty("Authorization", "Bearer "+ PrefUtils.getString(mContext, Constants.API_ACCESS_TOKEN,Constants.BEARER_CONSTANT));//httppost.setHeader("Authorization", "Bearer "+accessToken);


            //httpURLConnection.addRequestProperty("inventory_items","FEXP15400-L-30032");//for testing

            // int responseCode = httpURLConnection.getResponseCode();

            if (requestBody != null && requestBody != "") {
                // httpURLConnection.setDoInput(true);
                // httpURLConnection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
                out.writeBytes(requestBody);
                out.flush();
                out.close();

            }


            httpURLConnection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while ((temp = in.readLine()) != null) {
                sb.append(temp).append(" ");
            }
            resultString = sb.toString();

            in.close();

            return resultString;

        } catch (UnknownHostException uhe) {

            // mListener.slowInternetConnction();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            //logger.error(e.getMessage());
        }



        /*
        *    httpPostRequest.setHeader("Content-type", "application/json");
         httpPostRequest.setHeader("Accept-Encoding", "utf-8");
         httpPostRequest.setHeader("Authorization", "Bearer "+"123456");
        * */


       /* httpURLConnection.addRequestProperty("inventory_items","FEXP15400-L-30032");

        httpURLConnection.setUseCaches(false);*/


        return resultString;

    }


    public String UpdateHttpRequest(String url, String requestBody)

    {

        URL urls = null;
        String resultString = null;
        try {
            urls = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        HttpURLConnection httpURLConnection = null;


        try {
            httpURLConnection = (HttpURLConnection) urls.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("Accept-Encoding", "utf-8");
            httpURLConnection.setRequestProperty("Authorization", "bearer " + accessToken);
            //httpURLConnection.setRequestProperty("Authorization", "Bearer "+Constants.BEARER_CONSTANT);
            //httpURLConnection.setRequestProperty("Authorization", "Bearer "+ PrefUtils.getString(mContext, Constants.API_ACCESS_TOKEN,Constants.BEARER_CONSTANT));//httppost.setHeader("Authorization", "Bearer "+accessToken);


            //httpURLConnection.addRequestProperty("inventory_items","FEXP15400-L-30032");//for testing


            if (requestBody != null && requestBody != "") {
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                //  httpURLConnection.getResponseCode();
                DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
                out.writeBytes(requestBody);
                out.flush();
                out.close();

            }


            httpURLConnection.connect();
            //  httpURLConnection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while ((temp = in.readLine()) != null) {
                sb.append(temp).append(" ");
            }
            resultString = sb.toString();

            in.close();

            return resultString;

        } catch (UnknownHostException uhe) {

            //mListener.slowInternetConnction();
            return null;
        } catch (FileNotFoundException fne) {
            mListener.onError();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //logger.error(e.getMessage());
        }


            /*
            *    httpPostRequest.setHeader("Content-type", "application/json");
             httpPostRequest.setHeader("Accept-Encoding", "utf-8");
             httpPostRequest.setHeader("Authorization", "Bearer "+"123456");
            * */


           /* httpURLConnection.addRequestProperty("inventory_items","FEXP15400-L-30032");

            httpURLConnection.setUseCaches(false);*/
        return resultString;

    }


    public String HttpPatchRequest(String url, String requestBody)

    {

        URL urls = null;
        String resultString = null;
        try {
            urls = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        HttpURLConnection httpURLConnection = null;


        try {
            httpURLConnection = (HttpURLConnection) urls.openConnection();
            httpURLConnection.setRequestMethod("PATCH");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("Accept-Encoding", "utf-8");
            httpURLConnection.setRequestProperty("Authorization", "bearer " + accessToken);
            // httpURLConnection.setRequestProperty("Authorization", "Bearer "+ PrefUtils.getString(mContext, Constants.API_ACCESS_TOKEN,Constants.BEARER_CONSTANT));//httppost.setHeader("Authorization", "Bearer "+accessToken);


            //httpURLConnection.addRequestProperty("inventory_items","FEXP15400-L-30032");//for testing


            if (requestBody != null && requestBody != "") {
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
                out.writeBytes(requestBody);
                out.flush();
                out.close();

            }

            httpURLConnection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while ((temp = in.readLine()) != null) {
                sb.append(temp).append(" ");
            }
            resultString = sb.toString();

            in.close();

            return resultString;

        } catch (UnknownHostException uhe) {

            //mListener.slowInternetConnction();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //logger.error(e.getMessage());
        }


            /*
            *    httpPostRequest.setHeader("Content-type", "application/json");
             httpPostRequest.setHeader("Accept-Encoding", "utf-8");
             httpPostRequest.setHeader("Authorization", "Bearer "+"123456");
            * */


           /* httpURLConnection.addRequestProperty("inventory_items","FEXP15400-L-30032");

            httpURLConnection.setUseCaches(false);*/
        return resultString;

    }


    public String SendHttpGettstring(String URL) {
        String resultString = "";
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGetRequest = new HttpGet(URL);


            httpGetRequest.setHeader("Accept", "application/json");
            httpGetRequest.setHeader("Content-Type", "application/json");
            httpGetRequest.setHeader("Accept-Encoding", "utf-8"); // only
            // httpGetRequest.setHeader("Authorization", "bearer "+accessToken);

            //   httpGetRequest.setHeader("Authorization", "Bearer "+ PrefUtils.getString(mContext, Constants.API_ACCESS_TOKEN,Constants.BEARER_CONSTANT));

            long t = System.currentTimeMillis();
            HttpResponse response = (HttpResponse) httpclient.execute(httpGetRequest);
            Log.i(TAG,
                    "HTTPResponse received in ["
                            + (System.currentTimeMillis() - t) + "ms]");

            // Get hold of the response entity (-> the data):
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Read the content stream
                InputStream instream = entity.getContent();
                Header contentEncoding = response
                        .getFirstHeader("Content-Encoding");
                if (contentEncoding != null
                        && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    instream = new GZIPInputStream(instream);
                }

                // convert content stream to a String
                resultString = convertStreamToString(instream);
                instream.close();

                return resultString;
            }

        } catch (UnknownHostException uhe) {

            // mListener.slowInternetConnction();
            return null;
        } catch (Exception e) {
            // More about HTTP exception handling in another tutorial.
            // For now we just print the stack trace.
            System.out.println("COnnection timed out" + e.getMessage());
            e.printStackTrace();
            mListener.onError();
        }
        return resultString;
    }

    private static String convertStreamToString(InputStream is) {
        /*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 *
		 * (c) public domain:
		 * http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/
		 * 11/a-simple-restful-client-at-android/
		 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected String doInBackground(String... params) {


        long startTime = System.currentTimeMillis();
        LogUtil.d(true, "###URL###", url);
        String response;

        if (via.equalsIgnoreCase("post")) {

            response = SendHttpPost(url, mJsonObject);

        } else if (via.equalsIgnoreCase("") || via.equalsIgnoreCase("get")) {
            response = SendHttpGettstring(url);
        } else if (via.equalsIgnoreCase("patch")) {
            LogUtil.d(true, "##METHOD", "PATCH");
            response = HttpPatchRequest(url, mJsonObject == null ? "" : mJsonObject.toString());
        } else if (via.equalsIgnoreCase("delete")) {
            response = DeleteHttpRequest(url, mJsonObject == null ? "" : mJsonObject.toString());
        } else {
            response = UpdateHttpRequest(url, mJsonObject == null ? "" : mJsonObject.toString());
        }

        long endtime = System.currentTimeMillis();

        long timediff = endtime - startTime;


        // LogUtil.d("TIMEDIFF_DOINBG",timediff+"#URL#"+url);
        return response;
    }

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    /***
     *
     * @param url
     * @return
     */
    public JSONObject getJSONFromUrl(String url) {
        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            // Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            // Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }


}
