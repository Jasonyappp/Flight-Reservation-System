package com.example.finalasignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class user_review_view extends AppCompatActivity {

    private List<ReviewData> reviewList;
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review_view);

        RecyclerView recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy review data for testing, replace it with your actual data
        reviewList = new ArrayList<>();
        reviewList.add(new ReviewData("John Doe", 4.5f, "Great app!"));

        adapter = new ReviewAdapter(this, reviewList);
        recyclerView.setAdapter(adapter);
    }
}
