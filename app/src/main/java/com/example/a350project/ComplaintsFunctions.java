package com.example.a350project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;


public class ComplaintsFunctions {

    private static LinkedList<ComplaintsObject> allComplaints = new LinkedList<ComplaintsObject>();

    ComplaintsFunctions() {

    }

    public static void loadComplaints() {
        //allComplaints.add(new ComplaintsObject("'Hahaha, you suck'","John","Approved","Aaron"));
        //allComplaints.add(new ComplaintsObject("'What a terrible tutor!'","Aaron","Denied","Chris"));
        //DataManagement.writeComplaint(MainActivity.context , new ComplaintsObject("'What a terrible tutor!'","DUMMY","Denied","Chris"));
        DataManagement.writeComplaint(MainActivity.context , new ComplaintsObject("'Hahaha, you suck'","No Show","Approved","Aaron"));
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
