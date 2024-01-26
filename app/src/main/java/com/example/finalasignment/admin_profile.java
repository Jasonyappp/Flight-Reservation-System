package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class admin_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        ImageView toUserView = findViewById(R.id.user_icon_button);
        toUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserView = new Intent(admin_profile.this,admin_userView.class);
                startActivity(UserView);
            }
        });

        ImageView toReviewView = findViewById(R.id.review_icon_button);
        toReviewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ReviewView = new Intent(admin_profile.this, user_review_view.class);
                startActivity(ReviewView);
            }
        });
    }
}