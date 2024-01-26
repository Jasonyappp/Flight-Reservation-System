package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbManager = new dbmanager(this);
        dbManager.open();
    }

    public void SendRegisterConfirm(View view) {

        EditText RegisterUsername = findViewById(R.id.edit_registerUsername);
        EditText RegisterIC = findViewById(R.id.edit_registerIC);
        EditText RegisterBirth = findViewById(R.id.edit_registerBirth);
        EditText RegisterEmail = findViewById(R.id.edit_registerEmail);
        EditText RegisterPhone = findViewById(R.id.edit_registerPhone);
        EditText RegisterPassword = findViewById(R.id.edit_registerPassword);
        EditText RegisterConfirmPassword = findViewById(R.id.edit_registerConfirmPassword);

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("Username", RegisterUsername.getText().toString());
        intent.putExtra("IC", RegisterIC.getText().toString());
        intent.putExtra("Date Of Birth", RegisterBirth.getText().toString());
        intent.putExtra("Email Address", RegisterEmail.getText().toString());
        intent.putExtra("Phone Number", RegisterPhone.getText().toString());
        intent.putExtra("Password", RegisterPassword.getText().toString());
        intent.putExtra("Confirm Password", RegisterConfirmPassword.getText().toString());

        if (RegisterUsername.getText().toString().isEmpty()) {
            RegisterUsername.setError("Username is required!");
            return;
        }
        if (RegisterIC.getText().toString().isEmpty()) {
            RegisterIC.setError("IC is required!");
            return;
        }
        if (RegisterBirth.getText().toString().isEmpty()) {
            RegisterBirth.setError("Date of Birth is required!");
            return;
        }
        if (RegisterEmail.getText().toString().isEmpty()) {
            RegisterEmail.setError("Email is required!");
            return;
        }
        if (RegisterPhone.getText().toString().isEmpty()) {
            RegisterPhone.setError("Phone Number is required!");
            return;
        }
        if (RegisterPassword.getText().toString().isEmpty()) {
            RegisterPassword.setError("Password is required!");
            return;
        }
        if (RegisterConfirmPassword.getText().toString().isEmpty()) {
            RegisterConfirmPassword.setError("Confirm Password is required!");
            return;
        }

        if (RegisterUsername.getError() == null && RegisterIC.getError() == null && RegisterBirth.getError() == null
                && RegisterEmail.getError() == null && RegisterPhone.getError() == null
                && RegisterPassword.getError() == null && RegisterConfirmPassword.getError() == null) {

            dbManager.insertRegister(
                    RegisterUsername.getText().toString(),
                    RegisterIC.getText().toString(),
                    RegisterBirth.getText().toString(),
                    RegisterEmail.getText().toString(),
                    RegisterPhone.getText().toString(),
                    RegisterPassword.getText().toString(),
                    RegisterConfirmPassword.getText().toString()
            );

            Intent successIntent  = new Intent(this, MainActivity.class);
            startActivity(successIntent );
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
        }
    }

    public void SendRegisterBack (View view){
        Intent Back_login_page = new Intent(this, MainActivity.class);
        startActivity(Back_login_page);
    }
}