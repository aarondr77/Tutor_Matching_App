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
        allComplaints.add(new ComplaintsObject("'Hahaha, you suck'","John","Approved","Aaron"));
        allComplaints.add(new ComplaintsObject("'What a terrible tutor!'","Aaron","Denied","Chris"));
    }




    public static void addComplaint(String submitter, String content, String target) {
        allComplaints.add(new ComplaintsObject(content, submitter, "Pending", target));
    }

    public static LinkedList<ComplaintsObject> getAllComplaints() {
        return allComplaints;
    }
}
