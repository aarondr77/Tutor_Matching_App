package com.example.a350project;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
// import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;


public class LoginActivity extends AppCompatActivity {

    private static final String username = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        // add onClick listener for the login button
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //get username field
                EditText emailField = (EditText) findViewById(R.id.email);
                Editable emailEditable = emailField.getText();
                String email = emailEditable.toString();

                //get password field
                EditText passwordField = (EditText) findViewById(R.id.password);
                Editable passwordEditable = passwordField.getText();
                String password = passwordEditable.toString();


                if (password.equals("") || email.equals("")) {
                    Log.e("tag1", "Must enter a username and password");
                    return;
                } else {
                    MainActivity.currentUserEmail = email;
                    //reset fields and launch MainActivity
                    emailField.setText("");
                    passwordField.setText("");
                    launchMainActivity();
                }
            }

        });

        // add onClick listener for the login button
        Button signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignupActivity();
            }

        });

    }

    // sends you to the main activity after you login
    public void launchMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // sends you to the main activity after you login
    public void launchSignupActivity() {
        Log.d("tag1", "entering this method");
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
