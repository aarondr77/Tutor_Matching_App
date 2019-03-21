package com.example.a350project;

import android.os.Environment;
import android.util.Log;
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
        // add Fake Starting Data to Database
        addSession("Petra","Terry","MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Aaron","Terry","MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Chris","Terry","MATH240","3PM 03/25/19", "60", "15", "pending");
        Log.e("LOAD SESSIONS", "CALLED ADD SESSION: SIZE = " + allSessions.size());
        allSessions.clear();
        allSessions.addAll(DataManagement.loadSessions());
        Log.e("LOAD SESSIONS ", "Size: " + allSessions.size());
    }

    public static void addSession(String tutor, String student, String subject, String date, String duration, String price, String status) {
        SessionObject newSession = new SessionObject(tutor, student, subject, date, duration, price, status);
        allSessions.add(newSession);
        Log.e("ADD SESSIONS ", "Size: " + allSessions.size());
        DataManagement.writeSession(MainActivity.context , newSession);
    }

    public static LinkedList<SessionObject> getAllSessions () {
        return allSessions;
    }
}
