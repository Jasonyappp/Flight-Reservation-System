package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbManager = new dbmanager(this);
    }

    public void SendFavourite(View view) {
        Intent Favourite = new Intent(this, Favourites.class);
        startActivity(Favourite);

    }

    public void SendMyCard(View view) {
        Intent MyCard = new Intent(this, MyCard.class);
        startActivity(MyCard);
    }

    public void SendReview(View view) {
        Intent Review = new Intent(this, Review.class);
        startActivity(Review);
    }

    public void SendCustomerService(View view) {
        Intent CustomerService = new Intent(this, CustomerService.class);
        startActivity(CustomerService);
    }

    public void SendSetting(View view) {
        Intent Setting = new Intent(this, Setting.class);
        startActivity(Setting);
    }

    public void SendEditInformation(View view) {
        Intent Edit = new Intent(this, EditInformation.class);
        startActivity(Edit);
    }

    public void SendRating(View view) {
        Intent Rating = new Intent(this,Review.class);
        startActivity(Rating);
    }

    public void back_to_home (View view){
        Intent backtohomepage = new Intent(this, homepage.class);
        startActivity(backtohomepage);
    }

    public void gotohistory (View view){
        Intent gotohistory = new Intent(this, History.class);
        startActivity(gotohistory);
    }

    public void performProfileAction(View view) {
        dbManager.open();
        dbManager.insertAdminProfile("1", "admin_profile_pic_path");
        dbManager.close();

        dbManager.open();
        Cursor profileCursor = dbManager.fetchAdminProfile();
        dbManager.close();

        if (profileCursor != null && profileCursor.moveToFirst()) {
            int profileIdIndex = profileCursor.getColumnIndex(dbhelper.COLUMN_PROFILE_ID);
            int adminProfilePicIndex = profileCursor.getColumnIndex(dbhelper.COLUMN_ADMIN_PROFILE_PIC);

            if (profileIdIndex != -1 && adminProfilePicIndex != -1) {
                String profileId = profileCursor.getString(profileIdIndex);
                String adminProfilePic = profileCursor.getString(adminProfilePicIndex);

                Toast.makeText(this, "Profile ID: " + profileId + "\nAdmin Profile Pic: " + adminProfilePic, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid column index", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No profile data found", Toast.LENGTH_SHORT).show();
        }

        dbManager.open();
        int updatedRows = dbManager.updateUserProfile("1", "new_admin_profile_pic_path"); // Replace with actual data
        dbManager.close();

        if (updatedRows > 0) {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Profile update failed", Toast.LENGTH_SHORT).show();
        }

        dbManager.open();
        dbManager.deleteUserProfile("1");
        dbManager.close();

        Toast.makeText(this, "Profile deleted successfully", Toast.LENGTH_SHORT).show();
    }
}