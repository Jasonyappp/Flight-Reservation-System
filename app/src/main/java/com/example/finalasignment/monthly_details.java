package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class monthly_details extends AppCompatActivity {

    private LineChart lineChart;
    private List<String> xValues;
    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_details);

        dbManager = new dbmanager(this);

        ImageView back_button = findViewById(R.id.back_image4);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_action = new Intent(monthly_details.this, Monthly_statistics.class);
                startActivity(back_action);
            }
        });

        lineChart = findViewById(R.id.MonthlyLineChart1);
        lineChart.setDoubleTapToZoomEnabled(false);

        Description description1 = new Description();
        description1.setText("Booked Flight");
        description1.setPosition(150f, 15f);
        lineChart.setDescription(description1);
        lineChart.getAxisRight().setDrawLabels(false);

        xValues = Arrays.asList("Sep", "Oct", "Nov", "Dec");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(3);
        xAxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(1000f);
        yAxis.setZeroLineWidth(100f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        List<Entry> entries1 = fetchMonthlyStatistics();

        LineDataSet dataSet1 = new LineDataSet(entries1, "Booked Flight");
        dataSet1.setColor(Color.BLUE);

        LineData lineData1 = new LineData(dataSet1);
        lineChart.setData(lineData1);
        lineChart.invalidate();
    }

    private List<Entry> fetchMonthlyStatistics() {
        List<Entry> entries = new ArrayList<>();

        dbManager.open();

        String[] columns = new String[]{"month_id", "monthly_bookedFlight"};

        Cursor cursor = dbManager.database.query(dbhelper.TABLE_MONTHLY_STATISTIC, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int monthly_bookedFlightIndex = cursor.getColumnIndex("monthly_bookedFlight");

                entries.add(new Entry(cursor.getPosition(), cursor.getFloat(monthly_bookedFlightIndex)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        dbManager.close();

        return entries;
    }
}