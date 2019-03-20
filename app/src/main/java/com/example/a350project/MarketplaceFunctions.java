package com.example.a350project;

import java.util.LinkedList;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MarketplaceFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();

    public MarketplaceFunctions() { }

    public static void onSearchButtonClick(View view, TextView searchResultsView, String searchString) {
        //Toast.makeText(view.getContext(), searchString, Toast.LENGTH_LONG).show();
        SessionFunctions.loadSessions();
        allSessions = SessionFunctions.getAllSessions();
        LinkedList<SessionObject> relevantSessions = new LinkedList<SessionObject>();
        if (allSessions != null) {
            for (SessionObject currentSession : allSessions) {
                if (currentSession.getSubject().equals(searchString)) {
                    relevantSessions.add(currentSession);
                    Toast.makeText(view.getContext(), "FOUND " + searchString, Toast.LENGTH_LONG).show();

                }
            }
        }
        // Now Display the Relevant Sessions

    }









}
