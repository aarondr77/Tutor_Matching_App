package com.example.a350project;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.TextView;
=======
>>>>>>> marketplace
import android.widget.Toast;
import android.view.View;
// import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private static final String username = "";

    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        context = getApplicationContext();

        // add onClick listener for the login button
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                TextView error = (TextView) findViewById(R.id.error);
=======
>>>>>>> marketplace

                //get username field
                EditText emailField = (EditText) findViewById(R.id.email);
                Editable emailEditable = emailField.getText();
                String email = emailEditable.toString();

                //get password field
                EditText passwordField = (EditText) findViewById(R.id.password);
                Editable passwordEditable = passwordField.getText();
                String password = passwordEditable.toString();


                if (password.equals("") || email.equals("")) {
<<<<<<< HEAD
                    error.setText("Error: Must enter a valid username and password");
=======
>>>>>>> marketplace
                    Log.e("tag1", "Must enter a username and password");
                    return;
                } else {
                    List<String> users = DataManagement.loadUsers();
                    for (String user: users) {
                        String[] info = user.split(",");
                        String userEmail = info[1].split(":")[1];
                        String userPassword = info[2].split(":")[1];
                        if (email.equals(userEmail) && password.equals(userPassword)) {
                            MainActivity.currentUserEmail = email;
                            emailField.setText("");
                            passwordField.setText("");
                            launchMainActivity();
                            return;
                        }
                    }
<<<<<<< HEAD
                    error.setText("Error: Incorrect email/password combination");
=======
>>>>>>> marketplace
                    Log.e("tag1", "Incorrect email/password combination");
                    return;
                }
            }

        });

        // add onClick listener for the login button
        Button signupStudentButton = (Button) findViewById(R.id.signup_student_button);
        signupStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignupActivity();
            }
        });

        // add onClick listener for the login button
        Button signupTutorButton = (Button) findViewById(R.id.signup_both_button);
        signupTutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignupTutorActivity();
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
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    // sends you to the main activity after you login
    public void launchSignupTutorActivity() {
        Intent i = new Intent(this, SignupTutorActivity.class);
        startActivity(i);
    }
}
