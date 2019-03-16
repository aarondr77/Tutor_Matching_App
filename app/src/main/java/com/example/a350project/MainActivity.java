package com.example.a350project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final int test = 1;

    private ComplaintsFunctions compalintsFunctions = new ComplaintsFunctions(this);


    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setContentView(R.layout.activity_main);
                    return true;
                case R.id.navigation_profilePage:
                    createProfilePageActivity();
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_complaints:
                    setContentView(R.layout.activity_complaints);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/
    }

    public void onLaunchComplaintButtonClick(View view) {
        compalintsFunctions.onLaunchComplaintButtonClick(view);
    }

    public void onHomeClick(View view) {
        setContentView(R.layout.activity_main);
    }

    public void onComplaintClick(View view) {
        setContentView(R.layout.activity_complaints);
    }

    public void onProfilePageCLick(View view) {
        setContentView(R.layout.activity_profilepage);
    }

}
