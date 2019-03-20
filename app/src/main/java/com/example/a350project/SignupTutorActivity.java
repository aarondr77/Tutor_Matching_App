package com.example.a350project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


// import android.view.View.OnClickListener;


public class SignupTutorActivity extends AppCompatActivity {

    private String email = "";
    private String name = "";
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
                EditText nameField = (EditText) findViewById(R.id.name);
                Editable nameEditable = nameField.getText();
                name = nameEditable.toString();

                //get name field
                EditText priceField = (EditText) findViewById(R.id.price);
                Editable priceEditable = priceField.getText();
                price = priceEditable.toString();

                userType = "tutor";

                //check if the fields are value
                if (email.equals("") || password.equals("") || name.equals("")) {
                    Log.e("error", "Please fill out all fields");
                    return;
                } else {
                    MainActivity.currentUserEmail = email;
                    DataManagement.registerNewUser(name, email, password, userType, context);
                    launchMainActivity();
                }
            }

        });

        //button to sign up as both
        Button bothSignupButton = (Button) findViewById(R.id.signup_both_button);
        bothSignupButton.setOnClickListener(new View.OnClickListener() {
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

                userType = "both";

                //check if the fields are value
                if (email.equals("") || password.equals("") || name.equals("")) {
                    Log.e("error", "Please fill out all fields and enter a valid email address");
                    return;
                } else {
                    MainActivity.currentUserEmail = email;
                    DataManagement.registerNewUser(name, email, password, userType, context);
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

    // sends you back to the login activity
    public void launchLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}
