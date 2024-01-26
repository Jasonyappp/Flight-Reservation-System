package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class identity_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_select);
    }

    public void userButton (View view){
        Intent userLogin = new Intent(this, MainActivity.class);
        startActivity(userLogin);
    }

    public void adminButton (View view){
        Intent adminLogin = new Intent(this, admin_login.class);
        startActivity(adminLogin);
    }
}