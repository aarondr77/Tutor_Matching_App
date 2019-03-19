package com.example.a350project;

import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class SessionFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();

    public SessionFunctions() { }

    public static void loadSessions() {
        DataManagement.writeSession(MainActivity.context , new SessionObject("Petra","Terry","MATH114","3PM 03/25/19", "60", "15", "pending"));
        allSessions.clear();
        allSessions.addAll(DataManagement.loadSessions());
    }

    public static void addComplaint(String tutor, String student, String subject, String date, String duration, String price, String status) {
        SessionObject newSession = new SessionObject(tutor, student, subject, date, duration, price, status);
        allSessions.add(newSession);
        DataManagement.writeSession(MainActivity.context , newSession);
    }
/*
    public static void onSearchButtonClick(View view, TextView searchResultsView, String searchString) {
        Toast.makeText(view.getContext(), searchString, Toast.LENGTH_LONG).show();
        Toast.makeText(view.getContext(), databasePath, Toast.LENGTH_LONG).show();
    }
    */








}
