package com.example.finalasignment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class admin_userView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_view);

        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy user data for testing, replace it with your actual data
        List<User> userList = new ArrayList<>();
        userList.add(new User("User1", "123456789", "01-01-1990", "user1@example.com", "1234567890", "password1"));
        userList.add(new User("User2", "987654321", "02-02-1995", "user2@example.com", "9876543210", "password2"));

        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);
    }
}
