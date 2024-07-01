package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class EditDiaryEntryPupilCommentsActivity extends AppCompatActivity {
    RatingBar enjoymentRatingInputField;
    EditText pupilCommentsInputField;
    myDbAdapter helper;
    String diaryEntryId, uid,readingStart,readingEnd,bookTitle,bookAuthor,pageCount,startPage,endPage,enjoymentRating,pupilComments,readingAbility,parentComments,readingProgress,teacherComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry_pupil_comments);

        diaryEntryId = getIntent().getStringExtra("diaryEntryId");
        helper = new myDbAdapter(this);

        enjoymentRatingInputField = (RatingBar) findViewById(R.id.edit_diary_entry_pupil_comments_enjoyment_rating_bar);
        pupilCommentsInputField = (EditText) findViewById(R.id.edit_pupil_comments_input);
        Button cancel = (Button) findViewById(R.id.edit_diary_entry_pupil_comments_button_cancel);
        Button save = (Button) findViewById(R.id.edit_diary_entry_pupil_comments_button_save);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.edit_diary_entry_pupil_comments_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.edit_diary_entry_pupil_comments_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.edit_diary_entry_pupil_comments_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.edit_diary_entry_pupil_comments_navigation_button_settings);

        if ((!helper.getDiaryEntryDataById(diaryEntryId).equals(null)) && (!helper.getDiaryEntryDataById(diaryEntryId).equals(""))) {
            String returnedData = helper.getDiaryEntryDataById(diaryEntryId);
            String[] diaryEntryData = returnedData.split("¬");

            uid = diaryEntryData[0];
            readingStart = diaryEntryData[1];
            readingEnd = diaryEntryData[2];
            bookTitle = diaryEntryData[3];
            bookAuthor = diaryEntryData[4];
            pageCount = diaryEntryData[5];
            startPage = diaryEntryData[6];
            endPage = diaryEntryData[7];
            enjoymentRating = diaryEntryData[8];
            pupilComments = diaryEntryData[9];
            readingAbility = diaryEntryData[10];
            parentComments = diaryEntryData[11];
            readingProgress = diaryEntryData[12];
            teacherComments = diaryEntryData[13];

            enjoymentRatingInputField.setRating(Float.parseFloat(enjoymentRating));
            pupilCommentsInputField.setText(pupilComments);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditDiaryEntryMenuScreen = new Intent(getApplicationContext(), EditDiaryEntryMenuActivity.class);
                EditDiaryEntryMenuScreen.putExtra("diaryEntryId",diaryEntryId);
                startActivity(EditDiaryEntryMenuScreen);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDiaryEntry(v);
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

    public void updateDiaryEntry(View view) {
        boolean fieldsCompleted = true;
        Float enjoymentRating = 0.0F;
        String pupilComments = "";
        enjoymentRating = enjoymentRatingInputField.getRating();
        pupilComments = pupilCommentsInputField.getText().toString();

        if ((pupilComments.equals(null)) || (pupilComments.equals(""))) {
            pupilCommentsInputField.setHintTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(), "Pupil Comments Must Be Completed");
            fieldsCompleted = false;
        }

        if (fieldsCompleted == true) {
            String enjoymentRatingString = String.valueOf(enjoymentRating);
            int count = helper.updateDiaryEntry(uid,readingStart,readingEnd,bookTitle,bookAuthor,pageCount,startPage,endPage,enjoymentRatingString,pupilComments,readingAbility,parentComments,readingProgress,teacherComments,"1");
            if (count <= 0) {
                Message.message(getApplicationContext(), "Update Unsuccessful - Please Try Again");
            }
            else {
                Message.message(getApplicationContext(), "Update Successful");
                Intent ViewDiaryEntryScreen = new Intent(getApplicationContext(), ViewDiaryEntryActivity.class);
                ViewDiaryEntryScreen.putExtra("diaryEntryId",diaryEntryId);
                startActivity(ViewDiaryEntryScreen);
            }
        }
        else {
            Message.message(getApplicationContext(),"Ensure All Fields Are Completed Correctly");
        }
    }
}