package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    public void SendReview_Back(View view) {
        Intent Profile2 = new Intent(this, Profile.class);
        startActivity(Profile2);
    }

    public void SendReview_Submit(View view) {
        dbmanager dbManager = new dbmanager(this);

        String reviewID = "1";
        String reviewStar = "5";
        String comments = "Great experience!";

        dbManager.open();
        dbManager.insertReview(reviewID, reviewStar, comments);
        dbManager.close();

        Intent Review = new Intent(this, ReviewSubmit.class);
        startActivity(Review);
    }
}