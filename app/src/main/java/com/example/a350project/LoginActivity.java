package com.example.a350project;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class  LoginActivity extends AppCompatActivity {

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

                //get username field
                EditText emailField = (EditText) findViewById(R.id.email);
                Editable emailEditable = emailField.getText();
                String email = emailEditable.toString();

                //get password field
                EditText passwordField = (EditText) findViewById(R.id.password);
                Editable passwordEditable = passwordField.getText();
                String password = passwordEditable.toString();


                if (password.equals("") || email.equals("")) {
                    TextView error = (TextView) findViewById(R.id.error_login);
                    error.setText("Please fill out all fields");
                    return;
                } else {
                    JSONArray users = DataManagement.loadUsers();
                    Log.e("LoginActivity", "users" + users);
                    Log.e("LoginActivity", "users.length" + users.length());
                    for (int i = 0; i < users.length(); i++) {
                        String userEmail = "";
                        String userPassword = "";
                        Boolean userBanned = false;
                        try {
                            JSONObject user = users.getJSONObject(i);
                            userEmail = user.getString("email");
                            userPassword = user.getString("password");
                            userBanned = user.getBoolean("banned");

                            Log.d("user", user.toString());
                        } catch (Exception e) {
                            Log.e("getting user info >>> ", "failed");
                        }

                        if (email.equals(userEmail) && password.equals(userPassword)) {
                            if (userBanned) {
                                TextView error = (TextView) findViewById(R.id.error_login);
                                error.setText("You are Banned!");
                                return;
                            }
                            MainActivity.currentUserEmail = email;
                            emailField.setText("");
                            passwordField.setText("");
                            launchMainActivity();
                            return;
                        }
                    }
                    TextView error = (TextView) findViewById(R.id.error_login);
                    error.setText("Invalid email/password combination");
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
