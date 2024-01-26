package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new dbmanager(this);
        dbManager.open();

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    public void SendSignIn(View view) {
        EditText LoginUser = findViewById(R.id.edit_loginuser);
        EditText LoginPassword = findViewById(R.id.edit_loginpassword);

        String enteredUsername = LoginUser.getText().toString();
        String enteredPassword = LoginPassword.getText().toString();

        if (enteredUsername.isEmpty()) {
            LoginUser.setError("Username/Email is required!");
            return;
        }

        if (enteredPassword.isEmpty()) {
            LoginPassword.setError("Password is required!");
            return;
        }

        Cursor cursor = dbManager.fetchSignIn(enteredUsername, enteredPassword);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                int usernameIndex = cursor.getColumnIndex(dbhelper.COLUMN_USERNAME);
                int passwordIndex = cursor.getColumnIndex(dbhelper.COLUMN_PASSWORD);

                if (usernameIndex != -1 && passwordIndex != -1) {
                    do {
                        String storedUsername = cursor.getString(usernameIndex);
                        String storedPassword = cursor.getString(passwordIndex);
                        if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                            Toast.makeText(MainActivity.this, "Login successful. Welcome!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, homepage.class);
                            startActivity(intent);

                            finish();

                            return;
                        }
                    } while (cursor.moveToNext());
                } else {
                        Toast.makeText(MainActivity.this, "Invalid column index. Please check the database.", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Invalid username/email or password. Please try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "No user data found. Please sign up.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public void SendSignUp(View view) {

        Intent signUp = new Intent(this, MainActivity2.class);
        startActivity(signUp);

    }
    public void SendForgotPasswd(View view) {
        Intent forgotpasswd = new Intent(this, ForgetPassword.class);
        startActivity(forgotpasswd);
    }
}





