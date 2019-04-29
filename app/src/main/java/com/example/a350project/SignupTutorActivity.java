package com.example.a350project;

import android.app.AlertDialog;
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

import java.util.HashSet;
import java.util.Set;


public class SignupTutorActivity extends AppCompatActivity {

    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private String password = "";
    private String userType = "";
    private String price = "";
    private String qualifications = "";
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

                //check that the price is a double
                try {
                    Double.parseDouble(price);
                } catch (NumberFormatException e) {
                    TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                    error.setText("Please enter a numerical value for minimum price");
                    return;
                }

                //check if the fields are filled out
                if (email.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || price.equals("")) {
                    TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                    error.setText("Please fill out all fields");
                    return;
                } else if (DataManagement.userExists(email)) {
                    TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                    error.setText("The email you chose is taken");
                    return;
                } else {
                    if (!checkQualifications()) {
                        TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                        error.setText("Please re-enter qualifications in the specified format");
                        qualifications = "";
                        return;
                    }
                    MainActivity.currentUserEmail = email;
                    DataManagement.registerNewUser(firstName, lastName, email, password, userType,
                            price, qualifications.substring(0, qualifications.length() - 1), context);
                    launchMainActivity();
                    finish();
                }
            }


        });

        //button to return to sign in page
        Button qualifications = (Button) findViewById(R.id.qualifications);
        qualifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialog(v);
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

    private boolean checkQualifications() {
        Set<String> grades = new HashSet<String>();
        grades.add("A");
        grades.add("A+");
        grades.add("A-");
        grades.add("B+");
        grades.add("B");
        grades.add("B-");
        grades.add("C+");
        grades.add("C");
        grades.add("C-");
        grades.add("D+");
        grades.add("D");
        grades.add("D-");
        grades.add("F");

        String[] quals = qualifications.substring(0, qualifications.length() - 1).split("~");

        for (String q: quals) {
            String subject = q.split("-")[0];
            String grade = q.split("-")[1];

            if (subject.length() > 7) return false;

            if (!grades.contains(grade)) return false;
            if (!isLetter(subject.charAt(0)) || !isLetter(subject.charAt(1)) || !isLetter(subject.charAt(2))) return false;
            if (!isNumberOrLetter(subject.charAt(3))) return false;
            if (!isNumber(subject.charAt(4)) || !isNumber(subject.charAt(5))) return false;
            if (subject.length() == 7) {
                if (!isNumberOrLetter(subject.charAt(6))) return false;
            }
        }
        return true;

    }

    private boolean isLetter(int c) {
        return (c >= 65 && c <= 90);
    }

    private boolean isNumberOrLetter(int c) {
        return (c >= 48 && c <= 57) || (c >= 65 && c <= 90);
    }

    private boolean isNumber(int c) {
        return (c >= 48 && c <= 57);
    }


    private void launchDialog(View v) {
        Context c = v.getContext();

        LayoutInflater factory = LayoutInflater.from(c);
        final View textEntryView = factory.inflate(R.layout.qualifications_entry_popup, null);
        //text_entry is an Layout XML file containing two text field to display in alert dialog
        final EditText subject = (EditText) textEntryView.findViewById(R.id.enterSubject);
        final EditText grade = (EditText) textEntryView.findViewById(R.id.enterGrade);
        subject.setHint("MATH114");
        grade.setHint("A");

        final AlertDialog.Builder alert = new AlertDialog.Builder(c);

        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Submit Qualifications")
                .setView(textEntryView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Editable subjectsEditable = subject.getText();
                        String subject = subjectsEditable.toString();

                        Editable gradesEditable = grade.getText();
                        String grade = gradesEditable.toString();

                        if (subjectsEditable.toString().equals("") || gradesEditable.toString().equals("")) {
                            TextView error = (TextView) findViewById(R.id.error_tutor_signup);
                            error.setText("Please fill all fields");
                            return;
                        }

                        qualifications += subject + "-"  + grade + "~";

                    }


                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
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
