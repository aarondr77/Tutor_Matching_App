package com.example.a350project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class AccessWebTaskPost extends AsyncTask<String, Void, Void> {

    /*
    This method is called in background when this object's "execute" method is invoked.
    The arguments passed to "execute" are passed to this method.
    */
//    JSONObject postParams = null;
    Map<String, String> postData;
    public AccessWebTaskPost(Map<String, String> postData) {
        if (postData != null) {
            this.postData = postData;
//            this.postParams = new JSONObject(postData);
//            Log.e("fuckPostParams>>>>>>>", postParams.toString());

        }
    }

    @Override
    protected Void doInBackground(String... args) {

        // This is the JSON body of the post
        String response = "";

        //get the first URL from the array

        try {
            URL url = new URL(args[0]);
            // create connection and sendHttpURLConnectionrequest
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
//            conn.connect();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Log.e("paramsnotnull>>>>>>>", postData.toString());
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(getPostDataString((HashMap<String, String>)postData));
            writer.flush();


            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            Log.e("fuckCode>>>>>>>", Integer.toString(responseCode));

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;

                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
            Log.e("response>>>>>>>", response);


        } catch (Exception e) {
//            Log.e("Exception while posting", e.getMessage());

        }
        return null;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    /*
    This method is called in foreground after doInBackground finishes.
    It can access and update Views in user interface.
    */
    protected void onPostExecute(String msg) {
        // not implemented but you can use this if you’d like}}
    }
}