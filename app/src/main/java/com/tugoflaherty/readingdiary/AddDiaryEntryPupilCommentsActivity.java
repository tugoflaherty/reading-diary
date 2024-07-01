package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class AddDiaryEntryPupilCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_entry_pupil_comments);

        String readingStart = getIntent().getStringExtra("readingStart");
        String readingEnd = getIntent().getStringExtra("readingEnd");
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        int pageCount = getIntent().getIntExtra("pageCount",0);
        int startPage = getIntent().getIntExtra("startPage",0);
        int endPage = getIntent().getIntExtra("endPage",0);

        RatingBar enjoymentRatingInputField = (RatingBar) findViewById(R.id.new_diary_entry_pupil_comments_enjoyment_rating_bar);
        EditText pupilCommentsInputField = (EditText) findViewById(R.id.new_pupil_comments_input);
        Button cancel = (Button) findViewById(R.id.new_diary_entry_pupil_comments_button_cancel);
        Button next = (Button) findViewById(R.id.new_diary_entry_pupil_comments_button_next);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.add_diary_entry_pupil_comments_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.add_diary_entry_pupil_comments_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.add_diary_entry_pupil_comments_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.add_diary_entry_pupil_comments_navigation_button_settings);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enjoymentRatingInputField.setRating(0.0F);
                pupilCommentsInputField.setText("");
                Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(HomeScreen);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fieldsCompleted = true;
                float enjoymentRating = 0.0F;
                String pupilComments = "";
                enjoymentRating = enjoymentRatingInputField.getRating();
                pupilComments = pupilCommentsInputField.getText().toString();

                if ((pupilComments.equals(null)) || (pupilComments.equals(""))) {
                    pupilCommentsInputField.setHintTextColor(getResources().getColor(R.color.red));
                    Message.message(getApplicationContext(), "Pupil Comments Must Be Completed");
                    fieldsCompleted = false;
                }

                if (fieldsCompleted == true) {
                    Intent AddDiaryEntryParentCommentsScreen = new Intent(getApplicationContext(), AddDiaryEntryParentCommentsActivity.class);
                    AddDiaryEntryParentCommentsScreen.putExtra("readingStart", readingStart);
                    AddDiaryEntryParentCommentsScreen.putExtra("readingEnd", readingEnd);
                    AddDiaryEntryParentCommentsScreen.putExtra("bookTitle", bookTitle);
                    AddDiaryEntryParentCommentsScreen.putExtra("bookAuthor", bookAuthor);
                    AddDiaryEntryParentCommentsScreen.putExtra("pageCount",pageCount);
                    AddDiaryEntryParentCommentsScreen.putExtra("startPage", startPage);
                    AddDiaryEntryParentCommentsScreen.putExtra("endPage", endPage);
                    AddDiaryEntryParentCommentsScreen.putExtra("enjoymentRating", enjoymentRating);
                    AddDiaryEntryParentCommentsScreen.putExtra("pupilComments", pupilComments);
                    startActivity(AddDiaryEntryParentCommentsScreen);
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