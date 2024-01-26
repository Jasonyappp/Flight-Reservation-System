package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ticketselection extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketselection);

        dbManager = new dbmanager(this);
        dbManager.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    private void handleTicketSelection(String ticketType) {
        String username = "your_username_here";
        String password = "placeholder_password";

        Cursor userCursor = dbManager.fetchSignIn(username ,password);
        Cursor cartCursor = dbManager.fetchCart();

        if (userCursor != null && userCursor.moveToFirst()) {
            int columnIndexUsername = userCursor.getColumnIndex(dbhelper.COLUMN_USERNAME);

            if (columnIndexUsername != -1) {
                String fetchedUsername = userCursor.getString(columnIndexUsername);

                if (username.equals(fetchedUsername)) {
                    dbManager.insertCart("cart_id_placeholder", ticketType, "option_placeholder");
                    showToast("Ticket added to cart successfully!");
                } else {
                    showToast("Please sign in to select tickets.");
                }
            } else {
                showToast("Error: Username column not found in the result set.");
            }
        }

        if (userCursor != null && !userCursor.isClosed()) {
            userCursor.close();
        }

        if (cartCursor != null && !cartCursor.isClosed()) {
            cartCursor.close();
        }
        Intent intent = new Intent(this, confiembooking.class);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void SendTicket_Select1(View view) {
        handleTicketSelection("Ticket_Type_1");
    }

    public void SendTicket_Select2(View view) {
        handleTicketSelection("Ticket_Type_2");
    }

    public void SendTicket_Select3(View view) {
        handleTicketSelection("Ticket_Type_3");
    }

    public void BackTicket_Button(View view) {
        Intent TicketBack = new Intent(this, advance_booking.class);
        startActivity(TicketBack);
    }

    public void backtohomepage(View view){
        Intent backtohome = new Intent(this, homepage.class);
        startActivity(backtohome);
    }
}