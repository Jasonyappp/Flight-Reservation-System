package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        dbManager = new dbmanager(this);
    }

    public void SendForgotPasswd_Back(View view) {
        Intent forgotpasswd_back = new Intent(this, MainActivity.class);
        startActivity(forgotpasswd_back);
    }

    public void SendForgotPasswd_Confirm(View view) {

        EditText Email = findViewById(R.id.editTextTextEmailAddress);
        EditText NewPassword = findViewById(R.id.editTextTextPassword3);
        EditText ConfirmNewPassword = findViewById(R.id.editTextTextPassword4);

        dbManager.open();

        String email = Email.getText().toString();
        String newPassword = NewPassword.getText().toString();
        String confirmNewPassword = ConfirmNewPassword.getText().toString();

        if (email.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else {
            Cursor cursor = dbManager.fetchForgotPassword(email);

            if (cursor != null && cursor.moveToFirst()) {
                dbManager.updateForgotPassword(email, newPassword, confirmNewPassword);
                Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
            }

            if (cursor != null) {
                cursor.close();
            }
        } dbManager.close();
    }
}

