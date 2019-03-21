package com.example.a350project;

import java.util.LinkedList;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MarketplaceFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();
    private static LinkedList<SessionObject> foundSessions = new LinkedList<SessionObject>();

    public MarketplaceFunctions() { }

    public static void onSearchButtonClick(View view, TextView searchResultsView, String searchString) {
        //Toast.makeText(view.getContext(), searchString, Toast.LENGTH_LONG).show();
        SessionFunctions.loadSessions();
        foundSessions.clear();
        allSessions = SessionFunctions.getAllSessions();
        if (allSessions != null) {
            for (SessionObject currentSession : allSessions) {
                if (currentSession.getSubject().equals(searchString)) {
                    foundSessions.add(currentSession);
                    Toast.makeText(view.getContext(), "FOUND " + searchString, Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    public static LinkedList<SessionObject> getFoundSessions () {
        return foundSessions;
    }


}
