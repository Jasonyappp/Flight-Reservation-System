package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalasignment.ChooseMeal;
import com.example.finalasignment.R;

import java.util.ArrayList;

public class My_cart extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);

        LinearLayout cartLayout = findViewById(R.id.linearLayout);

        dbmanager db = new dbmanager(this).open();
        Cursor cursor = db.fetchCart();

        if (cursor != null && cursor.moveToFirst()) {
            int foodNameIndex = cursor.getColumnIndex(dbhelper.COLUMN_FOOD_NAME);

            if (foodNameIndex != -1) {
                do {
                    String foodName = cursor.getString(foodNameIndex);

                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setText(foodName);

                    cartLayout.addView(textView);
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                Log.e("My_cart", "COLUMN_FOOD_NAME not found in the result set");
            }
        } else {
            TextView getFirstMeal = findViewById(R.id.textView15);
            getFirstMeal.setText("No meals selected");
        }
        db.close();
    }

    public void add_on_button(View view){
        Intent addon = new Intent(this, ChooseMeal.class);
        startActivity(addon);
    }

    public void go_to_confirm_booked (View view){
        Intent confirm_book = new Intent(this, confiembooking.class);
        startActivity(confirm_book);
    }
    public void cart_back(View view) {
        Intent back = new Intent(this, advance_booking.class);
        startActivity(back);
    }
}