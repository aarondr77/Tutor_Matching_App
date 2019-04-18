package com.example.a350project;

import java.util.LinkedList;


public class ComplaintsFunctions {

    private static LinkedList<ComplaintsObject> allComplaints = new LinkedList<ComplaintsObject>();

    ComplaintsFunctions() {}

    public static void loadComplaints() {
        //DataManagement.writeComplaint(MainActivity.context ,new ComplaintsObject("'Hahaha, you suck'","chris@gmail.com","Approved","Aaron"));
        //DataManagement.writeComplaint(MainActivity.context ,new ComplaintsObject("'What a terrible tutor!'","aaron@gmail.com","Denied","Chris"));
        //DataManagement.writeComplaint(MainActivity.context , new ComplaintsObject("'What a terrible tutor!'","DUMMY","Denied","Chris"));
        //DataManagement.writeComplaint(MainActivity.context , new ComplaintsObject("'Hahaha, you suck'","No Show","Approved","Aaron"));
        allComplaints.clear();
        allComplaints.addAll(DataManagement.loadComplaints());
    }

    public static void addComplaint(String submitter, String content, String target) {
        ComplaintsObject newComplaint = new ComplaintsObject(content, submitter, "Pending", target);
        allComplaints.add(newComplaint);
        DataManagement.writeComplaint(MainActivity.context , newComplaint);
    }

    public static LinkedList<ComplaintsObject> getAllComplaints() {
        return allComplaints;
    }
}
