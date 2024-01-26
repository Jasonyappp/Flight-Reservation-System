package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;

public class identity_comfirm extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_comfirm);

        dbManager = new dbmanager(this);
        dbManager.open();

        ImageView back = findViewById(R.id.back_image2);

        Button continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText identityConfirmEditText = findViewById(R.id.identityComfirm_edit);
                String identityConfirmation = identityConfirmEditText.getText().toString();

                confirmIdentityWithCode(identityConfirmation);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick(v);
            }
        });
    }

    public void onImageClick(View view) {
        Intent backAction = new Intent(identity_comfirm.this, admin_login.class);
        startActivity(backAction);
    }

    private void confirmIdentityWithCode(String enteredCode) {
        Cursor cursor = dbManager.fetchAdminPasscode();

        if (cursor != null && cursor.moveToFirst()) {
            int passcodeColumnIndex = cursor.getColumnIndex(dbhelper.COLUMN_PASSCODE);

            if (passcodeColumnIndex >= 0) {
                String storedCode = cursor.getString(passcodeColumnIndex);

                if (enteredCode.equals(storedCode)) {
                    Toast.makeText(identity_comfirm.this, "The code is valid, welcome!", Toast.LENGTH_SHORT).show();
                    Intent continueAction = new Intent(identity_comfirm.this, admin_profile.class);
                    startActivity(continueAction);
                } else {
                    Toast.makeText(identity_comfirm.this, "Invalid code. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(identity_comfirm.this, "Passcode column not found. Please contact support.", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        } else {
            Toast.makeText(identity_comfirm.this, "Passcode not found. Please contact support.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
