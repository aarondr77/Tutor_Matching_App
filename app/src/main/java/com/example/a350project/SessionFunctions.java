package com.example.a350project;

import android.util.Log;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class SessionFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();

    public SessionFunctions() { }

    public static void loadSessions() {
        // add Fake Starting Data to Database
        addSession("Petra","Terry","MATH114","3PM 03/25/19", "60", "15", "accpeted");
        addSession("Aaron","Terry","MATH114","3PM 03/25/19", "60", "15", "accepted");
        addSession("Chris","Terry","MATH240","3PM 03/25/19", "60", "15", "accepted");
        addSession("Petra","unclaimed","MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Aaron","unclaimed","MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Chris","unclaimed","MATH240","3PM 03/25/19", "60", "15", "pending");
        addSession("Chris","unclaimed","MATH114","3PM 03/25/19", "60", "200", "pending");

        Log.e("LOAD SESSIONS", "CALLED ADD SESSION: SIZE = " + allSessions.size());
        allSessions.clear();
        allSessions.addAll(DataManagement.loadSessions());
        Log.e("LOAD SESSIONS ", "Size: " + allSessions.size());
    }

    public static void addSession(String tutor, String student, String subject, String date, String duration, String price, String status) {
        String sessionID = Double.toString(Math.random());
        Log.e("RANDOM SESSION ID: ", sessionID);
        SessionObject newSession = new SessionObject(sessionID, tutor, student, subject, date, duration, price, status);
        allSessions.add(newSession);
        Log.e("SESSION ADDED ", newSession.getSessionID());
        Log.e("ADD SESSIONS ", "Size: " + allSessions.size());
        DataManagement.writeSession(MainActivity.context , allSessions);
    }

    public static void addSession(String sessionID, String tutor, String student, String subject, String date, String duration, String price, String status) {
        SessionObject newSession = new SessionObject(sessionID, tutor, student, subject, date, duration, price, status);
        allSessions.add(newSession);
        Log.e("SESSION ADDED ", newSession.getSessionID());
        Log.e("ADD SESSIONS ", "Size: " + allSessions.size());
        DataManagement.writeSession(MainActivity.context , allSessions);
    }
    public static void claimSession(String targetSessionID) {
        for (SessionObject currentSession : allSessions) {
            if (currentSession.getSessionID().equals(targetSessionID)) {
                Log.i("FOUND SESSION TO CLAIM", "FOUND");
                allSessions.remove(currentSession);
                // CHANGE CURRENT USER EMAIL TO CURRENT USER NAME
                addSession(targetSessionID, currentSession.getTutor(), MainActivity.currentUserEmail, currentSession.getSubject(), currentSession.getDate(), currentSession.getDuration(), currentSession.getPrice(), "accepted");
                break;
            }
        }
    }

    public static LinkedList<SessionObject> getAllSessions () {
        return allSessions;
    }
}
