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


public class ComplaintsFunctions {

    private Context context;

    ComplaintsFunctions(Context context) {
        this.context = context;
    }

    public void onLaunchComplaintButtonClick(View view) {
        Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
    }


}
