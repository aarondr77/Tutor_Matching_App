package com.example.a350project;

import android.content.Context;
import android.se.omapi.Session;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
    private static String userDatabase = "UserDatabase1001.txt";
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
                                       String password, String userType, String price, String days,
                                       String times, String qualifications, Context context) {
        // make POST request
        try {
            // put params in a map format
            Map<String, String> postParams = new HashMap<>();
            postParams.put("email", "aaron@gmail.com");
            postParams.put("addBalance", "10000");
            AccessWebTaskPost task = new AccessWebTaskPost(postParams);
            task.execute("http://10.0.2.2:3000/addBalance");
        } catch(Exception e) {
            Log.d("error post", e.getMessage());
        }

        String FILENAME = userDatabase;

        List<String> allUsers = loadUsers();
        String JSONobj = "{ firstName:" + firstName + ",lastName:" + lastName + ",email:" + email + ",password:" + password +
                ",userType:" + userType + ",price:" + price + ",days:" + days + ",times:"
                + times + ",numSessions:" + "0" + ",totalCost:" + "0" +  ",avgCost:"  + "0" +
                ",rateNum:" + "0" + ",rateTotal:" + "0" + ",rating:" +  "0"  + ",balance:" + "100" +
                ",qualifications:" + qualifications + "}";

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

    // find user by email, used for finding current user
    public static JSONObject findUser(String email) {
        List<String> allUsers = loadUsers();
        for (String e: allUsers) Log.d("u", e);

        JSONObject result = null;
        try{
            // loop through users and find matching email
            for(String user : allUsers) {
                JSONObject userJson = new JSONObject(user);
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

    public static void updateRating (String emailAddress, double newRating, double newRateTotal, int newRateNum, Context context) {
        String FILENAME = userDatabase;

        // get up to date list of allUsers as String
        List<String> allUsers = loadUsers();
        List<String> updatedUsers =  new LinkedList<String>();
        for (String currentUser : allUsers) {
            try {
                JSONObject userJson = new JSONObject(currentUser);
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
                    updatedUsers.add(currentUser);
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
        List<String> allUsers = loadUsers();
        List<String> updatedUsers =  new LinkedList<String>();
        for (String currentUser : allUsers) {
            try {
                JSONObject userJson = new JSONObject(currentUser);
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
        List<String> allUsers = DataManagement.loadUsers();

        try {
            URL url = new URL("http://10.0.2.2:3000/getUsers/");
            AccessWebTaskGet task = new AccessWebTaskGet();
            task.execute(url);
            String name = task.get();
            Log.d("Called URL>>>>>>>>>>>>>", name);
        } catch (Exception e) {

        }
        for (String u: allUsers) {
            String[] info = u.split(",");
            String e = info[2].split(":")[1];
            if (email.equals(e)) return true;
        }
        return false;
    }
}
