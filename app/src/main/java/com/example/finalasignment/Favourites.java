package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Favourites extends AppCompatActivity {

    private dbmanager dbManager;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        ListView favourite = findViewById(R.id.Favourite);

        dbManager = new dbmanager(this);
        dbManager.open();

        Intent ProfileFavourite = getIntent();
        List<String> dataList = ProfileFavourite.getStringArrayListExtra("Favourite");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList);
        favourite.setAdapter(adapter);

        Button addFavoriteButton = findViewById(R.id.btnAddFavorite);
        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavorite(1, "1234567890123456", "12/23", "123");
                updateListView();
            }
        });

        Button removeFavoriteButton = findViewById(R.id.btnRemoveFavorite);
        removeFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFavorite(1);
                updateListView();
            }
        });
    }

    private void updateListView() {
        Cursor cursor = getFavorites();
        if (cursor != null) {
            List<String> newDataList = new ArrayList<>();
            int cardNumberIndex = cursor.getColumnIndex(dbhelper.COLUMN_CARD_NUMBER);
            int expiryDateIndex = cursor.getColumnIndex(dbhelper.COLUMN_EXPIRY_DATE);
            int cvvIndex = cursor.getColumnIndex(dbhelper.COLUMN_CVV);

            while (cursor.moveToNext()) {
                String cardNumber = (cardNumberIndex != -1) ? cursor.getString(cardNumberIndex) : "N/A";
                String expiryDate = (expiryDateIndex != -1) ? cursor.getString(expiryDateIndex) : "N/A";
                String cvv = (cvvIndex != -1) ? cursor.getString(cvvIndex) : "N/A";

                newDataList.add("Card Number: " + cardNumber + "\nExpiry Date: " + expiryDate + "\nCVV: " + cvv);
            }
            adapter.clear();
            adapter.addAll(newDataList);
            adapter.notifyDataSetChanged();
            cursor.close();
        }
    }

    public void SendFavouritesBack(View view) {
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
        Toast.makeText(this, "Favourites sent back to Profile", Toast.LENGTH_SHORT).show();
    }

    public void addFavorite(int Favrouite_ID, String cardNumber, String expiryDate, String CVV) {
        try {
            dbManager.insertFavourite(Favrouite_ID, cardNumber, expiryDate, CVV);
            Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error adding to Favourites", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getFavorites() {
        try {
            return dbManager.fetchFavourite();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeFavorite(int favrouiteId) {
        try {
            dbManager.deleteFavourite(favrouiteId);
            Toast.makeText(this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error removing from Favourites", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}