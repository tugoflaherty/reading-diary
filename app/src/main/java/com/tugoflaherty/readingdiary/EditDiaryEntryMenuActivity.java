package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EditDiaryEntryMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry_menu);

        String diaryEntryId = getIntent().getStringExtra("diaryEntryId");

        Button editDiaryEntryInformation = (Button) findViewById(R.id.edit_diary_entry_menu_button_information);
        Button editDiaryEntryPupilComments = (Button) findViewById(R.id.edit_diary_entry_menu_button_pupil_comments);
        Button editDiaryEntryParentComments = (Button) findViewById(R.id.edit_diary_entry_menu_button_parent_comments);
        Button editDiaryEntryTeacherComments = (Button) findViewById(R.id.edit_diary_entry_menu_button_teacher_comments);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.edit_diary_entry_menu_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.edit_diary_entry_menu_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.edit_diary_entry_menu_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.edit_diary_entry_menu_navigation_button_settings);

        editDiaryEntryInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditDiaryEntryInformationScreen = new Intent(getApplicationContext(), EditDiaryEntryInformationActivity.class);
                EditDiaryEntryInformationScreen.putExtra("diaryEntryId",diaryEntryId);
                startActivity(EditDiaryEntryInformationScreen);
            }
        });

        editDiaryEntryPupilComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditDiaryEntryPupilCommentsScreen = new Intent(getApplicationContext(), EditDiaryEntryPupilCommentsActivity.class);
                EditDiaryEntryPupilCommentsScreen.putExtra("diaryEntryId",diaryEntryId);
                startActivity(EditDiaryEntryPupilCommentsScreen);
            }
        });

        editDiaryEntryParentComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditDiaryEntryParentCommentsScreen = new Intent(getApplicationContext(), EditDiaryEntryParentCommentsActivity.class);
                EditDiaryEntryParentCommentsScreen.putExtra("diaryEntryId",diaryEntryId);
                startActivity(EditDiaryEntryParentCommentsScreen);
            }
        });

        editDiaryEntryTeacherComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditDiaryEntryTeacherCommentsScreen = new Intent(getApplicationContext(), EditDiaryEntryTeacherCommentsActivity.class);
                EditDiaryEntryTeacherCommentsScreen.putExtra("diaryEntryId",diaryEntryId);
                startActivity(EditDiaryEntryTeacherCommentsScreen);
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