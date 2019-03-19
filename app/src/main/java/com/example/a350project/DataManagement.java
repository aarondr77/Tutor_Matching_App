package com.example.a350project;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class DataManagement {

    String sessionDatabase;

    public DataManagement(String sessionDatabase) {

    }

    public static List<SessionObject> searchTutoringSessions (String subject) {
        String FILENAME = "Sessions.txt";
        //String string = newComplaint.getContent() + "," + newComplaint.getSubmitter() + "," + newComplaint.getStatus() + "," + newComplaint.getTarget();

        BufferedReader fos = null;

        List<SessionObject> tempList = new LinkedList<SessionObject>();
        List<SessionObject> returnVal = new LinkedList<SessionObject>();

        try {
            fos = new BufferedReader(new InputStreamReader(MainActivity.context.openFileInput(FILENAME)));

            while (fos.ready()) {
                String curLine = fos.readLine();
                Log.d("READ VALUE", curLine.split(":")[0]);
                tempList.add(new SessionObject(curLine.split(":")[0], curLine.split(":")[1], curLine.split(":")[2], curLine.split(":")[3],
                        curLine.split(":")[4], curLine.split(":")[5], curLine.split(":")[6]));
            }
            fos.close();

            for (SessionObject currentSession : tempList) {
                if (currentSession.getSubject().equals(subject)) {
                    returnVal.add(currentSession);
                }
            }

            return returnVal;
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }

        return null;
    }

    public static void writeSession(Context context, SessionObject newSession) {
        String FILENAME = "Session.txt";
        String sessionString = newSession.getTutor() + ":" + newSession.getStudent() + ":" + newSession.getSubject() + ":" +
                newSession.getDate() + ":" + newSession.getDuration() + ":" + newSession.getPrice() + ":" + newSession.getStatus();

        BufferedWriter fos = null;
        try {
            fos = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND)));
            fos.write(sessionString);
            fos.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }
    }



    public static void writeComplaint(Context context, ComplaintsObject newComplaint) {

        /*private String complaintsFile = context.getFilesDir().getPath().toString() + "/ComplaintsFile.txt";

        private File complaints = new File(complaintsFile);

        if (!complaints.exists()) {
            try {complaints.createNewFile();}
            catch (IOException e) {
                Log.d("Create File", e.toString());
            }

        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(complaintsFile));
            writer.write(newComplaint.getContent() + "," + newComplaint.getSubmitter() + "," + newComplaint.getStatus() + "," + newComplaint.getTarget());
            writer.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        } */


        /*String complaintsFile = context.getFilesDir().getPath().toString() + "/ComplaintsFile.txt";
        File complaints = new File(complaintsFile);
        complaints.delete();*/


        String FILENAME = "ComplaintsFile.txt";
        String string = newComplaint.getContent() + ":" + newComplaint.getSubmitter() + ":" + newComplaint.getStatus() + ":" + newComplaint.getTarget() + "\n";

        BufferedWriter fos = null;
        try {
            fos = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND)));
            fos.write(string);
            fos.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }

    }

    public static List<ComplaintsObject> loadComplaints() {
        String FILENAME = "ComplaintsFile.txt";
        //String string = newComplaint.getContent() + "," + newComplaint.getSubmitter() + "," + newComplaint.getStatus() + "," + newComplaint.getTarget();

        BufferedReader fos = null;

        List<ComplaintsObject> tempList = new LinkedList<ComplaintsObject>();
        List<ComplaintsObject> returnVal = new LinkedList<ComplaintsObject>();

        try {
            fos = new BufferedReader(new InputStreamReader(MainActivity.context.openFileInput(FILENAME)));

            while (fos.ready()) {
                String curLine = fos.readLine();
                Log.d("READ VALUE", curLine.split(":")[0]);
                tempList.add(new ComplaintsObject(curLine.split(":")[0], curLine.split(":")[1], curLine.split(":")[2], curLine.split(":")[3]));
            }
            fos.close();

            for (ComplaintsObject curComplaint : tempList) {
                if (curComplaint.getSubmitter().equals(MainActivity.currentUserEmail)) {
                    returnVal.add(curComplaint);
                }
            }

            return returnVal;
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }

        return null;
    }
}
