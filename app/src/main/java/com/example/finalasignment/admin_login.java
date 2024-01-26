package com.example.finalasignment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login);
        dbManager = new dbmanager(this);
    }

    public void identity_comfirm(View view) {
        EditText admin_username = findViewById(R.id.username_textfield);
        EditText admin_password = findViewById(R.id.edit_password);

        String username = admin_username.getText().toString();
        String password = admin_password.getText().toString();

        if (username.isEmpty()) {
            admin_username.setError("Admin_Username is required!");
            return;
        }

        if (password.isEmpty()) {
            admin_password.setError("Admin_Password is required!");
            return;
        }

        Cursor cursor = dbManager.fetchAdminLogin(username, password);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                Toast.makeText(admin_login.this, "Login successful. Welcome!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(admin_login.this, identity_comfirm.class);
                startActivity(intent);

                finish();
            } else {
                Toast.makeText(admin_login.this, "Invalid Admin username or password. Please try again.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(admin_login.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void Image_back_Butt(View view) {
        Intent back_button = new Intent(this, identity_select.class);
        startActivity(back_button);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}


