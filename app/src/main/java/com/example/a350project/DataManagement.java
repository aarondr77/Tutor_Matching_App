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
import org.json.JSONObject;
import org.json.JSONException;


public class DataManagement {

    String sessionDatabase;

    public DataManagement(String sessionDatabase) {

    }

    public static List<SessionObject> loadSessions () {
        String FILENAME = "Sessions.txt";
        //String string = newComplaint.getContent() + "," + newComplaint.getSubmitter() + "," + newComplaint.getStatus() + "," + newComplaint.getTarget();

        BufferedReader fos = null;

        List<SessionObject> returnVal = new LinkedList<SessionObject>();

        try {
            fos = new BufferedReader(new InputStreamReader(MainActivity.context.openFileInput(FILENAME)));

            while (fos.ready()) {
                String curLine = fos.readLine();
                Log.e("READ VALUE", curLine.split(":")[0]);
                returnVal.add(new SessionObject(curLine.split(":")[0], curLine.split(":")[1], curLine.split(":")[2], curLine.split(":")[3],
                        curLine.split(":")[4], curLine.split(":")[5], curLine.split(":")[6]));
            }
            fos.close();

            return returnVal;
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }

        return null;
    }

    public static void writeSession(Context context, SessionObject newSession) {
        String FILENAME = "Sessions.txt";
        String sessionString = newSession.getTutor() + ":" + newSession.getStudent() + ":" + newSession.getSubject() + ":" +
                newSession.getDate() + ":" + newSession.getDuration() + ":" + newSession.getPrice() + ":" + newSession.getStatus() + "\n";

        BufferedWriter fos = null;
        try {
            fos = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND)));
            fos.write(sessionString);
            fos.close();
            Log.e("PRINT", "wrote session to database");
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

    public static void registerNewUser(String name, String email, String password, String userType, String price, String days, String times, Context context) {
        String FILENAME = "new_users1.txt";


        String JSONobj = "{ name:" + name + ",email:" + email + ",password:" + password +
                ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                + times + ",tutorRating:" +  "0" + ",studentRating:" + "0" + ",balance:" + "100}" + "\n";;
        Log.d("day", JSONobj);

        BufferedWriter  w = null;
        try {
            w = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND)));
            w.write(JSONobj);
            w.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }
    }

    // find user by email, used for finding current user
    public static JSONObject findUser(String email) {
        List<String> allUsers = loadUsers();

        JSONObject result = null;

        try{
            // loop through users and find matching email
            for(String user : allUsers) {
                Log.d("user", user);
                JSONObject userJson = new JSONObject(user);
                //Log.d("email", userJson.getString("email"));
                if (userJson.getString("email").equals(email)) {
                    result = userJson;
                }
            }
        } catch(JSONException e) {
            Log.e("json error", "unexpected JSON error");
        }
        // return matching json object
        return result;
    }

    public static List<String> loadUsers() {
        String FILENAME = "new_users1.txt";

        BufferedReader w;

        List<String> tempList = new LinkedList<>();

        try {
            w = new BufferedReader(new InputStreamReader(LoginActivity.context.openFileInput(FILENAME)));

            while (w.ready()) {
                String curLine = w.readLine();
                tempList.add(curLine);
            }
            w.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }
        return tempList;
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
