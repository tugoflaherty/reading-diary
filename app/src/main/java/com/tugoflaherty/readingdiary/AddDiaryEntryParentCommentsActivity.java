package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class AddDiaryEntryParentCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary_entry_parent_comments);

        String readingStart = getIntent().getStringExtra("readingStart");
        String readingEnd = getIntent().getStringExtra("readingEnd");
        String bookTitle = getIntent().getStringExtra("bookTitle");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        int pageCount = getIntent().getIntExtra("pageCount",0);
        int startPage = getIntent().getIntExtra("startPage",0);
        int endPage = getIntent().getIntExtra("endPage",0);
        float enjoymentRating = getIntent().getFloatExtra("enjoymentRating", 0.0F);
        String pupilComments = getIntent().getStringExtra("pupilComments");

        RatingBar readingAbilityRatingInputField = (RatingBar) findViewById(R.id.new_diary_entry_parent_comments_reading_ability_rating_bar);
        EditText parentCommentsInputField = (EditText) findViewById(R.id.new_parent_comments_input);
        Button cancel = (Button) findViewById(R.id.new_diary_entry_parent_comments_button_cancel);
        Button next = (Button) findViewById(R.id.new_diary_entry_parent_comments_button_next);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.add_diary_entry_parent_comments_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.add_diary_entry_parent_comments_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.add_diary_entry_parent_comments_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.add_diary_entry_parent_comments_navigation_button_settings);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readingAbilityRatingInputField.setRating(0.0F);
                parentCommentsInputField.setText("");
                Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(HomeScreen);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fieldsCompleted = true;
                float readingAbilityRating = 0.0F;
                String parentComments = "";
                readingAbilityRating = readingAbilityRatingInputField.getRating();
                parentComments = parentCommentsInputField.getText().toString();

                if ((parentComments.equals(null)) || (parentComments.equals(""))) {
                    parentCommentsInputField.setHintTextColor(getResources().getColor(R.color.red));
                    Message.message(getApplicationContext(), "Parent Comments Must Be Completed");
                    fieldsCompleted = false;
                }

                if (fieldsCompleted == true) {
                    Intent AddDiaryEntryTeacherCommentsScreen = new Intent(getApplicationContext(), AddDiaryEntryTeacherCommentsActivity.class);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("readingStart", readingStart);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("readingEnd", readingEnd);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("bookTitle", bookTitle);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("bookAuthor", bookAuthor);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("pageCount",pageCount);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("startPage", startPage);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("endPage", endPage);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("enjoymentRating", enjoymentRating);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("pupilComments", pupilComments);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("readingAbilityRating", readingAbilityRating);
                    AddDiaryEntryTeacherCommentsScreen.putExtra("parentComments", parentComments);
                    startActivity(AddDiaryEntryTeacherCommentsScreen);
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