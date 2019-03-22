package com.example.a350project;

import android.util.Log;
import java.util.LinkedList;


public class SessionFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();

    public SessionFunctions() { }

    public static void loadSessions() {
        // add Fake Starting Data to Database
        addSession("Petra","Terry", "petra@gmail.com", "terry@gmail.com","MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Aaron","Terry", "aaron@gmail.com", "terry@gmail.com", "MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Chris","Terry", "chris@gmail.com", "terry@gmail.com", "MATH240","3PM 03/25/19", "60", "15", "pending");
        addSession("Petra","unclaimed", "petra@gmail.com", "unclaimed", "MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Aaron","unclaimed", "aaron@gmail.com", "unclaimed","MATH114","3PM 03/25/19", "60", "15", "pending");
        addSession("Chris","unclaimed", "chris@gmail.com", "unclaimed", "MATH240","3PM 03/25/19", "60", "15", "pending");

        Log.e("LOAD SESSIONS", "CALLED ADD SESSION: SIZE = " + allSessions.size());
        allSessions.clear();
        allSessions.addAll(DataManagement.loadSessions());
        Log.e("LOAD SESSIONS ", "Size: " + allSessions.size());
    }


    public static void addSession(String tutor, String student, String tutorEmail, String studentEmail, String subject, String date, String duration, String price, String status) {
        String sessionID = Double.toString(Math.random());
        Log.e("RANDOM SESSION ID: ", sessionID);
        SessionObject newSession = new SessionObject(sessionID, tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status);
        allSessions.add(newSession);
        Log.e("SESSION ADDED ", newSession.getSessionID());
        Log.e("ADD SESSIONS ", "Size: " + allSessions.size());
        DataManagement.writeSession(MainActivity.context , allSessions);
    }

    public static void addSession(String sessionID, String tutor, String student, String tutorEmail, String studentEmail, String subject, String date, String duration, String price, String status) {
        SessionObject newSession = new SessionObject(sessionID, tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status);
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
                addSession(targetSessionID, currentSession.getTutor(), MainActivity.currentUserEmail, currentSession.getTutorEmail(), currentSession.getStudentEmail(), currentSession.getSubject(), currentSession.getDate(), currentSession.getDuration(), currentSession.getPrice(), "accepted");
                break;
            }
        }
    }

    public static LinkedList<SessionObject> getAllSessions () {
        return allSessions;
    }

    public static LinkedList<SessionObject> getMySessions () {
        LinkedList<SessionObject> allMySessions = new LinkedList<SessionObject>();
        for(SessionObject session : allSessions) {
            if(session.getTutorEmail().equals(MainActivity.currentUserEmail) ||
                    session.getStudentEmail().equals(MainActivity.currentUserEmail)) {
                allMySessions.add(session);
            }
        }
        return allMySessions;
    }
}
