package com.example.a350project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


// import android.view.View.OnClickListener;


public class SignupTutorActivity extends AppCompatActivity {

    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private String password = "";
    private String userType = "";
    private String price = "";
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_tutor_page);

        context = getApplicationContext();

        //button to sign up as a tutor
        Button tutorSignupButton = (Button) findViewById(R.id.signup_tutor_button);
        tutorSignupButton.setOnClickListener(new View.OnClickListener() {
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
                EditText firstNameField = (EditText) findViewById(R.id.first_name);
                Editable firstNameEditable = firstNameField.getText();
                firstName = firstNameEditable.toString();

                //get name field
                EditText lastNameField = (EditText) findViewById(R.id.last_name);
                Editable lastNameEditable = lastNameField.getText();
                lastName = lastNameEditable.toString();

                //get name field
                EditText priceField = (EditText) findViewById(R.id.price);
                Editable priceEditable = priceField.getText();
                price = priceEditable.toString();

                userType = "tutor";

                String days = "-";

                CheckBox mon = (CheckBox) findViewById(R.id.checkbox_mon);
                if (mon.isChecked()) {
                    days += "monday-";
                }

                CheckBox tues = (CheckBox) findViewById(R.id.checkbox_tues);
                if (tues.isChecked()) {
                    days += "tuesday-";
                }

                CheckBox weds = (CheckBox) findViewById(R.id.checkbox_weds);
                if (weds.isChecked()) {
                    days += "wednesday-";
                }
                CheckBox thurs = (CheckBox) findViewById(R.id.checkbox_thurs);
                if (thurs.isChecked()) {
                    days += "thursday-";
                }
                CheckBox fri = (CheckBox) findViewById(R.id.checkbox_fri);
                if (fri.isChecked()) {
                    days += "friday-";
                }
                CheckBox sat = (CheckBox) findViewById(R.id.checkbox_sat);
                if (sat.isChecked()) {
                    days += "saturday-";
                }
                CheckBox sun = (CheckBox) findViewById(R.id.checkbox_sun);
                if (sun.isChecked()) {
                    days += "sunday-";
                }

                String times = "-";

                CheckBox morning = (CheckBox) findViewById(R.id.checkbox_morning);
                if (morning.isChecked()) {
                    times += "morning-";
                }
                CheckBox afternoon = (CheckBox) findViewById(R.id.checkbox_afternoon);
                if (afternoon.isChecked()) {
                    times += "afternoon-";
                }
                CheckBox evening = (CheckBox) findViewById(R.id.checkbox_evening);
                if (evening.isChecked()) {
                    times += "evening-";
                }

                //check if the fields are value
                if (email.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")) {
                    TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                    error.setText("Please fill out all fields");
                    return;
                } else if (DataManagement.userExists(email)) {
                    TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                    error.setText("The email you chose is taken");
                    return;
                } else {
                    MainActivity.currentUserEmail = email;
                    DataManagement.registerNewUser(firstName, lastName, email, password, userType, price, days, times, context);
                    launchMainActivity();
                    finish();
                }
            }


        });

        /*Button qualificationsButton = (Button) findViewById(R.id.qualifications);
        qualificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Enter Your Subject Qualifications");
            }
        });*/

        Button btn = (Button) findViewById(R.id.qualifications);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    // sends you back to the login activity
    public void launchLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }



}
