package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class booking_flight extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_flight);

        dbManager = new dbmanager(this);

        displayBookingInformation();
    }

    private void displayBookingInformation() {
        Cursor cursor;
        try {
            dbManager.open();
            cursor = dbManager.fetchBooking();

            if (cursor != null && cursor.moveToFirst()) {
                int airportIndex = cursor.getColumnIndex(dbhelper.COLUMN_AIRPORT);
                int destinationIndex = cursor.getColumnIndex(dbhelper.COLUMN_DESTINATION_COUNTRY);
                int paymentMethodIndex = cursor.getColumnIndex(dbhelper.COLUMN_PAYMENT_METHOD);
                int flightDateIndex = cursor.getColumnIndex(dbhelper.COLUMN_FLIGHT_DATE);

                if (airportIndex != -1 && destinationIndex != -1 && paymentMethodIndex != -1 && flightDateIndex != -1) {
                    String airport = cursor.getString(airportIndex);
                    String destination = cursor.getString(destinationIndex);
                    String paymentMethod = cursor.getString(paymentMethodIndex);
                    String flightDate = cursor.getString(flightDateIndex);

                    TextView airportTextView = findViewById(R.id.hotel);
                    TextView destinationTextView = findViewById(R.id.hotel_location);
                    TextView paymentMethodTextView = findViewById(R.id.payment_method);
                    TextView flightDateTextView = findViewById(R.id.date_booking);

                    airportTextView.setText("Airport: " + airport);
                    destinationTextView.setText("Destination: " + destination);
                    paymentMethodTextView.setText("Payment Method: " + paymentMethod);
                    flightDateTextView.setText("Flight Date: " + flightDate);
                } else {
                    Toast.makeText(this, "No booking information found", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void cancelBooking (View view){
            String bookingId = "123456";

            dbmanager db = new dbmanager(this);
            db.open();

            db.deleteBooking(bookingId);
            db.deleteSeats(bookingId);
            db.deleteFoodOptions(bookingId);

            db.close();

            Toast.makeText(this, "Booking canceled successfully", Toast.LENGTH_SHORT).show();
        }

        public void backtoprofile (View view){
            Intent toprofile = new Intent(this, Profile.class);
            startActivity(toprofile);
        }
    }
