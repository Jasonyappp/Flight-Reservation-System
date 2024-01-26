package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class yearly_details extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearly_details);

        dbManager = new dbmanager(this);
        dbManager.open();

        Cursor yearlyDetailsCursor = fetchYearlyDetails("2023");

        if (yearlyDetailsCursor != null && yearlyDetailsCursor.moveToFirst()) {
            int bookedFlightIndex = yearlyDetailsCursor.getColumnIndex(dbhelper.COLUMN_YEARLY_BOOKED_FLIGHT);
            int cancelActivitiesIndex = yearlyDetailsCursor.getColumnIndex(dbhelper.COLUMN_YEARLY_CANCEL_ACTIVITIES);

            if (bookedFlightIndex != -1 && cancelActivitiesIndex != -1) {
                String yearly_bookedFlight = yearlyDetailsCursor.getString(bookedFlightIndex);
                String yearly_cancelActivities = yearlyDetailsCursor.getString(cancelActivitiesIndex);

                Toast.makeText(this, "Yearly Booked Flight: " + yearly_bookedFlight, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Yearly Cancel Activities: " + yearly_cancelActivities, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Columns not found in the cursor", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No yearly details found", Toast.LENGTH_SHORT).show();
        }
        dbManager.close();

        ImageView backButton = findViewById(R.id.back_image6);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backAction = new Intent(yearly_details.this, yearly_statistic.class);
                startActivity(backAction);
            }
        });

        PieChart pieChart = findViewById(R.id.yearlyPieChart1);

        ArrayList<PieEntry> entries1 = new ArrayList<>();
        entries1.add(new PieEntry(80f,"Flight"));
        entries1.add(new PieEntry(90f,"Hotel"));
        entries1.add(new PieEntry(75f,"Activities"));
        entries1.add(new PieEntry(20f,"Cancel"));

        PieDataSet pieDataSet = new PieDataSet(entries1, "Booking Activities");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
        pieChart.invalidate();

        PieChart pieChart2 = findViewById(R.id.yearlyPieChart2);

        ArrayList<PieEntry> entries2 = new ArrayList<>();
        entries2.add(new PieEntry(80f,"Flight"));
        entries2.add(new PieEntry(90f,"Hotel"));
        entries2.add(new PieEntry(75f,"Activities"));
        entries2.add(new PieEntry(20f,"Cancel"));

        PieDataSet pieDataSet2 = new PieDataSet(entries2, "Booking Activities");
        pieDataSet2.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData2 = new PieData(pieDataSet2);
        pieChart2.setData(pieData2);

        pieChart2.getDescription().setEnabled(false);
        pieChart2.animateY(1000);
        pieChart2.invalidate();

        PieChart pieChart3 = findViewById(R.id.yearlyPieChart3);

        ArrayList<PieEntry> entries3 = new ArrayList<>();
        entries3.add(new PieEntry(80f,"Flight"));
        entries3.add(new PieEntry(90f,"Hotel"));
        entries3.add(new PieEntry(75f,"Activities"));
        entries3.add(new PieEntry(20f,"Cancel"));

        PieDataSet pieDataSet3 = new PieDataSet(entries3, "Booking Activities");
        pieDataSet3.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData3 = new PieData(pieDataSet3);
        pieChart3.setData(pieData3);

        pieChart3.getDescription().setEnabled(false);
        pieChart3.animateY(1000);
        pieChart3.invalidate();
    }

    private Cursor fetchYearlyDetails(String year) {
        return dbManager.fetchYearlyStatistic();
    }
}