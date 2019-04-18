package com.example.a350project;

import android.content.Context;
import android.support.annotation.MainThread;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.net.URL;
import java.util.Map;


public class DataManagement {

    private static String sessionDatabase = "Sessions1001.txt";
    private static String userDatabase = "UserDatabase1001.txt";
    private static String complaintsDatabase = "ComplaintsDatabase1000.txt";


    public DataManagement() { }

    public static List<SessionObject> loadSessions () {
        String FILENAME = sessionDatabase;
        BufferedReader fos = null;

        List<SessionObject> returnVal = new LinkedList<SessionObject>();

        try {
            fos = new BufferedReader(new InputStreamReader(MainActivity.context.openFileInput(FILENAME)));

            while (fos.ready()) {
                String curLine = fos.readLine();
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
        try {
            BufferedWriter fos = null;
            fos = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE)));

            for (SessionObject currentSession : writeSession) {
                String sessionString = currentSession.getSessionID() + ":" + currentSession.getTutor() + ":" + currentSession.getStudent() + ":" + currentSession.getTutorEmail() + ":" + currentSession.getStudentEmail() + ":" +  currentSession.getSubject() + ":" +
                        currentSession.getDate() + ":" + currentSession.getDuration() + ":" + currentSession.getPrice() + ":" + currentSession.getStatus() + "\n";
                try {
                    fos.write(sessionString);
                } catch (IOException e) {
                    Log.d("PRINT", e.toString());
                }
            }
            fos.close();
        } catch (IOException e) {
            Log.d("PRINT", e.toString());

        }

    }


    public static JSONObject addRating(String userEmail, float rating) {
        JSONObject user = findUser(userEmail);
        try{
            user.put("rateTotal", user.getDouble("rateTotal") + rating);
            user.put("rateNum", user.getInt("rateNum") + 1);
            user.put("rating", user.getDouble("rateTotal")/user.getInt("rateNum"));
        } catch(JSONException e) {
            Log.e("jsonerror", e.getMessage());
        }

        return user;

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

    public static void registerNewUser(String firstName, String lastName, String email,
                                       String password, String userType, String price,
                                       String pendingQualifications, Context context) {

        // make POST request
        try {
            // put params in a map format
            Map<String, String> postParams = new HashMap<>();
            postParams.put("email", email);
            postParams.put("firstName", firstName);
            postParams.put("lastName", lastName);
            postParams.put("password", password);
            postParams.put("userType", userType);
            postParams.put("price", price);
            postParams.put("numSessions", "0");
            postParams.put("totalCost", "0");
            postParams.put("avgCost", "0");
            postParams.put("rateNum", "0");
            postParams.put("rateTotal", "0");
            postParams.put("rating", "0");
            postParams.put("balance", "100");
            postParams.put("qualifications", "");
            postParams.put("pendingQualifications", pendingQualifications);
            postParams.put("sessions", "");
            postParams.put("banned", "false");

            AccessWebTaskPost task = new AccessWebTaskPost(postParams);
            task.execute("http://10.0.2.2:3000/registerUser");
            Log.d("register_user", "successfully registered new user?");
        } catch(Exception e) {
            Log.d("error post", e.getMessage());
        }
    }

    // find user by email
    public static JSONObject findUser(String email) {

        JSONArray allUsers = loadUsers();


        JSONObject result = null;
        try{
            // loop through users and find matching email
            for (int i = 0; i < allUsers.length(); i++) {
                JSONObject userJson = allUsers.getJSONObject(i);
                Log.d("email", userJson.getString("email"));
                if (userJson.getString("email").equals(email)) {
                    result = userJson;
                }
            }
        } catch(JSONException e) {
            Log.e("json error", e.getMessage());
        }
        // return matching json object
        return result;
    }

    public static void updateRating (String emailAddress, double newRating, double newRateTotal,
                                     int newRateNum, Context context) {
        String FILENAME = userDatabase;

        // get up to date list of allUsers as String
        JSONArray allUsers = loadUsers();
        List<String> updatedUsers =  new LinkedList<String>();
        for (int i = 0; i < allUsers.length(); i++) {
            try {
                JSONObject userJson = allUsers.getJSONObject(i);
                if (userJson.getString("email").equals(emailAddress)) {
                    String firstName = userJson.getString("firstName");
                    String lastName = userJson.getString("lastName");
                    String password = userJson.getString("password");
                    String userType = userJson.getString("userType");
                    String price = userJson.getString("price");
                    String days = userJson.getString("days");
                    String times = userJson.getString("times");
                    String totalCost = userJson.getString("totalCost");
                    String avgCost = userJson.getString("avgCost");
                    String rating = Double.toString(newRating);
                    String rateTotal = Double.toString(newRateTotal);
                    String rateNum = Integer.toString(newRateNum);
                    String numSessions = userJson.getString("numSessions");
                    String balance = userJson.getString("balance");
                    String qualifications = userJson.getString("qualifications");

                    String JSONobj = "{ firstName:" + firstName + ",lastName:" + lastName + ",email:" + emailAddress + ",password:" + password +
                            ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                            + times + ",numSessions:"+ numSessions + ",rateNum:" + rateNum + ",rating:"
                            + rating + ",rateTotal:" + rateTotal + ",totalCost:" + totalCost + ",avgCost:"
                            + avgCost + ",balance:" + balance + ",qualifications:" + qualifications + "}";

                    updatedUsers.add(JSONobj);
                } else {
                    updatedUsers.add(userJson.toString());
                }
            } catch (JSONException e) {
                Log.e("JSONException", e.getStackTrace().toString());
            }
        }
        // overwrite old file and rewrite all new users
        BufferedWriter  w = null;
        try {
            w = new BufferedWriter( new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE)));
            for (String user : updatedUsers) {
                w.write(user + "\n");
            }
            w.close();
        } catch (IOException e) {
            Log.e("IOException", e.getStackTrace().toString());
        }


    }

    public static void updateBalance (String emailAddress, double newBalance, double sessionPrice, Context context) {
        String FILENAME = userDatabase;

        // get up to date list of allUsers as String
        JSONArray allUsers = loadUsers();
        List<String> updatedUsers =  new LinkedList<String>();
        for (int i = 0; i < allUsers.length(); i++) {
            try {
                JSONObject userJson = allUsers.getJSONObject(i);
                if (userJson.get("email").equals(emailAddress)) {

                    String firstName = userJson.getString("firstName");
                    String lastName = userJson.getString("lastName");
                    String password = userJson.getString("password");
                    String userType = userJson.getString("userType");
                    String days = userJson.getString("days");
                    String times = userJson.getString("times");
                    String rating = userJson.getString("rating");
                    String rateTotal = userJson.getString("rateTotal");
                    String balance = Double.toString(newBalance);
                    String rateNum = userJson.getString("rateNum");
                    String qualifications = userJson.getString("qualifications");
                    String price = userJson.getString("price");

                    // update average cost
                    String totalCost = userJson.getString("totalCost");
                    String numSessions = userJson.getString("numSessions");

                    double totalCostDouble = Double.parseDouble(totalCost);
                    totalCostDouble += sessionPrice;
                    totalCost = Double.toString(totalCostDouble);

                    int numSessionsInt = Integer.parseInt(numSessions);
                    numSessionsInt ++;
                    numSessions = Integer.toString(numSessionsInt);

                    double averageCostDouble = totalCostDouble / numSessionsInt;
                    String avgCost = Double.toString(averageCostDouble);


                    String JSONobj = "{ firstName:" + firstName + ",lastName:" + lastName + ",email:" + emailAddress + ",password:" + password +
                            ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                            + times + ",numSessions:" + numSessions + ",rateNum:" + rateNum + ",rating:" + rating + ",rateTotal:" + rateTotal + ",totalCost:"
                            + totalCost + ",avgCost:" + avgCost + ",balance:" + balance + ",qualifications:" + qualifications + "}";

                    updatedUsers.add(JSONobj);
                } else {
                    updatedUsers.add(userJson.toString());
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

    }

    public static JSONArray loadUsers() {
        JSONArray users = new JSONArray();
        try {
            URL url = new URL("http://10.0.2.2:3000/getUsers/");
            AccessWebTaskGet task = new AccessWebTaskGet();
            task.execute(url);
            String info = task.get();
            Log.d("info", info);
            JSONObject json = new JSONObject(info);
            users = json.getJSONArray("users");
        } catch (Exception e) {
            Log.e("error", e.toString());
            Log.e("Loading Users >>>>", "failed");
        }
        return users;
    }


    public static List<ComplaintsObject> loadComplaints() {
        String FILENAME = complaintsDatabase;
        BufferedReader fos = null;
        List<ComplaintsObject> tempList = new LinkedList<ComplaintsObject>();
        List<ComplaintsObject> returnVal = new LinkedList<ComplaintsObject>();


        //File complaints = MainActivity.context.getDir(complaintsDatabase, Context.MODA);
        //Log.d("DATA MANAG", MainActivity.context.getDir(complaintsDatabase, Context.MODE_PRIVATE);
        //complaints.delete();

        try {
            fos = new BufferedReader(new InputStreamReader(MainActivity.context.openFileInput(FILENAME)));

            while (fos.ready()) {
                String curLine = fos.readLine();
                Log.d("DATA MANAG", String.valueOf(curLine));
                if (curLine.split(":").length == 4)
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
        JSONArray allUsers = DataManagement.loadUsers();

        try {
            URL url = new URL("http://10.0.2.2:3000/getUsers/");
            AccessWebTaskGet task = new AccessWebTaskGet();
            task.execute(url);
            String name = task.get();
            Log.d("Called URL>>>>>>>>>>>>>", name);
            for (int i = 0; i < allUsers.length(); i++) {
                String e = allUsers.getJSONObject(i).getString("email");
                if (email.equals(e)) return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
