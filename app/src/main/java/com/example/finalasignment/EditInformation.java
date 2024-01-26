package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditInformation extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);

        dbManager = new dbmanager(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SendEditPortrait(View view) {
        Intent EditPortrait = new Intent(this, Portrait.class);
        startActivity(EditPortrait);
    }

    public void SendEditInformation_save(View view) {

        Intent P = new Intent(this, Profile.class);

        EditText editUsername = findViewById(R.id.editTextText);
        P.putExtra("Username",editUsername.getText().toString());

        EditText editIcNumber = findViewById(R.id.Ic_number);
        P.putExtra("IC Number",editIcNumber.getText().toString());

        EditText editDateBirth = findViewById(R.id.editBirth);
        P.putExtra("Date of Birth",editDateBirth.getText().toString());

        EditText editEmail = findViewById(R.id.editTextText2);
        P.putExtra("Email",editEmail.getText().toString());

        EditText editPass = findViewById(R.id.editTextTextPassword);
        P.putExtra("Password", editPass.getText().toString());

        EditText editConfirmPass = findViewById(R.id.editTextTextPassword2);
        P.putExtra("Confirm Password", editConfirmPass.getText().toString());

        EditText editEmergencyName = findViewById(R.id.editTextText4);
        P.putExtra("Emergency Contact Name", editEmergencyName.getText().toString());

        EditText editEmergencyPhone = findViewById(R.id.editTextNumber4);
        P.putExtra("Emergency Contact Phone", editEmergencyPhone.getText().toString());

        String username = "current_username";

        dbManager.updateRegister(
                username,
                editIcNumber.getText().toString(),
                editDateBirth.getText().toString(),
                editEmail.getText().toString(),
                editEmergencyPhone.getText().toString(),
                editPass.getText().toString(),
                editConfirmPass.getText().toString()
        );

        showToast("User information updated successfully");

        dbManager.close();
        startActivity(P);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}