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
        Toast.makeText(view.getContext(), searchString, Toast.LENGTH_LONG).show();
    }









}
