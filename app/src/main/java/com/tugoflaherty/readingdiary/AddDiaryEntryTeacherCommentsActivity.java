package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class AddDiaryEntryTeacherCommentsActivity extends AppCompatActivity {
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_entry_teacher_comments);

        helper = new myDbAdapter(this);
        String readingStart = getIntent().getStringExtra("readingStart");
        String readingEnd = getIntent().getStringExtra("readingEnd");
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String pageCount = String.valueOf(getIntent().getIntExtra("pageCount",0));
        String startPage = String.valueOf(getIntent().getIntExtra("startPage",0));
        String endPage = String.valueOf(getIntent().getIntExtra("endPage",0));
        String enjoymentRating = String.valueOf(getIntent().getFloatExtra("enjoymentRating", 0.0F));
        String pupilComments = getIntent().getStringExtra("pupilComments");
        String readingAbilityRating = String.valueOf(getIntent().getFloatExtra("readingAbilityRating", 0.0F));
        String parentComments = getIntent().getStringExtra("parentComments");

        RatingBar readingProgressInputField = (RatingBar) findViewById(R.id.new_diary_entry_teacher_comments_reading_progress_rating_bar);
        EditText teacherCommentsInputField = (EditText) findViewById(R.id.new_teacher_comments_input);
        Button cancel = (Button) findViewById(R.id.new_diary_entry_teacher_comments_button_cancel);
        Button save = (Button) findViewById(R.id.new_diary_entry_teacher_comments_button_save);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.add_diary_entry_teacher_comments_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.add_diary_entry_teacher_comments_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.add_diary_entry_teacher_comments_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.add_diary_entry_teacher_comments_navigation_button_settings);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readingProgressInputField.setRating(0.0F);
                teacherCommentsInputField.setText("");
                Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(HomeScreen);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fieldsCompleted = true;
                float readingProgressRating = 0.0F;
                String teacherComments = "";
                readingProgressRating = readingProgressInputField.getRating();
                teacherComments = teacherCommentsInputField.getText().toString();

                if ((teacherComments.equals(null)) || (teacherComments.equals(""))) {
                    teacherCommentsInputField.setHintTextColor(getResources().getColor(R.color.red));
                    Message.message(getApplicationContext(), "Teacher Comments Must Be Completed");
                    fieldsCompleted = false;
                }

                if (fieldsCompleted == true) {
                    String readingProgressString = String.valueOf(readingProgressRating);
                    long id = helper.insertDiaryEntryData(readingStart, readingEnd, bookTitle, bookAuthor, pageCount, startPage, endPage, enjoymentRating, pupilComments, readingAbilityRating, parentComments, readingProgressString, teacherComments,"1");
                    if (id <= 0) {
                        Message.message(getApplicationContext(), "Save Unsuccessful - Please Try Again");
                    } else {
                        Message.message(getApplicationContext(), "Save Successful");
                        Intent ViewReadingHistoryScreen = new Intent(getApplicationContext(), ViewReadingHistoryActivity.class);
                        startActivity(ViewReadingHistoryScreen);
                    }
                }
                else {
                    Message.message(getApplicationContext(),"Ensure All Fields Are Completed Correctly");
                }
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
}