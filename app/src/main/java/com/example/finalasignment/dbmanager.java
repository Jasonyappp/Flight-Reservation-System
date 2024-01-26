package com.example.finalasignment;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class dbmanager {

    private dbhelper dbHelper;

    private Context context;

    public SQLiteDatabase database;

    public dbmanager(Context c) {
        context = c;
    }

    public dbmanager open() throws SQLException {
        dbHelper = new dbhelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Register
    public void insertRegister(String username, String icNo, String dateOfBirth, String emailAddress, String phoneNumber, String password, String confirmPassword) {
        if (!isUsernameExists(username)) {
            ContentValues contentValue = new ContentValues();
            contentValue.put(dbhelper.COLUMN_USERNAME, username);
            contentValue.put(dbhelper.COLUMN_IC_NO, icNo);
            contentValue.put(dbhelper.COLUMN_DATE_OF_BIRTH, dateOfBirth);
            contentValue.put(dbhelper.COLUMN_EMAIL_ADDRESS, emailAddress);
            contentValue.put(dbhelper.COLUMN_PHONE_NUMBER, phoneNumber);
            contentValue.put(dbhelper.COLUMN_PASSWORD, password);
            contentValue.put(dbhelper.COLUMN_CONFIRM_PASSWORD, confirmPassword);
            database.insert(dbhelper.TABLE_REGISTER, null, contentValue);

            insertSignIn(username, password);
        } else {
            Toast.makeText(context, "Username already exists. Please choose a different username.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isUsernameExists(String username) {
        Cursor cursor = database.query(dbhelper.TABLE_REGISTER,
                new String[]{dbhelper.COLUMN_USERNAME},
                dbhelper.COLUMN_USERNAME + "=?",
                new String[]{username},
                null, null, null);

        boolean exists = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }

        return exists;
    }

    public Cursor fetchRegister() {
        String[] columns = new String[]{
                dbhelper.COLUMN_USERNAME,
                dbhelper.COLUMN_IC_NO,
                dbhelper.COLUMN_DATE_OF_BIRTH,
                dbhelper.COLUMN_EMAIL_ADDRESS,
                dbhelper.COLUMN_PHONE_NUMBER,
                dbhelper.COLUMN_PASSWORD,
                dbhelper.COLUMN_CONFIRM_PASSWORD
        };
        Cursor cursor = database.query(dbhelper.TABLE_REGISTER, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateRegister(String username, String icNo, String dateOfBirth, String emailAddress, String phoneNumber, String password, String confirmPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_IC_NO, icNo);
        contentValues.put(dbhelper.COLUMN_DATE_OF_BIRTH, dateOfBirth);
        contentValues.put(dbhelper.COLUMN_EMAIL_ADDRESS, emailAddress);
        contentValues.put(dbhelper.COLUMN_PHONE_NUMBER, phoneNumber);
        contentValues.put(dbhelper.COLUMN_PASSWORD, password);
        contentValues.put(dbhelper.COLUMN_CONFIRM_PASSWORD, confirmPassword);
        return database.update(dbhelper.TABLE_REGISTER, contentValues, dbhelper.COLUMN_USERNAME + " = ?", new String[]{username});
    }

    public void deleteRegister(String username) {
        database.delete(dbhelper.TABLE_REGISTER, dbhelper.COLUMN_USERNAME + "=?", new String[]{username});
    }

    // sign-in
    public void insertSignIn(String username, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_USERNAME, username);
        contentValue.put(dbhelper.COLUMN_PASSWORD, password);
        database.insert(dbhelper.TABLE_SIGN_IN, null, contentValue);
    }

    public Cursor fetchSignIn(String username, String password) {
        String[] columns = new String[]{dbhelper.COLUMN_USERNAME, dbhelper.COLUMN_PASSWORD};
        String selection = dbhelper.COLUMN_USERNAME + "=? AND " + dbhelper.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = new String[]{username, password};

        Cursor cursor = database.query(dbhelper.TABLE_SIGN_IN, columns, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int updateSignIn(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_PASSWORD, password);
        return database.update(dbhelper.TABLE_SIGN_IN, contentValues, dbhelper.COLUMN_USERNAME + " = ?", new String[]{username});
    }

    public void deleteSignIn(String username) {
        database.delete(dbhelper.TABLE_SIGN_IN, dbhelper.COLUMN_USERNAME + "=?", new String[]{username});
    }

    // ForgotPassword
    public void insertForgotPassword(String emailAddress, String newPassword, String confirmNewPassword) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_EMAIL_ADDRESS, emailAddress);
        contentValue.put(dbhelper.COLUMN_NEW_PASSWORD, newPassword);
        contentValue.put(dbhelper.COLUMN_CONFIRM_NEW_PASSWORD, confirmNewPassword);
        database.insert(dbhelper.TABLE_FORGOT_PASSWORD, null, contentValue);
    }

    public Cursor fetchForgotPassword(String emailAddress) {
        String[] columns = new String[]{dbhelper.COLUMN_EMAIL_ADDRESS, dbhelper.COLUMN_NEW_PASSWORD, dbhelper.COLUMN_CONFIRM_NEW_PASSWORD};
        Cursor cursor = database.query(dbhelper.TABLE_FORGOT_PASSWORD, columns, dbhelper.COLUMN_EMAIL_ADDRESS + "=?", new String[]{emailAddress}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateForgotPassword(String emailAddress, String newPassword, String confirmNewPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_NEW_PASSWORD, newPassword);
        contentValues.put(dbhelper.COLUMN_CONFIRM_NEW_PASSWORD, confirmNewPassword);
        return database.update(dbhelper.TABLE_FORGOT_PASSWORD, contentValues, dbhelper.COLUMN_EMAIL_ADDRESS + " = ?", new String[]{emailAddress});
    }

    public void deleteForgotPassword(String emailAddress) {
        database.delete(dbhelper.TABLE_FORGOT_PASSWORD, dbhelper.COLUMN_EMAIL_ADDRESS + "=?", new String[]{emailAddress});
    }


    // Add Card
    public void insertAddCard(String cardNumber, String expiryDate, String cvv) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_CARD_NUMBER, cardNumber);
        contentValue.put(dbhelper.COLUMN_EXPIRY_DATE, expiryDate);
        contentValue.put(dbhelper.COLUMN_CVV, cvv);
        database.insert(dbhelper.TABLE_ADD_CARD, null, contentValue);
    }

    public Cursor fetchAddCard() {
        String[] columns = new String[]{dbhelper.COLUMN_CARD_NUMBER, dbhelper.COLUMN_EXPIRY_DATE, dbhelper.COLUMN_CVV};
        return database.query(dbhelper.TABLE_ADD_CARD, columns, null, null, null, null, null);
    }

    public int updateAddCard(String cardNumber, String expiryDate, String cvv) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_EXPIRY_DATE, expiryDate);
        contentValues.put(dbhelper.COLUMN_CVV, cvv);
        return database.update(dbhelper.TABLE_ADD_CARD, contentValues, dbhelper.COLUMN_CARD_NUMBER + " = ?", new String[]{cardNumber});
    }

    public void deleteAddCard(String cardNumber) {
        database.delete(dbhelper.TABLE_ADD_CARD, dbhelper.COLUMN_CARD_NUMBER + "=?", new String[]{cardNumber});
    }

    // favourite
    public void insertFavourite(int favrouiteID, String cardNumber, String expiryDate, String cvv) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_FAVROUITE_ID, favrouiteID);
        contentValue.put(dbhelper.COLUMN_CARD_NUMBER, cardNumber);
        contentValue.put(dbhelper.COLUMN_EXPIRY_DATE, expiryDate);
        contentValue.put(dbhelper.COLUMN_CVV, cvv);
        database.insert(dbhelper.TABLE_FAVOURITES, null, contentValue);
    }

    public Cursor fetchFavourite() {
        String[] columns = new String[]{dbhelper.COLUMN_CARD_NUMBER, dbhelper.COLUMN_EXPIRY_DATE, dbhelper.COLUMN_CVV};
        return database.query(dbhelper.TABLE_FAVOURITES, columns, null, null, null, null, null);
    }

    public int updateFavourite(int favrouiteID, String cardNumber, String expiryDate, String cvv) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_CARD_NUMBER, cardNumber);
        contentValues.put(dbhelper.COLUMN_EXPIRY_DATE, expiryDate);
        contentValues.put(dbhelper.COLUMN_CVV, cvv);
        return database.update(dbhelper.TABLE_FAVOURITES, contentValues, dbhelper.COLUMN_FAVROUITE_ID + " = ?", new String[]{String.valueOf(favrouiteID)});
    }

    public void deleteFavourite(int favrouiteID) {
        database.delete(dbhelper.TABLE_FAVOURITES, dbhelper.COLUMN_FAVROUITE_ID + "=?", new String[]{String.valueOf(favrouiteID)});
    }


    // reviews
    public void insertReview(String Review_ID, String Review_star , String Comments) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_FAVROUITE_ID, Review_ID);//Primary Key
        contentValue.put(dbhelper.COLUMN_CARD_NUMBER,Review_star);
        contentValue.put(dbhelper.COLUMN_EXPIRY_DATE,Comments);
        database.insert(dbhelper.TABLE_REVIEW, null, contentValue);
    }

    public Cursor fetchReview() {
        String[] columns = new String[]{dbhelper.COLUMN_REVIEW_ID, dbhelper.COLUMN_REVIEW_STAR, dbhelper.COLUMN_COMMENTS};
        return database.query(dbhelper.TABLE_REVIEW, columns, null, null, null, null, null);
    }

    public int updateReview(String Review_ID, String Review_star , String Comments) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_CARD_NUMBER, Review_ID);
        contentValues.put(dbhelper.COLUMN_EXPIRY_DATE, Review_star);
        contentValues.put(dbhelper.COLUMN_CVV, Comments);
        return database.update(dbhelper.TABLE_REVIEW, contentValues, dbhelper.COLUMN_FAVROUITE_ID + " = ?", new String[]{Review_ID});
    }

    public void deleteReview(String Review_ID) {
        database.delete(dbhelper.TABLE_REVIEW, dbhelper.COLUMN_REVIEW_ID + "=?", new String[]{Review_ID});
    }

    //Booking
    public void insertBooking(String Booking_id, String Airport , String destination_country,String Payment_Method, String flight_date) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_BOOKING_ID, Booking_id);
        contentValue.put(dbhelper.COLUMN_AIRPORT,Airport);
        contentValue.put(dbhelper.COLUMN_DESTINATION_COUNTRY,destination_country);
        contentValue.put(dbhelper.COLUMN_PAYMENT_METHOD, Payment_Method);
        contentValue.put(dbhelper.COLUMN_FLIGHT_DATE,flight_date);
        database.insert(dbhelper.TABLE_BOOKING, null, contentValue);
    }

    public Cursor fetchBooking() {
        String[] columns = new String[]{dbhelper.COLUMN_BOOKING_ID, dbhelper.COLUMN_AIRPORT, dbhelper.COLUMN_EXPIRY_DATE,dbhelper.COLUMN_PAYMENT_METHOD, dbhelper.COLUMN_FLIGHT_DATE};
        return database.query(dbhelper.TABLE_BOOKING, columns, null, null, null, null, null);
    }

    public int updateBooking(String Booking_id, String Airport , String destination_country, String flight_date, String Payment_Method) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_AIRPORT, Airport);
        contentValues.put(dbhelper.COLUMN_DESTINATION_COUNTRY, destination_country);
        contentValues.put(dbhelper.COLUMN_PAYMENT_METHOD, Payment_Method);
        contentValues.put(dbhelper.COLUMN_FLIGHT_DATE, flight_date);
        return database.update(dbhelper.TABLE_BOOKING, contentValues, dbhelper.COLUMN_BOOKING_ID + " = ?", new String[]{Booking_id});
    }

    public void deleteBooking(String Booking_id) {
        database.delete(dbhelper.TABLE_BOOKING, dbhelper.COLUMN_BOOKING_ID + "=?", new String[]{Booking_id});
    }

    //seats
    public void insertSeats(String Seats_id, String Flight_class , String Booking_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_SEATS_ID, Seats_id);
        contentValue.put(dbhelper.COLUMN_FLIGHT_CLASS,Flight_class);
        contentValue.put(dbhelper.COLUMN_BOOKING_ID,Booking_id);
        database.insert(dbhelper.TABLE_SEATS, null, contentValue);
    }

    public long insertSeats(String flightClass, long bookingId) {
        ContentValues values = new ContentValues();
        values.put("Flight_class", flightClass);
        values.put("Booking_id", bookingId);

        return database.insert("Seats", null, values);
    }

    public Cursor fetchSeats() {
        String[] columns = new String[]{dbhelper.COLUMN_SEATS_ID, dbhelper.COLUMN_FLIGHT_CLASS, dbhelper.COLUMN_BOOKING_ID};
        return database.query(dbhelper.TABLE_SEATS, columns, null, null, null, null, null);
    }

    public int updateSeats(String Seats_id, String Flight_class , String Booking_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_FLIGHT_CLASS, Flight_class);
        contentValues.put(dbhelper.COLUMN_BOOKING_ID, Booking_id);
        return database.update(dbhelper.TABLE_SEATS, contentValues, dbhelper.COLUMN_SEATS_ID + " = ?", new String[]{Seats_id});
    }

    public void deleteSeats(String Seats_id) {
        database.delete(dbhelper.TABLE_SEATS, dbhelper.COLUMN_SEATS_ID + "=?", new String[]{Seats_id});
    }

    //meals
    public void insertMeals(String Food_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_FOOD_NAME, Food_name);//Primary Key
        database.insert(dbhelper.TABLE_SELECT_MEAL, null, contentValue);
    }

    public Cursor fetchMeals() {
        String[] columns = new String[]{dbhelper.COLUMN_FOOD_NAME};
        return database.query(dbhelper.TABLE_SELECT_MEAL, columns, null, null, null, null, null);
    }

    public int updateMeals(String Food_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_FOOD_NAME, Food_name);
        return database.update(dbhelper.TABLE_SELECT_MEAL, contentValues, dbhelper.COLUMN_FOOD_NAME + " = ?", new String[]{Food_name});
    }

    public void deleteMeals(String Food_name) {
        database.delete(dbhelper.TABLE_SELECT_MEAL, dbhelper.COLUMN_FOOD_NAME + "=?", new String[]{Food_name});
    }

    // options
    public void insertFoodOptions(String Food_name, String Option_select) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_FOOD_NAME, Food_name);
        contentValue.put(dbhelper.COLUMN_OPTION_SELECT, Option_select);//Primary Key
        database.insert(dbhelper.TABLE_FOOD_OPTION, null, contentValue);
    }

    public Cursor fetchFoodOptions() {
        String[] columns = new String[]{dbhelper.COLUMN_FOOD_NAME, dbhelper.COLUMN_OPTION_SELECT};
        return database.query(dbhelper.TABLE_FOOD_OPTION, columns, null, null, null, null, null);
    }

    public int updateFoodOptions(String Food_name, String Option_select) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_FOOD_NAME, Food_name);
        return database.update(dbhelper.TABLE_FOOD_OPTION, contentValues, dbhelper.COLUMN_OPTION_SELECT + " = ?", new String[]{Option_select});
    }

    public void deleteFoodOptions(String Option_select) {
        database.delete(dbhelper.TABLE_FOOD_OPTION, dbhelper.COLUMN_OPTION_SELECT + "=?", new String[]{Option_select});
    }

    // cart
    public void insertCart(String Cart_ID, String Food_name, String Option_select) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_CART_ID, Cart_ID);//Primary Key
        contentValue.put(dbhelper.COLUMN_FOOD_NAME, Food_name);
        contentValue.put(dbhelper.COLUMN_OPTION_SELECT, Option_select);
        database.insert(dbhelper.TABLE_MY_CART, null, contentValue);
    }

    public Cursor fetchCart() {
        String[] columns = new String[]{dbhelper.COLUMN_CART_ID, dbhelper.COLUMN_FOOD_NAME, dbhelper.COLUMN_OPTION_SELECT};
        return database.query(dbhelper.TABLE_MY_CART, columns, null, null, null, null, null);
    }

    public int updateCart(String Cart_ID, String Food_name, String Option_select) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_FOOD_NAME, Food_name);
        contentValues.put(dbhelper.COLUMN_OPTION_SELECT, Option_select);
        return database.update(dbhelper.TABLE_MY_CART, contentValues, dbhelper.COLUMN_CART_ID + " = ?", new String[]{Cart_ID});
    }

    public void deleteCart(String Cart_ID) {
        database.delete(dbhelper.TABLE_MY_CART, dbhelper.COLUMN_CART_ID + "=?", new String[]{Cart_ID});
    }

    // confirm booking
    public void insertComfirmBooking(String Comfirm_ID,String Booking_ID, String Seats_id, String Food_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_COMFIRM_ID, Comfirm_ID); //Primary Key
        contentValue.put(dbhelper.COLUMN_BOOKING_ID, Booking_ID);
        contentValue.put(dbhelper.COLUMN_SEATS_ID, Seats_id);
        contentValue.put(dbhelper.COLUMN_FOOD_NAME, Food_name);
        database.insert(dbhelper.TABLE_CONFIRM_BOOKING, null, contentValue);
    }

    public Cursor fetchComfirmBooking() {
        String[] columns = new String[]{dbhelper.COLUMN_COMFIRM_ID,dbhelper.COLUMN_BOOKING_ID, dbhelper.COLUMN_SEATS_ID, dbhelper.COLUMN_FOOD_NAME};
        return database.query(dbhelper.TABLE_CONFIRM_BOOKING, columns, null, null, null, null, null);
    }

    public int updateComfirmBooking(String Comfirm_ID,String Booking_ID, String Seats_id, String Food_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_BOOKING_ID, Booking_ID);
        contentValues.put(dbhelper.COLUMN_FOOD_NAME, Seats_id);
        contentValues.put(dbhelper.COLUMN_OPTION_SELECT, Food_name);
        return database.update(dbhelper.TABLE_CONFIRM_BOOKING, contentValues, dbhelper.COLUMN_COMFIRM_ID + " = ?", new String[]{Comfirm_ID});
    }

    public void deleteComfirmBooking(String Comfirm_ID) {
        database.delete(dbhelper.TABLE_CONFIRM_BOOKING, dbhelper.COLUMN_COMFIRM_ID + "=?", new String[]{Comfirm_ID});
    }

    // admin login
    public void insertAdminLogin(String admin_id,String admin_username, String admin_password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_ADMIN_ID, admin_id);
        contentValue.put(dbhelper.COLUMN_ADMIN_USERNAME, admin_username);
        contentValue.put(dbhelper.COLUMN_ADMIN_PASSWORD, admin_password);
        database.insert(dbhelper.TABLE_ADMIN_LOGIN, null, contentValue);
    }

    public Cursor fetchAdminLogin(String admin_Username, String admin_Password) {
        String[] columns = new String[]{dbhelper.COLUMN_ADMIN_ID,dbhelper.COLUMN_ADMIN_USERNAME, dbhelper.COLUMN_ADMIN_PASSWORD};
        return database.query(dbhelper.TABLE_ADMIN_LOGIN, columns, null, null, null, null, null);
    }

    public int updateAdminLogin(String admin_id,String admin_username, String admin_password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_ADMIN_USERNAME, admin_username);
        contentValues.put(dbhelper.COLUMN_ADMIN_PASSWORD, admin_password);
        return database.update(dbhelper.TABLE_ADMIN_LOGIN, contentValues, dbhelper.COLUMN_ADMIN_ID + " = ?", new String[]{admin_id});
    }

    public void deleteAdminLogin(String admin_id) {
        database.delete(dbhelper.TABLE_ADMIN_LOGIN, dbhelper.COLUMN_ADMIN_ID + "=?", new String[]{admin_id});
    }

    // admin password
    public void insertAdminPasscode(String passcode_id, String passcode, String admin_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_PASSCODE_ID, passcode_id); //Primary Key
        contentValue.put(dbhelper.COLUMN_PASSCODE, passcode);
        contentValue.put(dbhelper.COLUMN_ADMIN_ID, admin_id);
        database.insert(dbhelper.TABLE_ADMIN_PASSCODE, null, contentValue);
    }

    public Cursor fetchAdminPasscode() {
        String[] columns = new String[]{dbhelper.COLUMN_PASSCODE_ID, dbhelper.COLUMN_PASSCODE, dbhelper.COLUMN_ADMIN_ID};
        return database.query(dbhelper.TABLE_ADMIN_PASSCODE, columns, null, null, null, null, null);
    }

    public int updateAdminPasscode(String passcode_id, String passcode, String admin_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_PASSCODE, passcode);
        contentValues.put(dbhelper.COLUMN_ADMIN_ID, admin_id);
        return database.update(dbhelper.TABLE_ADMIN_PASSCODE, contentValues, dbhelper.COLUMN_PASSCODE_ID + " = ?", new String[]{passcode_id});
    }

    public void deleteAdminPasscode(String passcode_id) {
        database.delete(dbhelper.TABLE_ADMIN_PASSCODE, dbhelper.COLUMN_PASSCODE_ID + "=?", new String[]{passcode_id});
    }

    // admin profile
    public void insertAdminProfile(String profile_id,String adminProfile_pic) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_PROFILE_ID, profile_id); //Primary Key
        contentValue.put(dbhelper.COLUMN_ADMIN_PROFILE_PIC, adminProfile_pic);
        database.insert(dbhelper.TABLE_ADMIN_PROFILE, null, contentValue);
    }

    public Cursor fetchAdminProfile() {
        String[] columns = new String[]{dbhelper.COLUMN_PASSCODE_ID,dbhelper.COLUMN_ADMIN_PROFILE_PIC, dbhelper.COLUMN_PASSCODE, dbhelper.COLUMN_ADMIN_ID};
        return database.query(dbhelper.TABLE_ADMIN_PROFILE, columns, null, null, null, null, null);
    }

    public int updateAdminProfile(String profile_id,String adminProfile_pic, String passcode, String admin_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_ADMIN_PROFILE_PIC, adminProfile_pic);
        contentValues.put(dbhelper.COLUMN_PASSCODE, passcode);
        contentValues.put(dbhelper.COLUMN_ADMIN_ID, admin_id);
        return database.update(dbhelper.TABLE_ADMIN_PROFILE, contentValues, dbhelper.COLUMN_PROFILE_ID + " = ?", new String[]{profile_id});
    }

    public void deleteAdminProfile(String profile_id) {
        database.delete(dbhelper.TABLE_ADMIN_PROFILE, dbhelper.COLUMN_PROFILE_ID + "=?", new String[]{profile_id});
    }

    // monthly statistic
    public void insertMonthlyStatistic(String month_id,String month, String monthly_bookedFlight,String monthly_bookedHotel, String monthly_bookedActivities, String monthly_cancelActivities) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_MONTH_ID, month_id); //Primary Key
        contentValue.put(dbhelper.COLUMN_MONTH, month);
        contentValue.put(dbhelper.COLUMN_MONTHLY_BOOKED_FLIGHT, monthly_bookedFlight);
        contentValue.put(dbhelper.COLUMN_MONTHLY_CANCEL_ACTIVITIES, monthly_cancelActivities);
        database.insert(dbhelper.TABLE_MONTHLY_STATISTIC, null, contentValue);
    }

    public Cursor fetchMonthlyStatistic() {
        String[] columns = new String[]{dbhelper.COLUMN_MONTH_ID,dbhelper.COLUMN_MONTH, dbhelper.COLUMN_MONTHLY_BOOKED_FLIGHT,dbhelper.COLUMN_MONTHLY_CANCEL_ACTIVITIES};
        return database.query(dbhelper.TABLE_MONTHLY_STATISTIC, columns, null, null, null, null, null);
    }

    public int updateMonthlyStatistic(String month_id,String month, String monthly_bookedFlight,String monthly_bookedHotel, String monthly_bookedActivities, String monthly_cancelActivities) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_MONTH, month);
        contentValues.put(dbhelper.COLUMN_MONTHLY_BOOKED_FLIGHT, monthly_bookedFlight);
        contentValues.put(dbhelper.COLUMN_MONTHLY_CANCEL_ACTIVITIES, monthly_cancelActivities);
        return database.update(dbhelper.TABLE_MONTHLY_STATISTIC, contentValues, dbhelper.COLUMN_MONTH_ID + " = ?", new String[]{month_id});
    }

    public void deleteMonthlyStatistic(String month_id) {
        database.delete(dbhelper.TABLE_MONTHLY_STATISTIC, dbhelper.COLUMN_MONTH_ID + "=?", new String[]{month_id});
    }

    // yearly statistic
    public void insertYearlyStatistic(String years_id,String years, String yearly_bookedFlight, String yearly_cancelActivities) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_YEARS_ID, years_id); //Primary Key
        contentValue.put(dbhelper.COLUMN_YEARS, years);
        contentValue.put(dbhelper.COLUMN_YEARLY_BOOKED_FLIGHT, yearly_bookedFlight);
        contentValue.put(dbhelper.COLUMN_YEARLY_CANCEL_ACTIVITIES, yearly_cancelActivities);
        database.insert(dbhelper.TABLE_YEARLY_STATISTIC, null, contentValue);
    }

    public Cursor fetchYearlyStatistic() {
        String[] columns = new String[]{dbhelper.COLUMN_YEARS_ID,dbhelper.COLUMN_YEARS, dbhelper.COLUMN_YEARLY_BOOKED_FLIGHT,dbhelper.COLUMN_YEARLY_CANCEL_ACTIVITIES};
        return database.query(dbhelper.TABLE_YEARLY_STATISTIC, columns, null, null, null, null, null);
    }

    public int updateYearlyStatistic(String years_id,String years, String yearly_bookedFlight ,String yearly_cancelActivities) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_YEARS, years);
        contentValues.put(dbhelper.COLUMN_YEARLY_BOOKED_FLIGHT, yearly_bookedFlight);
        contentValues.put(dbhelper.COLUMN_YEARLY_CANCEL_ACTIVITIES, yearly_cancelActivities);
        return database.update(dbhelper.TABLE_YEARLY_STATISTIC, contentValues, dbhelper.COLUMN_YEARS_ID + " = ?", new String[]{years_id});
    }

    public void deleteYearlyStatistic(String years_id) {
        database.delete(dbhelper.TABLE_YEARLY_STATISTIC, dbhelper.COLUMN_YEARS_ID + "=?", new String[]{years_id});
    }

    // payment method
    public void insertPaymentMethod(String Payment_id,String Payment_Method, String Payment_Amount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_PAYMENT_ID, Payment_id); //Primary Key
        contentValue.put(dbhelper.COLUMN_PAYMENT_METHOD, Payment_Method);
        contentValue.put(dbhelper.COLUMN_PAYMENT_AMOUNT, Payment_Amount);
        database.insert(dbhelper.TABLE_CHOOSE_PAYMENT, null, contentValue);
    }

    public Cursor fetchPaymentMethod() {
        String[] columns = new String[]{dbhelper.COLUMN_PAYMENT_ID,dbhelper.COLUMN_PAYMENT_METHOD, dbhelper.COLUMN_PAYMENT_AMOUNT};
        return database.query(dbhelper.TABLE_CHOOSE_PAYMENT, columns, null, null, null, null, null);
    }

    public int updatePaymentMethod(String Payment_id,String Payment_Method, String Payment_Amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_PAYMENT_METHOD, Payment_Method);
        contentValues.put(dbhelper.COLUMN_PAYMENT_AMOUNT, Payment_Amount);
        return database.update(dbhelper.TABLE_CHOOSE_PAYMENT, contentValues, dbhelper.COLUMN_PAYMENT_ID + " = ?", new String[]{Payment_id});
    }

    public void deletePaymentMethod(String Payment_id) {
        database.delete(dbhelper.TABLE_CHOOSE_PAYMENT, dbhelper.TABLE_CHOOSE_PAYMENT + "=?", new String[]{Payment_id});
    }

    // card payment
    public void insertCardPayment(String Payment_id,String username, String Card_Number, String Total_Amount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_PAYMENT_ID, Payment_id); //Primary Key
        contentValue.put(dbhelper.COLUMN_PAYMENT_METHOD, username);
        contentValue.put(dbhelper.COLUMN_CARD_NUMBER, Card_Number);
        contentValue.put(dbhelper.COLUMN_PAYMENT_AMOUNT, Total_Amount);
        database.insert(dbhelper.TABLE_CARD_PAYMENT, null, contentValue);
    }

    public Cursor fetchCardPayment() {
        String[] columns = new String[]{dbhelper.COLUMN_PAYMENT_ID,dbhelper.COLUMN_PAYMENT_METHOD, dbhelper.COLUMN_CARD_NUMBER,dbhelper.COLUMN_PAYMENT_AMOUNT};
        return database.query(dbhelper.TABLE_CARD_PAYMENT, columns, null, null, null, null, null);
    }

    public int updateCardPayment(String Payment_id,String username, String Card_Number, String Total_Amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_PAYMENT_METHOD, username);
        contentValues.put(dbhelper.COLUMN_CARD_NUMBER, Card_Number);
        contentValues.put(dbhelper.COLUMN_PAYMENT_AMOUNT, Total_Amount);
        return database.update(dbhelper.TABLE_CARD_PAYMENT, contentValues, dbhelper.COLUMN_PAYMENT_ID + " = ?", new String[]{Payment_id});
    }

    public void deleteCardPayment(String Payment_id) {
        database.delete(dbhelper.TABLE_CARD_PAYMENT, dbhelper.TABLE_CHOOSE_PAYMENT + "=?", new String[]{Payment_id});
    }

    // payment
    public void insertPayment(String Payment_ID, String Payment_Method, String Amount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_PAYMENT_ID, Payment_ID); // Primary Key
        contentValue.put(dbhelper.COLUMN_PAYMENT_METHOD, Payment_Method);
        contentValue.put(dbhelper.COLUMN_PAYMENT_AMOUNT, Amount);
        database.insert(dbhelper.TABLE_PAYMENT, null, contentValue);
    }

    public Cursor fetchPayment() {
        String[] columns = new String[]{dbhelper.COLUMN_PAYMENT_ID, dbhelper.COLUMN_PAYMENT_METHOD, dbhelper.COLUMN_PAYMENT_AMOUNT};
        return database.query(dbhelper.TABLE_PAYMENT, columns, null, null, null, null, null);
    }

    public int updatePayment(String Payment_ID, String Payment_Method, String Amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_PAYMENT_METHOD, Payment_Method);
        contentValues.put(dbhelper.COLUMN_PAYMENT_AMOUNT, Amount);
        return database.update(dbhelper.TABLE_PAYMENT, contentValues, dbhelper.COLUMN_PAYMENT_ID + " = ?", new String[]{Payment_ID});
    }

    public void deletePayment(String Payment_ID) {
        database.delete(dbhelper.TABLE_PAYMENT, dbhelper.COLUMN_PAYMENT_ID + "=?", new String[]{Payment_ID});
    }

    public Cursor fetchUserBookingDetails(String username) {
        String query = "SELECT " +
                "b." + dbhelper.COLUMN_BOOKING_ID + ", " +
                "s." + dbhelper.COLUMN_FLIGHT_CLASS + ", " +
                "c." + dbhelper.COLUMN_FOOD_NAME +
                " FROM " + dbhelper.TABLE_CONFIRM_BOOKING + " cb " +
                " INNER JOIN " + dbhelper.TABLE_BOOKING + " b ON cb." + dbhelper.COLUMN_BOOKING_ID + " = b." + dbhelper.COLUMN_BOOKING_ID +
                " INNER JOIN " + dbhelper.TABLE_SEATS + " s ON cb." + dbhelper.COLUMN_SEATS_ID + " = s." + dbhelper.COLUMN_SEATS_ID +
                " INNER JOIN " + dbhelper.TABLE_SELECT_MEAL + " c ON cb." + dbhelper.COLUMN_FOOD_NAME + " = c." + dbhelper.COLUMN_FOOD_NAME +
                " WHERE b." + dbhelper.COLUMN_USERNAME + " = ?";

        return database.rawQuery(query, new String[]{username});
    }

    public Cursor fetchUserBookingDetails() {
        String query = "SELECT " +
                "r." + dbhelper.COLUMN_USERNAME + ", " +
                "s." + dbhelper.COLUMN_FLIGHT_CLASS + ", " +
                "c." + dbhelper.COLUMN_FOOD_NAME +
                " FROM " + dbhelper.TABLE_CONFIRM_BOOKING + " cb " +
                " INNER JOIN " + dbhelper.TABLE_BOOKING + " b ON cb." + dbhelper.COLUMN_BOOKING_ID + " = b." + dbhelper.COLUMN_BOOKING_ID +
                " INNER JOIN " + dbhelper.TABLE_SEATS + " s ON cb." + dbhelper.COLUMN_SEATS_ID + " = s." + dbhelper.COLUMN_SEATS_ID +
                " INNER JOIN " + dbhelper.TABLE_SELECT_MEAL + " c ON cb." + dbhelper.COLUMN_FOOD_NAME + " = c." + dbhelper.COLUMN_FOOD_NAME +
                " INNER JOIN " + dbhelper.TABLE_REGISTER + " r ON b." + dbhelper.COLUMN_USERNAME + " = r." + dbhelper.COLUMN_USERNAME +
                " WHERE b." + dbhelper.COLUMN_USERNAME + " = ?";

        return database.rawQuery(query, new String[]{});
    }


    //Popular
    public int updatePopular(String popularId, String updatedName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_POPULAR_NAME, updatedName);
        return database.update(dbhelper.TABLE_POPULAR, contentValues, dbhelper.COLUMN_POPULAR_ID + " = ?", new String[]{popularId});
    }

    public Cursor fetchPopular() {
        String[] columns = new String[]{dbhelper.COLUMN_POPULAR_ID, dbhelper.COLUMN_POPULAR_NAME};
        return database.query(dbhelper.TABLE_POPULAR, columns, null, null, null, null, null);
    }


    public int updateUserInfo(String username, String icNumber, String dateOfBirth, String email,
                              String password, String confirmPassword, String emergencyName,
                              String emergencyPhone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_DATE_OF_BIRTH, dateOfBirth);
        contentValues.put(dbhelper.COLUMN_EMAIL, email);
        contentValues.put(dbhelper.COLUMN_PASSWORD, password);
        contentValues.put(dbhelper.COLUMN_CONFIRM_PASSWORD, confirmPassword);
        contentValues.put(dbhelper.COLUMN_EMERGENCY_NAME, emergencyName);
        contentValues.put(dbhelper.COLUMN_EMERGENCY_PHONE, emergencyPhone);

        int rowsAffected = database.update(dbhelper.TABLE_USER_INFO, contentValues,
                dbhelper.COLUMN_USERNAME + " = ?", new String[]{username});

        return rowsAffected;
    }

    public long insertUserProfile(String userId, String userProfilePic) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_USER_ID, userId);
        contentValue.put(dbhelper.COLUMN_USER_PROFILE_PIC, userProfilePic);
        return database.insert(dbhelper.TABLE_USER_PROFILE, null, contentValue);
    }

    public Cursor fetchUserProfile() {
        String[] columns = new String[]{dbhelper.COLUMN_ID, dbhelper.COLUMN_USER_ID, dbhelper.COLUMN_USER_PROFILE_PIC};
        Cursor cursor = database.query(dbhelper.TABLE_USER_PROFILE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateUserProfile(String profile_id, String adminProfile_pic) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_ADMIN_PROFILE_PIC, adminProfile_pic);
        database.update(dbhelper.TABLE_ADMIN_PROFILE, contentValues, dbhelper.COLUMN_PROFILE_ID + " = ?", new String[]{profile_id});
        return 0;
    }

    public int deleteUserProfile(String userId) {
        return database.delete(dbhelper.TABLE_USER_PROFILE,
                dbhelper.COLUMN_USER_ID + "=?", new String[]{userId});
    }

    // promotion
    public void insertPromotion(String promotionID, String promotionName, String discountPercentage) {
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put(dbhelper.COLUMN_PROMOTION_ID, promotionID); // Primary Key
            contentValue.put(dbhelper.COLUMN_PROMOTION_NAME, promotionName);
            contentValue.put(dbhelper.COLUMN_DISCOUNT_PERCENTAGE, discountPercentage);
            long result = database.insert(dbhelper.TABLE_PROMOTION, null, contentValue);

            if (result != -1) {
                Toast.makeText(context, "Promotion added successfully", LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to add promotion", LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), LENGTH_SHORT).show();
        }
    }

    public Cursor fetchPromotion() {
        String[] columns = new String[]{dbhelper.COLUMN_PROMOTION_ID, dbhelper.COLUMN_PROMOTION_NAME, dbhelper.COLUMN_DISCOUNT_PERCENTAGE};
        return database.query(dbhelper.TABLE_PROMOTION, columns, null, null, null, null, null);
    }

    public int updatePromotion(String promotionID, String promotionName, String discountPercentage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_PROMOTION_NAME, promotionName);
        contentValues.put(dbhelper.COLUMN_DISCOUNT_PERCENTAGE, discountPercentage);
        return database.update(dbhelper.TABLE_PROMOTION, contentValues, dbhelper.COLUMN_PROMOTION_ID + " = ?", new String[]{promotionID});
    }

    public void deletePromotion(String promotionID) {
        database.delete(dbhelper.TABLE_PROMOTION, dbhelper.COLUMN_PROMOTION_ID + "=?", new String[]{promotionID});
    }

    // setting
    public void insertSetting(String darkTheme, String unit, String temperatureUnit, String country) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_DARK_THEME, darkTheme);
        contentValue.put(dbhelper.COLUMN_UNIT, unit);
        contentValue.put(dbhelper.COLUMN_TEMPERATURE_UNIT, temperatureUnit);
        contentValue.put(dbhelper.COLUMN_COUNTRY, country);

        long result = database.insert(dbhelper.TABLE_SETTINGS, null, contentValue);
    }

    public Cursor fetchSetting() {
        String[] columns = new String[]{
                dbhelper.COLUMN_DARK_THEME,
                dbhelper.COLUMN_UNIT,
                dbhelper.COLUMN_TEMPERATURE_UNIT,
                dbhelper.COLUMN_COUNTRY
        };

        return database.query(dbhelper.TABLE_SETTINGS, columns, null, null, null, null, null);
    }

    public int updateSetting(String darkTheme, String unit, String temperatureUnit, String country) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper.COLUMN_UNIT, unit);
        contentValues.put(dbhelper.COLUMN_TEMPERATURE_UNIT, temperatureUnit);
        contentValues.put(dbhelper.COLUMN_COUNTRY, country);

        return database.update(dbhelper.TABLE_SETTINGS, contentValues, dbhelper.COLUMN_DARK_THEME + " = ?", new String[]{darkTheme});
    }

    public void deleteSetting(String darkTheme) {
        database.delete(dbhelper.TABLE_SETTINGS, dbhelper.COLUMN_DARK_THEME + "=?", new String[]{darkTheme});
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void insertTransactionHistory(String username, String phoneNumber, String airline, String location, String hotel, String flightMeal, String amount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper.COLUMN_USERNAME, username);
        contentValue.put(dbhelper.COLUMN_PHONE_NUMBER, phoneNumber);
        contentValue.put(dbhelper.COLUMN_AIRLINE, airline);
        contentValue.put(dbhelper.COLUMN_LOCATION, location);
        contentValue.put(dbhelper.COLUMN_HOTEL, hotel);
        contentValue.put(dbhelper.COLUMN_FLIGHT_MEAL, flightMeal);
        contentValue.put(dbhelper.COLUMN_AMOUNT, amount);
        database.insert(dbhelper.TABLE_TRANSACTION_HISTORY, null, contentValue);
    }

    public Cursor fetchTransactionHistory(String username) {
        String[] columns = new String[]{
                dbhelper.COLUMN_PHONE_NUMBER,
                dbhelper.COLUMN_AIRLINE,
                dbhelper.COLUMN_LOCATION,
                dbhelper.COLUMN_HOTEL,
                dbhelper.COLUMN_FLIGHT_MEAL,
                dbhelper.COLUMN_AMOUNT
        };
        return database.query(dbhelper.TABLE_TRANSACTION_HISTORY, columns, dbhelper.COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
    }

}