package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReviewSubmit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_submit);
    }

    public void SendReviewSubmit_Back(View view) {
        Intent ReviewSubmit = new Intent(this, Profile.class);
        startActivity(ReviewSubmit);
    }
}