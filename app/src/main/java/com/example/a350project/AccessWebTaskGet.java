package com.example.a350project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AccessWebTaskGet extends AsyncTask<URL, String, String> {

    /*
    This method is called in background when this object's "execute" method is invoked.
    The arguments passed to "execute" are passed to this method.
    */

    protected String doInBackground(URL... urls) {

        try {
        //get the first URL from the array
            URL url = urls[0];
            Log.d("Called URL>>>>>>>>>>>>>", url.toString());
            // create connection and sendHttpURLConnectionrequest
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // read first line of data that is returned
//            Scanner in = new Scanner(url.openStream());
//            String msg = in.nextLine();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String msg = "";
            String line;
            while((line = reader.readLine()) != null){
                msg += line;
            }

            // use Android JSON library to parseJSON
            if(!msg.equals("")) {
                JSONObject jo = new JSONObject(msg);
                return jo.toString();
            } else {
                return msg;
            }

            // assumes that JSON object contains a "name" field
            // this will be passed to onPostExecute method
        } catch (Exception e) {
            return e.toString();
        }
    }

    /*
    This method is called in foreground after doInBackground finishes.
    It can access and update Views in user interface.
    */
    protected void onPostExecute(String msg) {
        // not implemented but you can use this if you’d like}}
    }
}