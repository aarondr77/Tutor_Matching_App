package com.example.a350project;

import android.content.Context;
import android.se.omapi.Session;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import org.json.JSONArray;


public class DataManagement {

    private static String sessionDatabase = "Sessions1001.txt";
    private static String userDatabase = "UserDatabase1002.txt";
    private static String complaintsDatabase = "ComplaintsDatabase1000.txt";


    public DataManagement() { }

    public static List<SessionObject> loadSessions () {

        String sessionsString = "";
        List<SessionObject> returnVal = new LinkedList<SessionObject>();

        try {
            URL url = new URL("http://10.0.2.2:3000/getSessions/");
            AccessWebTaskGet task = new AccessWebTaskGet();
            task.execute(url);
            sessionsString = task.get();
            Log.d("Called GetSessions>>>>>", sessionsString);


            JSONObject s = new JSONObject(sessionsString);

            Log.d("ConvertedToJSON>>>>>", s.getString("sessions"));

            JSONArray sa = new JSONArray(s.getString("sessions"));
            Log.d("ConvertedToArray Size", "length " + sa.length() );

            for (int i = 0; i < sa.length(); i++) {

                JSONObject currentSession = sa.getJSONObject(i);

                String sessionID = currentSession.getString("sessionID");
                String tutor = currentSession.getString("tutor");
                String student = currentSession.getString("student");
                String subject = currentSession.getString("subject");
                String date = currentSession.getString("date");
                String duration = currentSession.getString("duration");
                String price = currentSession.getString("price");
                String status = currentSession.getString("status");
                String studentEmail = currentSession.getString("studentEmail");
                String tutorEmail = currentSession.getString("tutorEmail");

                SessionObject session = new SessionObject(sessionID, tutor, student, tutorEmail, studentEmail, subject, date, duration, price, status);

                Log.d("Added to Return", sessionID);
                returnVal.add(session);
            }

        } catch (Exception e) {
            Log.d("FAILED LOADING!", e.getMessage());
        }

        return returnVal;

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


    public static void addRating(String userEmail, float rating) {

        String result = "";
        try {
            URL url = new URL("http://10.0.2.2:3000/updateRating/" + userEmail + "/" + Float.toString(rating));
            AccessWebTaskGet task = new AccessWebTaskGet();
            task.execute(url);
            result = task.get();
            Log.d("Called URL>>>addRate>>>", result);

        } catch (Exception e) {

        }
    }

    public static void writeComplaint(Context context, ComplaintsObject newComplaint) {


        try {
            // put params in a map format
            Map<String, String> postParams = new HashMap<>();
            postParams.put("content", newComplaint.getContent());
            postParams.put("target", newComplaint.getTarget());
            postParams.put("submitter", newComplaint.getSubmitter());
            postParams.put("status", newComplaint.getStatus());
            AccessWebTaskPost task = new AccessWebTaskPost(postParams);
            task.execute("http://10.0.2.2:3000/addComplaint");
        } catch(Exception e) {
            Log.d("error post", e.getMessage());
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


    public static void updateBalance (String emailAddress, int isIncreasing, double sessionPrice) {
        String FILENAME = userDatabase;

        // make POST request
        try {
            // put params in a map format
            Map<String, String> postParams = new HashMap<>();
            postParams.put("email", emailAddress);
            postParams.put("isIncreasing", Integer.toString(isIncreasing));
            postParams.put("sessionPrice", Double.toString(sessionPrice));

            AccessWebTaskPost task = new AccessWebTaskPost(postParams);
            task.execute("http://10.0.2.2:3000/updateBalanceAndCost");
        } catch(Exception e) {
            Log.d("error post", e.getMessage());
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
            URL url = new URL("http://10.0.2.2:3000/getComplaints/");
            AccessWebTaskGet task = new AccessWebTaskGet();
            task.execute(url);
            String complaints = task.get();
            Log.d("Called URL>>>>>>>>>>>>>", complaints);
            JSONArray  complaintsArray = (JSONArray) new JSONObject(complaints).get("complaints");
            for (int i = 0; i < complaintsArray.length(); i++) {
                JSONObject curComplaint = complaintsArray.getJSONObject(i);
                tempList.add(new ComplaintsObject((String) curComplaint.get("content"), (String) curComplaint.get("submitter"), (String) curComplaint.get("status"), (String) curComplaint.get("target")));

            }

            for (ComplaintsObject curComplaint : tempList) {
                if (curComplaint.getSubmitter().equals(MainActivity.currentUserEmail)) {
                    returnVal.add(curComplaint);
                }
            }

            return returnVal;
        } catch (Exception e) {
            Log.d("PRINT", e.toString());
        }

        return null;
    }

    public static boolean claimSession(String studentEmail, String studentName, String sessionID) {

        // make POST request
        try {
            // put params in a map format
            Map<String, String> postParams = new HashMap<>();
            postParams.put("sessionID", sessionID);
            postParams.put("studentEmail", studentEmail);
            postParams.put("studentName", studentName);

            AccessWebTaskPost task = new AccessWebTaskPost(postParams);
            task.execute("http://10.0.2.2:3000/claimSession");
        } catch(Exception e) {
            Log.d("error post", e.getMessage());
        }

        return true;
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
