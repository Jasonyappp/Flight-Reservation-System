package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        Intent intent = getIntent();
        if (intent.hasExtra("SelectedHistoryItem")) {
            String selectedHistoryItem = intent.getStringExtra("SelectedHistoryItem");

            TextView historyTextView = findViewById(R.id.historyTextView);
            historyTextView.setText(selectedHistoryItem);
        }
    }

    public void back_to_history (View view){
        Intent Back_to_History = new Intent(this, History.class);
        startActivity(Back_to_History);
    }
}