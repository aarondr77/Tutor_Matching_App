package com.example.a350project;

import java.util.LinkedList;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;


public class MarketplaceFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();
    private static LinkedList<SessionObject> foundSessions = new LinkedList<SessionObject>();

    public MarketplaceFunctions() { }

    public static void onSearchButtonClick(View view, String searchString) {
        SessionFunctions.loadSessions();
        foundSessions.clear();
        allSessions = SessionFunctions.getAllSessions();
        if (allSessions != null) {
            for (SessionObject currentSession : allSessions) {
                if (currentSession.getSubject().equals(searchString) && currentSession.getStudent().equals("unclaimed")) {
                    foundSessions.add(currentSession);
                    Toast.makeText(view.getContext(), "FOUND " + searchString, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public static double getBalance (String emailAddress) {
        JSONObject user = DataManagement.findUser(emailAddress);
        double balance = -1;
        try {
            balance = user.getDouble("balance");
        } catch (JSONException e ) {
            Log.e("JSONException", e.getStackTrace().toString());
        }
        return balance;
    }

    public static void updateBalance (String emailAddress, double balanceDelta) {
        JSONObject user = DataManagement.findUser(emailAddress);
        double balance;
        try {
            balance = user.getDouble("balance");
            balance += balanceDelta;
            
        } catch (JSONException e) {

        }
    }

    public static LinkedList<SessionObject> getFoundSessions () {
        return foundSessions;
    }


}
