package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class EditUsersActivity extends AppCompatActivity {
    EditText pupilNameInputField;
    EditText parentNameInputField;
    EditText teacherNameInputField;
    myDbAdapter helper;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);

        helper = new myDbAdapter(this);

        pupilNameInputField = (EditText) findViewById(R.id.edit_users_pupils_input);
        parentNameInputField = (EditText) findViewById(R.id.edit_users_parents_input);
        teacherNameInputField = (EditText) findViewById(R.id.edit_users_teachers_input);
        Button cancel = (Button) findViewById(R.id.edit_users_button_cancel);
        Button save = (Button) findViewById(R.id.edit_users_button_save);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.edit_users_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.edit_users_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.edit_users_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.edit_users_navigation_button_settings);

        if ((!helper.getUserData().equals(null)) && (!helper.getUserData().equals(""))) {
            String returnedData = helper.getUserData();
            String[] userData = returnedData.split("¬");

            uid = userData[0];
            pupilNameInputField.setText(userData[1]);
            parentNameInputField.setText(userData[2]);
            teacherNameInputField.setText(userData[3]);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreen = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(SettingsScreen);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(v);
            }
        });

        homepageNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(HomeScreen);
            }
        });

        viewReadingHistoryNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewReadingHistoryScreen = new Intent(getApplicationContext(), ViewReadingHistoryActivity.class);
                startActivity(ViewReadingHistoryScreen);
            }
        });

        addDiaryEntryNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddDiaryEntryScreen = new Intent(getApplicationContext(), AddDiaryEntryInformationActivity.class);
                startActivity(AddDiaryEntryScreen);
            }
        });

        settingsNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreen = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(SettingsScreen);
            }
        });
    }

    public void addUser(View view) {
        boolean fieldsCompleted = true;
        String pupilName = pupilNameInputField.getText().toString();
        String parentName = parentNameInputField.getText().toString();
        String teacherName = teacherNameInputField.getText().toString();

        if ((pupilName.equals(null)) || (pupilName.equals(""))) {
            pupilNameInputField.setHintTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Enter Pupil Name");
            fieldsCompleted = false;
        }

        if ((parentName.equals(null)) || (parentName.equals(""))) {
            parentNameInputField.setHintTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Enter Parent Name");
            fieldsCompleted = false;
        }

        if ((teacherName.equals(null)) || (teacherName.equals(""))) {
            teacherNameInputField.setHintTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Enter Teacher Name");
            fieldsCompleted = false;
        }

        if (fieldsCompleted == true) {
            if (uid == null) {
                long id = helper.insertUserData(pupilName, parentName, teacherName);
                if (id <= 0) {
                    Message.message(getApplicationContext(), "Save Unsuccessful - Please Try Again");
                } else {
                    Message.message(getApplicationContext(), "Save Successful");
                    Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(HomeScreen);
                }
            }
            else {
                int count = helper.updateUser("1",pupilName,parentName,teacherName);
                if (count <= 0) {
                    Message.message(getApplicationContext(), "Save Unsuccessful - Please Try Again");
                }
                else {
                    Message.message(getApplicationContext(), "Save Successful");
                    Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(HomeScreen);
                }
            }
        }
        else {
            Message.message(getApplicationContext(),"Ensure All Fields Are Completed Correctly");
        }
    }
}