package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class yearly_statistic extends AppCompatActivity {

    private dbmanager dbManager;
    private List<String> xValues = Arrays.asList("2020", "2021", "2022");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearly_statistic);

        dbManager = new dbmanager(this);
        dbManager.open();

        ImageView backButton = findViewById(R.id.back_image5);
        ImageView yearlyDetail_button = findViewById(R.id.yearlyDetails_button);
        yearlyDetail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick2(v);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick1(v);
            }
        });

        BarChart barChart = findViewById(R.id.yearlyStatisticChart);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.setDoubleTapToZoomEnabled(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(2020, new float[]{200f, 300f, 100f}));  // Year 2020
        entries.add(new BarEntry(2021, new float[]{150f, 250f, 100f}));  // Year 2021
        entries.add(new BarEntry(2022, new float[]{250f, 400f, 150f}));  // Year 2022

        BarDataSet dataSet = new BarDataSet(entries, "Yearly Booked Activities");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setStackLabels(new String[]{"Booked Flight", "Booked Hotel", "Booked Activities"});

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(1000f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(10f);
        legend.setTextSize(12f);
        legend.setXEntrySpace(5f);

        Cursor yearlyStatsCursor = dbManager.fetchYearlyStatistic();

        if (yearlyStatsCursor != null && yearlyStatsCursor.moveToFirst()) {
            int bookedFlightIndex = yearlyStatsCursor.getColumnIndex(dbhelper.COLUMN_YEARLY_BOOKED_FLIGHT);
            int cancelActivitiesIndex = yearlyStatsCursor.getColumnIndex(dbhelper.COLUMN_YEARLY_CANCEL_ACTIVITIES);

            if (bookedFlightIndex != -1 && cancelActivitiesIndex != -1) {
                String yearly_bookedFlight = yearlyStatsCursor.getString(bookedFlightIndex);
                String yearly_cancelActivities = yearlyStatsCursor.getString(cancelActivitiesIndex);

                Toast.makeText(this, "Yearly Booked Flight (2022): " + yearly_bookedFlight, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Yearly Cancel Activities (2022): " + yearly_cancelActivities, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid column indices", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No yearly statistics found for the specified year", Toast.LENGTH_SHORT).show();
        }

        dbManager.close();
    }

    public void onImageClick1(View view){
        Intent backAction = new Intent(yearly_statistic.this, admin_profile.class);
        startActivity(backAction);
    }
    public void onImageClick2(View view){
        Intent nextAction = new Intent(yearly_statistic.this, yearly_details.class);
        startActivity(nextAction);
    }
}