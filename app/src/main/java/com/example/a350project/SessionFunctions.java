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
        SessionObject newSession = new SessionObject(tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status);
        allSessions.add(newSession);
        Log.e("ADD SESSIONS ", "Size: " + allSessions.size());
        DataManagement.writeSession(MainActivity.context , newSession);
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
