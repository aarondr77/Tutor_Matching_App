package com.example.a350project;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SessionFunctions {

    private static LinkedList<SessionObject> allSessions = new LinkedList<SessionObject>();

    public SessionFunctions() { }

    public static List<SessionObject> loadSessions() {
        allSessions.clear();

        List<SessionObject> loadedSessions = DataManagement.loadSessions();
        if (loadedSessions != null) {
            Log.d("LOADING SESSIONS", "yay!");
            allSessions.addAll(loadedSessions);
            Log.d("LOADING SESSIONS SIZE", "size " + allSessions.size());
        }
        return allSessions;
    }


    public static void addSession(String tutor, String student, String tutorEmail, String studentEmail, String subject, String date, String duration, String price, String status) {
//        String sessionID = Double.toString(Math.random());
//        allSessions.clear();
//        List<SessionObject> loadedSessions = DataManagement.loadSessions();
//        if (loadedSessions != null) {
//            allSessions.addAll(loadedSessions);
//        }
//        SessionObject newSession = new SessionObject(sessionID, tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status);
//        allSessions.add(newSession);
//        DataManagement.writeSession(MainActivity.context , allSessions);


        // make POST request
        try {
            // put params in a map format
            Map<String, String> postParams = new HashMap<>();
            postParams.put("tutor", tutor);
            postParams.put("student", student);
            postParams.put("tutorEmail", tutorEmail);
            postParams.put("studentEmail", studentEmail);
            postParams.put("subject", subject);
            postParams.put("date", date);
            postParams.put("duration", duration);
            postParams.put("price", price);
            postParams.put("status", status);
            AccessWebTaskPost task = new AccessWebTaskPost(postParams);
            task.execute("http://10.0.2.2:3000/addSession");
        } catch(Exception e) {
            Log.d("error post add session", e.getMessage());
        }
    }
//
//    public static void addSession(String sessionID, String tutor, String student, String tutorEmail, String studentEmail, String subject, String date, String duration, String price, String status) {
//        // remove the session to update
//        for (SessionObject currentSession : allSessions) {
//            if (currentSession.getSessionID().equals(sessionID)) {
//                Log.e("addSession", "removing" + sessionID);
//                allSessions.remove(currentSession);
//            }
//        }
//        SessionObject newSession = new SessionObject(sessionID, tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status);
//        allSessions.add(newSession);
//        DataManagement.writeSession(MainActivity.context , allSessions);
//
//    }
    public static void claimSession(String targetSessionID, String student) {
        String studentEmail = MainActivity.currentUserEmail;
        DataManagement.claimSession(studentEmail, student, targetSessionID);
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
