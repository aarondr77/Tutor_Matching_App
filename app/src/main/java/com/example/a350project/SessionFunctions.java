package com.example.a350project;

import android.util.Log;
import java.util.LinkedList;


public class SessionFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();

    public SessionFunctions() { }

    public static void loadSessions() {
        allSessions.clear();
        allSessions.addAll(DataManagement.loadSessions());
        Log.e("LOAD SESSIONS", "CALLED ADD SESSION: SIZE = " + allSessions.size());
        Log.e("LOAD SESSIONS ", "Size: " + allSessions);
    }


    public static void addSession(String tutor, String student, String tutorEmail, String studentEmail, String subject, String date, String duration, String price, String status) {
        String sessionID = Double.toString(Math.random());
        allSessions.clear();
        allSessions.addAll(DataManagement.loadSessions());
        Log.e("RANDOM SESSION ID: ", sessionID);
        Log.e("session id>>>", subject + " sessionID:" + sessionID);
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
    public static void claimSession(String targetSessionID, String student) {
        for (SessionObject currentSession : allSessions) {
            if (currentSession.getSessionID().equals(targetSessionID)) {
                Log.i("FOUND SESSION TO CLAIM", "FOUND");
                allSessions.remove(currentSession);
                addSession(targetSessionID, currentSession.getTutor(), student, currentSession.getTutorEmail(), MainActivity.currentUserEmail, currentSession.getSubject(), currentSession.getDate(), currentSession.getDuration(), currentSession.getPrice(), "accepted");
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
