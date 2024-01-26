package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView historyList = findViewById(R.id.history_list);

        Intent intent = getIntent();

        if (intent.hasExtra("History")) {
            List<String> dataList = intent.getStringArrayListExtra("History");

            if (dataList != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
                historyList.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "Data List not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            fetchHistoryFromDatabase(historyList);
            Toast.makeText(getApplicationContext(), "History not found", Toast.LENGTH_SHORT).show();
        }

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                Intent viewHistoryIntent = new Intent(History.this, ViewHistory.class);

                viewHistoryIntent.putExtra("SelectedHistoryItem", selectedItem);

                startActivity(viewHistoryIntent);
            }
        });

        dbManager = new dbmanager(this);

        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchHistoryFromDatabase(ListView historyList) {
        Cursor cursor = dbManager.fetchBooking();

        if (cursor != null && cursor.moveToFirst()) {
            String[] columnNames = cursor.getColumnNames();
            Log.d("ColumnNames", Arrays.toString(columnNames));

            List<String> historyDataList = new ArrayList<>();

            try {
                int airportIndex = cursor.getColumnIndexOrThrow(dbhelper.COLUMN_AIRPORT);
                int destinationCountryIndex = cursor.getColumnIndexOrThrow(dbhelper.COLUMN_DESTINATION_COUNTRY);
                int flightDateIndex = cursor.getColumnIndexOrThrow(dbhelper.COLUMN_FLIGHT_DATE);

                do {
                    String airport = cursor.getString(airportIndex);
                    String destinationCountry = cursor.getString(destinationCountryIndex);
                    String flightDate = cursor.getString(flightDateIndex);

                    String historyEntry = "Airport: " + airport + "\nDestination: " + destinationCountry + "\nFlight Date: " + flightDate;
                    historyDataList.add(historyEntry);
                } while (cursor.moveToNext());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyDataList);
                historyList.setAdapter(adapter);
            } catch (IllegalArgumentException e) {
                Log.e("FetchHistory", "Error retrieving column index: " + e.getMessage());
            } finally {
                cursor.close();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No booking history found", Toast.LENGTH_SHORT).show();
        }
    }

    public void backtoprofile (View view){
        Intent backtoprofile = new Intent(this, Profile.class);
        startActivity(backtoprofile);
    }

}