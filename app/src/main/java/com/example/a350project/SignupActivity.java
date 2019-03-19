package com.example.a350project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// import android.view.View.OnClickListener;


public class SignupActivity extends AppCompatActivity {

    private static final String username = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        //button to sign up
        Button signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get email field
                EditText emailField = (EditText) findViewById(R.id.email);
                Editable emailEditable = emailField.getText();
                String email = emailEditable.toString();

                //get password field
                EditText passwordField = (EditText) findViewById(R.id.password);
                Editable passwordEditable = passwordField.getText();
                String password = passwordEditable.toString();

                //get name field
                EditText nameField = (EditText) findViewById(R.id.name);
                Editable nameEditable = passwordField.getText();
                String name = passwordEditable.toString();

                //check if the fields are value
                if (email.equals("") || password.equals("") || name.equals("")) {
                    Log.e("error", "Please fill out all fields and enter a valid email address");
                    return;
                } else {
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
        Log.d("tag", "entering this method?");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}
