package com.example.a350project;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONException;


public class DataManagement {

    private static String sessionDatabase = "Sessions10.txt";
    private static String userDatabase = "UserDatabase20.txt";
    private static String complaintsDatabase = "ComplaintsFile.txt";

    public DataManagement() { }

    public static List<SessionObject> loadSessions () {
        String FILENAME = sessionDatabase;
        //String string = newComplaint.getContent() + "," + newComplaint.getSubmitter() + "," + newComplaint.getStatus() + "," + newComplaint.getTarget();

        BufferedReader fos = null;

        List<SessionObject> returnVal = new LinkedList<SessionObject>();

        try {
            fos = new BufferedReader(new InputStreamReader(MainActivity.context.openFileInput(FILENAME)));

            while (fos.ready()) {
                String curLine = fos.readLine();
                Log.e("READ VALUE", curLine.split(":")[0]);
                Log.e("ALL VALUE", curLine);
                returnVal.add(new SessionObject(curLine.split(":")[0], curLine.split(":")[1], curLine.split(":")[2], curLine.split(":")[3],
                        curLine.split(":")[4], curLine.split(":")[5], curLine.split(":")[6], curLine.split(":")[7], curLine.split(":")[8],  curLine.split(":")[9]));
            }
            fos.close();


            return returnVal;
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }

        return null;
    }

    public static void writeSession(Context context, LinkedList<SessionObject> writeSession) {
        String FILENAME = sessionDatabase;
        Log.e("WRITE SESSION SIZE: ", Integer.toString(writeSession.size()));
        try {
            BufferedWriter fos = null;
            fos = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE)));

            for (SessionObject currentSession : writeSession) {
                String sessionString = currentSession.getSessionID() + ":" + currentSession.getTutor() + ":" + currentSession.getStudent() + ":" + currentSession.getTutorEmail() + ":" + currentSession.getStudentEmail() + ":" +  currentSession.getSubject() + ":" +
                        currentSession.getDate() + ":" + currentSession.getDuration() + ":" + currentSession.getPrice() + ":" + currentSession.getStatus() + "\n";
                try {
                    Log.i("ADDING TO DATABASE", sessionString);
                    fos.write(sessionString);
                    Log.e("PRINT", "wrote session to database");
                } catch (IOException e) {
                    Log.d("PRINT", e.toString());
                }
            }
            fos.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());

        }

    }



    public static void writeComplaint(Context context, ComplaintsObject newComplaint) {

        String FILENAME = complaintsDatabase;
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

    public static void registerNewUser(String firstName, String lastName, String email, String password, String userType, String price, String days, String times, Context context) {
        String FILENAME = userDatabase;

        List<String> allUsers = loadUsers();
        String JSONobj = "{ firstName:" + firstName + ",lastName:" + lastName + ",email:" + email + ",password:" + password +
                ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                + times + ",numSessions:" + "0" + ",totalCost:" + "0" +  ",avgCost:"  + "0" + ",tutorRating:" +  "0" + ",studentRating:" + "0" + ",balance:" + "100}";

        allUsers.add(JSONobj);

        BufferedWriter  w = null;
        try {
            w = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE)));
            for (String user : allUsers) {
                w.write(user + "\n");
            }
            w.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }

    }
/*
    public static void registerNewUser(String name, String email, String password, String userType, String price, String days, String times, Context context) {
        String FILENAME = "new_users1.txt";


        String JSONobj = "{ name:" + name + ",email:" + email + ",password:" + password +
                ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                + times + ",tutorRating:" +  "0" + ",avgCost:" + "0"+ ",studentRating:" + "0" + ",totalSessions:" + "0" + ",totalCost:" + "0" + ",balance:" + "100 }" + "\n";;
        Log.d("day", days);
        Log.d("time", times);

        BufferedWriter  w = null;
        try {
            w = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND)));
            w.write(JSONobj);
            w.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());
        }
    }
    */

    // find user by email, used for finding current user
    public static JSONObject findUser(String email) {
        List<String> allUsers = loadUsers();
        for (String e: allUsers) Log.d("u", e);

        JSONObject result = null;
        Log.d("input_email", email);
        try{
            // loop through users and find matching email
            for(String user : allUsers) {
                Log.d("input_email", email);

                JSONObject userJson = new JSONObject(user);
                Log.e("findUser", "currentEmail " + userJson.getString("email"));
                if (userJson.getString("email").equals(email)) {
                    result = userJson;
                } else {

                }
            }
        } catch(JSONException e) {
            Log.e("json error", e.getMessage());
        }
        // return matching json object
        return result;
    }

    public static void updateBalance (String emailAddress, double newBalance, Context context) {
        Log.e("Updating Balance", emailAddress);
        Log.e("Updating Balance", Double.toString(newBalance));

        String FILENAME = userDatabase;

        // get up to date list of allUsers as String
        List<String> allUsers = loadUsers();
        List<String> updatedUsers =  new LinkedList<String>();
        for (String currentUser : allUsers) {
            try {
                JSONObject userJson = new JSONObject(currentUser);
                if (userJson.get("email").equals(emailAddress)) {
                    Log.e("Updating Balance", "FOUND " + emailAddress);

                    String firstName = userJson.getString("firstName");
                    String lastName = userJson.getString("lastName");
                    String password = userJson.getString("password");
                    String userType = userJson.getString("userType");
                    String price = userJson.getString("price");
                    String days = userJson.getString("days");
                    String times = userJson.getString("times");
                    String tutorRating = userJson.getString("tutorRating");
                    String studentRating = userJson.getString("studentRating");
                    String balance = Double.toString(newBalance);

                    String JSONobj = "{ firstName:" + firstName + ",lastName:" + lastName + ",email:" + emailAddress + ",password:" + password +
                            ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                            + times + ",tutorRating:" + tutorRating + ",studentRating:" + studentRating + ",balance:" + balance + "}";

                    updatedUsers.add(JSONobj);
                } else {
                    updatedUsers.add(currentUser);
                }
            } catch (JSONException e) {
                Log.e("JSONException", e.getStackTrace().toString());
            }
        }
        // overwrite old file and rewrite all new users
        BufferedWriter  w = null;
        Log.e("Updating Balance", "updatedUsers Size " + updatedUsers.size());
        try {
            w = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE)));
            for (String user : updatedUsers) {
                Log.e("Adding User", user);
                w.write(user + "\n");
            }
            w.close();
        } catch (IOException e) {
            Log.e("IOException", e.getStackTrace().toString());
        }

        // TEST! REMOVE BEFORE SUBMITTING
        try {
            Log.e("Resulting Balance " , findUser(emailAddress).getString("balance"));
        } catch (JSONException e) {

        }

    }

    public static List<String> loadUsers() {
        String FILENAME = userDatabase;

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
        String FILENAME = complaintsDatabase;
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

    public static boolean userExists(String email) {
        List<String> allUsers = DataManagement.loadUsers();
        for (String u: allUsers) {
            String[] info = u.split(",");
            String e = info[2].split(":")[1];
            if (email.equals(e)) return true;
        }
        return false;
    }
}
