package com.example.a350project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.Context;
import android.widget.Spinner;


// import android.view.View.OnClickListener;


public class SignupActivity extends AppCompatActivity {

    private String email = "";
    private String name = "";
    private String password = "";
    private String userType = "";
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        context = getApplicationContext();

        //button to sign up as a student
        Button studentSignupButton = (Button) findViewById(R.id.signup_student_button);
        studentSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get email field
                EditText emailField = (EditText) findViewById(R.id.email);
                Editable emailEditable = emailField.getText();
                email = emailEditable.toString();

                //get password field
                EditText passwordField = (EditText) findViewById(R.id.password);
                Editable passwordEditable = passwordField.getText();
                password = passwordEditable.toString();

                //get name field
                EditText nameField = (EditText) findViewById(R.id.name);
                Editable nameEditable = nameField.getText();
                name = nameEditable.toString();

                userType = "student";


                boolean[] days = new boolean[7];

                CheckBox mon = (CheckBox) findViewById(R.id.checkbox_mon);
                if (mon.isChecked()) {
                    days[0] = true;
                }
                CheckBox tues = (CheckBox) findViewById(R.id.checkbox_tues);
                if (tues.isChecked()) {
                    days[1] = true;
                }
                CheckBox weds = (CheckBox) findViewById(R.id.checkbox_weds);
                if (weds.isChecked()) {
                    days[2] = true;
                }
                CheckBox thurs = (CheckBox) findViewById(R.id.checkbox_thurs);
                if (thurs.isChecked()) {
                    days[3] = true;
                }
                CheckBox fri = (CheckBox) findViewById(R.id.checkbox_fri);
                if (fri.isChecked()) {
                    days[4] = true;
                }
                CheckBox sat = (CheckBox) findViewById(R.id.checkbox_sat);
                if (sat.isChecked()) {
                    days[5] = true;
                }
                CheckBox sun = (CheckBox) findViewById(R.id.checkbox_sun);
                if (sun.isChecked()) {
                    days[6] = true;
                }

                for (boolean b: days) Log.d("days", b + "");

                //check if the fields are value
                if (email.equals("") || password.equals("") || name.equals("")) {
                    Log.e("error", "Please fill out all fields and enter a valid email address");
                    return;
                } else {
                    MainActivity.currentUserEmail = email;
                    DataManagement.registerNewUser(name, email, password, userType,"0", context);
                    launchMainActivity();
                }
            }

        });

        //button to return to sign in page
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLoginActivity();
            }
        });


    }

    // sends you to the main activity after you login
    public void launchMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // sends you to the main activity after you login
    public void launchLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}
