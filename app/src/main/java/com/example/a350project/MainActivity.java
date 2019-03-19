package com.example.a350project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.a350project.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ComplaintsListFragment.OnListFragmentInteractionListener {

    public static final int test = 1;

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    public void onListFragmentInteraction(DummyContent.DummyItem item) {}

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    //fragment = ProfilePageFragment.newInstance("This", "my title");
                    //ft.replace(R.id.frame_container, fragment, "CURRENT_FRAG");
                    //ft.commit();
                    return true;
                case R.id.navigation_profilePage:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_container, ProfilePageFragment.newInstance("This", "my title"));
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.navigation_notifications:

                    return true;
                case R.id.navigation_complaints:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_container, ComplaintsFragment.newInstance("This", "my title"));
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft.replace(R.id.frame_container, ProfilePageFragment.newInstance("","") );
        ft.addToBackStack(null);
        ft.commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
