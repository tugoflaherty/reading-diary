package com.tugoflaherty.readingdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewReadingHistoryActivity extends AppCompatActivity {
    RecyclerView readingHistoryList;
    myDbAdapter helper;
    myHistoryAdapter readingHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reading_history);

        helper = new myDbAdapter(this);

        TextView searchTitle = (TextView) findViewById(R.id.view_reading_history_title);
        SearchView diaryEntriesSearch = (SearchView) findViewById(R.id.view_reading_history_search);
        ImageButton homepageNav = (ImageButton) findViewById(R.id.view_reading_history_navigation_button_home);
        ImageButton viewReadingHistoryNav = (ImageButton) findViewById(R.id.view_reading_history_navigation_button_history);
        ImageButton addDiaryEntryNav = (ImageButton) findViewById(R.id.view_reading_history_navigation_button_add);
        ImageButton settingsNav = (ImageButton) findViewById(R.id.view_reading_history_navigation_button_settings);
        TextView noRecords = (TextView) findViewById(R.id.view_reading_history_no_records_available);

        noRecords.setText("");
        searchTitle.setText("Click the Search icon to search records");

        String returnedData = helper.getDiaryEntryData();
        String[] readingHistoryDataArray = returnedData.split("`");
        List<String> readingHistoryData = new ArrayList<String>(Arrays.asList(readingHistoryDataArray));
        readingHistoryList = (RecyclerView) findViewById(R.id.view_reading_history_list);
        readingHistoryAdapter = new myHistoryAdapter(readingHistoryData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        readingHistoryList.setLayoutManager(layoutManager);
        readingHistoryList.setAdapter(readingHistoryAdapter);

        diaryEntriesSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchField) {

                List<String> filteredReadingHistoryData = new ArrayList<String>();
                for (String diaryEntry : readingHistoryData ) {
                    if (diaryEntry.toLowerCase().contains(searchField.trim().toLowerCase())) {
                        filteredReadingHistoryData.add(diaryEntry);
                    }
                }
                readingHistoryAdapter.setDataSet(filteredReadingHistoryData);
                readingHistoryAdapter.notifyDataSetChanged();
                return true;
            }
        });

        if (helper.getDiaryEntryData().equals(null) || helper.getDiaryEntryData().equals("")) {
            noRecords.setText("Add a new diary entry to view your reading history here");
            searchTitle.setText("No Diary Entry Records To Display");
            readingHistoryList.setVisibility(View.GONE);
            diaryEntriesSearch.setVisibility(View.GONE);
        }

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