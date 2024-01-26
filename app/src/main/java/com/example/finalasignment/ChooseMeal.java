package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChooseMeal extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosemeal);

        dbManager = new dbmanager(this).open();
    }

    public void BlackPaper(View view) {
        addToCart("Black Paper");
    }

    public void Egg_Crossant(View view) {
        addToCart("Egg Crossant");
    }

    public void CPC(View view) {
        addToCart("CPC");
    }

    public void OCKCP(View view) {
        addToCart("OCKCP");
    }

    public void Nasi_Lemak(View view) {
        addToCart("Nasi Lemak");
    }

    public void SCW(View view) {
        addToCart("SCW");
    }

    private void addToCart(String mealName) {
        dbManager.insertMeals(mealName);

        Intent intent = new Intent(this, My_cart.class);
        intent.putExtra("Meal", mealName);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    public void back_flight_Advance_Booking (View view){
        Intent back_to_flight_Advance_Booking = new Intent(this, advance_booking.class);
        startActivity(back_to_flight_Advance_Booking);
    }
}