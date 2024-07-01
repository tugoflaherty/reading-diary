package com.tugoflaherty.readingdiary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditDiaryEntryInformationActivity extends AppCompatActivity {
    EditText bookTitleInputField, bookAuthorInputField, pageCountInputField, startPageInputField, endPageInputField;
    TextView readingStartOutput, readingEndOutput;
    Button readingStartInputField, readingEndInputField;
    myDbAdapter helper;
    String diaryEntryId, uid,readingStart,readingEnd,bookTitle,bookAuthor,pageCount,startPage,endPage,enjoymentRating,pupilComments,readingAbility,parentComments,readingProgress,teacherComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry_information);

        diaryEntryId = getIntent().getStringExtra("diaryEntryId");
        helper = new myDbAdapter(this);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("EEEE dd/MM/yyyy hh:mm");

        readingStartOutput = (TextView) findViewById(R.id.edit_diary_entry_information_reading_information_start_date_time_help);
        readingEndOutput = (TextView) findViewById(R.id.edit_diary_entry_information_reading_information_end_date_time_help);
        readingStartInputField = (Button) findViewById(R.id.edit_reading_start_datetime_input);
        readingEndInputField = (Button) findViewById(R.id.edit_reading_end_datetime_input);
        bookTitleInputField = (EditText) findViewById(R.id.edit_book_title_input);
        bookAuthorInputField = (EditText) findViewById(R.id.edit_book_author_input);
        pageCountInputField = (EditText) findViewById(R.id.edit_page_count_input);
        startPageInputField = (EditText) findViewById(R.id.edit_start_page_input);
        endPageInputField = (EditText) findViewById(R.id.edit_end_page_input);
        Button cancel = (Button) findViewById(R.id.edit_diary_entry_information_button_cancel);
        Button save = (Button) findViewById(R.id.edit_diary_entry_information_button_save);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.edit_diary_entry_information_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.edit_diary_entry_information_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.edit_diary_entry_information_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.edit_diary_entry_information_navigation_button_settings);

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

            readingStartOutput.setText(readingStart);
            readingEndOutput.setText(readingEnd);
            bookTitleInputField.setText(bookTitle);
            bookAuthorInputField.setText(bookAuthor);
            pageCountInputField.setText(pageCount);
            startPageInputField.setText(startPage);
            endPageInputField.setText(endPage);
        }

        readingStartInputField.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar currentDateTime = Calendar.getInstance();
                Calendar startDateTime = Calendar.getInstance();
                new DatePickerDialog(EditDiaryEntryInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int startYear, int startMonth, int startDay) {
                        startDateTime.set(startYear, startMonth, startDay);
                        new TimePickerDialog(EditDiaryEntryInformationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int startHour, int startMinute) {
                                startDateTime.set(Calendar.HOUR_OF_DAY, startHour);
                                startDateTime.set(Calendar.MINUTE, startMinute);
                                readingStartOutput.setText(dateTimeFormat.format(startDateTime.getTime()));
                            }
                        }, currentDateTime.get(Calendar.HOUR_OF_DAY), currentDateTime.get(Calendar.MINUTE), true).show();
                    }
                }, currentDateTime.get(Calendar.YEAR), currentDateTime.get(Calendar.MONTH), currentDateTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        readingEndInputField.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar currentDateTime = Calendar.getInstance();
                Calendar endDateTime = Calendar.getInstance();
                new DatePickerDialog(EditDiaryEntryInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int startYear, int startMonth, int startDay) {
                        endDateTime.set(startYear, startMonth, startDay);
                        new TimePickerDialog(EditDiaryEntryInformationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int startHour, int startMinute) {
                                endDateTime.set(Calendar.HOUR_OF_DAY, startHour);
                                endDateTime.set(Calendar.MINUTE, startMinute);
                                readingEndOutput.setText(dateTimeFormat.format(endDateTime.getTime()));
                            }
                        }, currentDateTime.get(Calendar.HOUR_OF_DAY), currentDateTime.get(Calendar.MINUTE), true).show();
                    }
                }, currentDateTime.get(Calendar.YEAR), currentDateTime.get(Calendar.MONTH), currentDateTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
        String readingStart = readingStartOutput.getText().toString();
        String readingEnd = readingEndOutput.getText().toString();
        String bookTitle = bookTitleInputField.getText().toString();
        String bookAuthor = bookAuthorInputField.getText().toString();
        String pageCountString = pageCountInputField.getText().toString();
        String startPageString = startPageInputField.getText().toString();
        String endPageString = endPageInputField.getText().toString();
        int pageCount = 0, startPage = 0, endPage = 0;

        if (readingStart.equals(R.string.diary_entry_information_reading_information_start_date_time_help)) {
            readingStartOutput.setTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Select Reading Start Date/Time");
            fieldsCompleted = false;
        }

        if (readingEnd.equals(R.string.diary_entry_information_reading_information_end_date_time_help)) {
            readingEndOutput.setTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Select Reading End Date/Time");
            fieldsCompleted = false;
        }

        if ((bookTitle.equals(null)) || (bookTitle.equals(""))) {
            bookTitleInputField.setHintTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Enter Book Title");
            fieldsCompleted = false;
        }

        if ((bookAuthor.equals(null)) || (bookAuthor.equals(""))) {
            bookAuthorInputField.setHintTextColor(getResources().getColor(R.color.red));
            Message.message(getApplicationContext(),"Enter Book Author");
            fieldsCompleted = false;
        }

        if ((!pageCountString.equals(null)) && (!pageCountString.equals(""))) {
            try {
                pageCount = Integer.parseInt(pageCountString);
                if (pageCount < 1) {
                    endPageInputField.setTextColor(getResources().getColor(R.color.red));
                    pageCountInputField.setHintTextColor(getResources().getColor(R.color.red));
                    Message.message(getApplicationContext(),"Page Count Must Be Greater Than 1");
                    fieldsCompleted = false;
                }
            } catch (Exception e) {
                endPageInputField.setTextColor(getResources().getColor(R.color.red));
                pageCountInputField.setHintTextColor(getResources().getColor(R.color.red));
                Message.message(getApplicationContext(),"Page Count Must Be A Number");
                fieldsCompleted = false;
            }
        }

        if ((!startPageString.equals(null)) && (!startPageString.equals(""))) {
            try {
                startPage = Integer.parseInt(startPageString);
                if ((startPage < 1) || (startPage > pageCount)) {
                    endPageInputField.setTextColor(getResources().getColor(R.color.red));
                    startPageInputField.setHintTextColor(getResources().getColor(R.color.red));
                    Message.message(getApplicationContext(),"Start Page Must Be Greater Than 1 And Less Than Page Count");
                    fieldsCompleted = false;
                }
            } catch (Exception e) {
                endPageInputField.setTextColor(getResources().getColor(R.color.red));
                startPageInputField.setHintTextColor(getResources().getColor(R.color.red));
                Message.message(getApplicationContext(),"Start Page Must Be A Number");
                fieldsCompleted = false;
            }
        }

        if ((!endPageString.equals(null)) && (!endPageString.equals(""))) {
            try {
                endPage = Integer.parseInt(endPageString);
                if ((endPage < 1) || (endPage > pageCount) || (endPage < startPage)) {
                    endPageInputField.setTextColor(getResources().getColor(R.color.red));
                    endPageInputField.setHintTextColor(getResources().getColor(R.color.red));
                    Message.message(getApplicationContext(),"End Page Must Be Greater Than 1, Less Than Page Count And Greater Than Start Page");
                    fieldsCompleted = false;
                }
            } catch (Exception e) {
                endPageInputField.setTextColor(getResources().getColor(R.color.red));
                endPageInputField.setHintTextColor(getResources().getColor(R.color.red));
                Message.message(getApplicationContext(),"End Page Must Be A Number");
                fieldsCompleted = false;
            }
        }

        if (fieldsCompleted == true) {
            String pageCountValue = String.valueOf(pageCount);
            String startPageValue = String.valueOf(startPage);
            String endPageValue = String.valueOf(endPage);
            int count = helper.updateDiaryEntry(uid,readingStart,readingEnd,bookTitle,bookAuthor,pageCountValue,startPageValue,endPageValue,enjoymentRating,pupilComments,readingAbility,parentComments,readingProgress,teacherComments,"1");
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