package com.example.finalasignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

    // Database Information
    static final String DB_NAME = "FLIGHT_RESERVATION_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Table Register
    public static final String TABLE_REGISTER = "Register";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_IC_NO = "IC_No";
    public static final String COLUMN_DATE_OF_BIRTH = "Date_Of_Birth";
    public static final String COLUMN_EMAIL_ADDRESS = "Email_Address";
    public static final String COLUMN_PHONE_NUMBER = "Phone_Number";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_CONFIRM_PASSWORD = "Confirm_Password";

    private static final String CREATE_TABLE_REGISTER =
            "CREATE TABLE " + TABLE_REGISTER + "("
            + COLUMN_USERNAME + " TEXT PRIMARY KEY, "
            + COLUMN_IC_NO + " TEXT, "
            + COLUMN_DATE_OF_BIRTH + " DATE, "
            + COLUMN_EMAIL_ADDRESS + " TEXT, "
            + COLUMN_PHONE_NUMBER + " TEXT, "
            + COLUMN_PASSWORD + " STRING, "
            + COLUMN_CONFIRM_PASSWORD + " STRING, "
            + "FOREIGN KEY(" + COLUMN_CONFIRM_PASSWORD + ") " + "REFERENCES " + TABLE_REGISTER + "(" + COLUMN_PASSWORD + "));";


    // Table SignIn
    public static final String TABLE_SIGN_IN = "SignIn";
    private static final String CREATE_TABLE_SIGN_IN = "CREATE TABLE " + TABLE_SIGN_IN + "("
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " STRING, "
                + "FOREIGN KEY (" + COLUMN_USERNAME + ") REFERENCES " + TABLE_REGISTER + "(" + COLUMN_USERNAME + "), "
                + "FOREIGN KEY (" + COLUMN_PASSWORD + ") REFERENCES " + TABLE_REGISTER + "(" + COLUMN_PASSWORD + "));";


    // Table ForgotPassword
    public static final String TABLE_FORGOT_PASSWORD = "ForgotPassword";
    public static final String COLUMN_NEW_PASSWORD = "New_Password";
    public static final String COLUMN_CONFIRM_NEW_PASSWORD = "Confirm_New_password";

    private static final String CREATE_TABLE_FORGOT_PASSWORD = "CREATE TABLE " + TABLE_FORGOT_PASSWORD + "("
            + COLUMN_EMAIL_ADDRESS + " TEXT, "
            + COLUMN_NEW_PASSWORD + " STRING, "
            + COLUMN_CONFIRM_NEW_PASSWORD + " STRING, "
            + "FOREIGN KEY (" + COLUMN_EMAIL_ADDRESS + ") REFERENCES " + TABLE_REGISTER + "(" + COLUMN_EMAIL_ADDRESS + "));";


    // Table Profile
    public static final String TABLE_PROFILE = "Profile";
    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE " + TABLE_PROFILE + "("
            + COLUMN_USERNAME + " TEXT, "
            + "FOREIGN KEY (" + COLUMN_USERNAME + ") REFERENCES " + TABLE_REGISTER + "(" + COLUMN_USERNAME + "));";


    // Table AddCard
    public static final String TABLE_ADD_CARD = "AddCard";
    public static final String COLUMN_CARD_NUMBER = "Card_Number";
    public static final String COLUMN_EXPIRY_DATE = "Expiry_Date";
    public static final String COLUMN_CVV = "CVV";
    String CREATE_TABLE_ADD_CARD = "CREATE TABLE " + TABLE_ADD_CARD + "("
            + COLUMN_CARD_NUMBER + " TEXT PRIMARY KEY, "
            + COLUMN_EXPIRY_DATE + " STRING, "
            + COLUMN_CVV + " STRING);";


    // Table Favourites
    public static final String TABLE_FAVOURITES = "Favourites";
    public static final String COLUMN_FAVROUITE_ID = "Favrouite_ID";

    String CREATE_TABLE_FAVOURITES = "CREATE TABLE " + TABLE_FAVOURITES + "("
            + COLUMN_FAVROUITE_ID + " INT, "
            + COLUMN_CARD_NUMBER + " TEXT, "
            + COLUMN_EXPIRY_DATE + " STRING, "
            + COLUMN_CVV + " STRING, "
            + "FOREIGN KEY (" + COLUMN_CARD_NUMBER + ") REFERENCES " + TABLE_ADD_CARD + "(" + COLUMN_CARD_NUMBER + "), "
            + "FOREIGN KEY (" + COLUMN_EXPIRY_DATE + ") REFERENCES " + TABLE_ADD_CARD + "(" + COLUMN_EXPIRY_DATE + "), "
            + "FOREIGN KEY (" + COLUMN_CVV + ") REFERENCES " + TABLE_ADD_CARD + "(" + COLUMN_CVV + "));";



    // Table Review
    public static final String TABLE_REVIEW = "Review";
    public static final String COLUMN_REVIEW_ID = "Review_ID";
    public static final String COLUMN_REVIEW_STAR = "Review_star";
    public static final String COLUMN_COMMENTS = "Comments";
    String CREATE_TABLE_REVIEW = "CREATE TABLE " + TABLE_REVIEW + "("
            + COLUMN_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_REVIEW_STAR + " TEXT, "
            + COLUMN_COMMENTS + " TEXT);";


    // Table Booking
    public static final String TABLE_BOOKING = "Booking";
    public static final String COLUMN_BOOKING_ID = "Booking_id";
    public static final String COLUMN_AIRPORT = "Airport";
    public static final String COLUMN_DESTINATION_COUNTRY = "destination_country";
    public static final String COLUMN_FLIGHT_DATE = "flight_date";
    String CREATE_TABLE_BOOKING = "CREATE TABLE " + TABLE_BOOKING + "("
            + COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_AIRPORT + " TEXT, "
            + COLUMN_DESTINATION_COUNTRY + " TEXT, "
            + COLUMN_PAYMENT_METHOD + " TEXT, "
            + COLUMN_FLIGHT_DATE + " DATE, "
            + "FOREIGN KEY(" + COLUMN_PAYMENT_METHOD + ") REFERENCES " + TABLE_CHOOSE_PAYMENT + "(" + COLUMN_PAYMENT_METHOD + "));";


    // Table Seats
    public static final String TABLE_SEATS = "Seats";
    public static final String COLUMN_SEATS_ID = "Seats_id";
    public static final String COLUMN_FLIGHT_CLASS = "Flight_class";
    String CREATE_TABLE_SEATS = "CREATE TABLE " + TABLE_SEATS + "("
            + COLUMN_SEATS_ID + " TEXT, "
            + COLUMN_FLIGHT_CLASS + " TEXT, "
            + COLUMN_BOOKING_ID + " INT, "
            + "PRIMARY KEY(" + COLUMN_SEATS_ID + "), "
            + "FOREIGN KEY(" + COLUMN_BOOKING_ID + ") REFERENCES " + TABLE_BOOKING + "(" + COLUMN_BOOKING_ID + "));";


    // Table SelectMeal
    public static final String TABLE_SELECT_MEAL = "Select_meal";
    public static final String COLUMN_FOOD_NAME = "Food_name";
    String CREATE_TABLE_SELECT_MEAL = "CREATE TABLE " + TABLE_SELECT_MEAL + "("
            + COLUMN_FOOD_NAME + " TEXT PRIMARY KEY);";


    // Table FoodOption
    public static final String TABLE_FOOD_OPTION = "Food_option";
    public static final String COLUMN_OPTION_SELECT = "Option_select";
    String CREATE_TABLE_FOOD_OPTION = "CREATE TABLE " + TABLE_FOOD_OPTION + "("
            + COLUMN_FOOD_NAME + " TEXT, "
            + COLUMN_OPTION_SELECT + " TEXT PRIMARY KEY, "
            + "FOREIGN KEY (" + COLUMN_FOOD_NAME + ") REFERENCES " + TABLE_SELECT_MEAL + "(" + COLUMN_FOOD_NAME + "));";


    // Table FoodDetails
    public static final String TABLE_FOOD_DETAILS = "Food_details";
    String CREATE_TABLE_FOOD_DETAILS = "CREATE TABLE " + TABLE_FOOD_DETAILS + "("
            + COLUMN_FOOD_NAME + " TEXT, "
            + COLUMN_OPTION_SELECT + " TEXT, "
            + "FOREIGN KEY (" + COLUMN_FOOD_NAME + ") REFERENCES " + TABLE_SELECT_MEAL + "(" + COLUMN_FOOD_NAME + "), "
            + "FOREIGN KEY (" + COLUMN_OPTION_SELECT + ") REFERENCES " + TABLE_FOOD_OPTION + "(" + COLUMN_OPTION_SELECT + "));";


    // Table MyCart
    public static final String TABLE_MY_CART = "My_cart";
    public static final String COLUMN_CART_ID = "Cart_ID";
    String CREATE_TABLE_MY_CART = "CREATE TABLE " + TABLE_MY_CART + "("
            + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_FOOD_NAME + " TEXT, "
            + COLUMN_OPTION_SELECT + " TEXT, "
            + "FOREIGN KEY (" + COLUMN_FOOD_NAME + ") REFERENCES " + TABLE_SELECT_MEAL + "(" + COLUMN_FOOD_NAME + "), "
            + "FOREIGN KEY (" + COLUMN_OPTION_SELECT + ") REFERENCES " + TABLE_FOOD_OPTION + "(" + COLUMN_OPTION_SELECT + "));";


    // Table ConfirmBooking
    public static final String TABLE_CONFIRM_BOOKING = "ConfirmBooking";
    public static final String COLUMN_COMFIRM_ID = "Comfirm_ID";
    String CREATE_TABLE_CONFIRM_BOOKING = "CREATE TABLE " + TABLE_CONFIRM_BOOKING + "("
            + COLUMN_COMFIRM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_BOOKING_ID + " INT, "
            + COLUMN_SEATS_ID + " INT, "
            + COLUMN_FOOD_NAME + " TEXT, "
            + "FOREIGN KEY(" + COLUMN_BOOKING_ID + ") REFERENCES " + TABLE_BOOKING + "(" + COLUMN_BOOKING_ID + "), "
            + "FOREIGN KEY(" + COLUMN_SEATS_ID + ") REFERENCES " + TABLE_SEATS + "(" + COLUMN_SEATS_ID + "), "
            + "FOREIGN KEY(" + COLUMN_FOOD_NAME + ") REFERENCES " + TABLE_MY_CART + "(" + COLUMN_FOOD_NAME + "));";


    //Table ChoosePayment
    public static final String TABLE_CHOOSE_PAYMENT = "Payment_Method";
    public static final String COLUMN_PAYMENT_ID = "Payment_id";
    public static final String COLUMN_PAYMENT_METHOD = "Payment_Method";
    public static final String COLUMN_PAYMENT_AMOUNT = "Payment_Amount";
    String CREATE_TABLE_CHOOSE_PAYMENT = "CREATE TABLE " + TABLE_CHOOSE_PAYMENT + "("
            + COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_PAYMENT_METHOD + " TEXT, "
            + COLUMN_PAYMENT_AMOUNT + " INT);";



    // Table CardPayment
    public static final String TABLE_CARD_PAYMENT = "Card_Payment";
    public static final String COLUMN_TOTAL_AMOUNT = "Total_Amount";
    String CREATE_TABLE_CARD_PAYMENT = "CREATE TABLE " + TABLE_CARD_PAYMENT + "("
            + COLUMN_PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_USERNAME + " TEXT, "
            + COLUMN_CARD_NUMBER + " TEXT, "
            + COLUMN_TOTAL_AMOUNT + " REAL, "
            + "FOREIGN KEY(" + COLUMN_USERNAME + ") REFERENCES " + TABLE_REGISTER + "(" + COLUMN_USERNAME + "), "
            + "FOREIGN KEY(" + COLUMN_CARD_NUMBER + ") REFERENCES " + TABLE_ADD_CARD + "(" + COLUMN_CARD_NUMBER + "));";


    // Table AdminLogin
    public static final String TABLE_ADMIN_LOGIN = "admin_login";
    public static final String COLUMN_ADMIN_ID = "admin_id";
    public static final String COLUMN_ADMIN_USERNAME = "admin_username";
    public static final String COLUMN_ADMIN_PASSWORD = "admin_password";
    String CREATE_TABLE_ADMIN_LOGIN = "CREATE TABLE " + TABLE_ADMIN_LOGIN + "("
            + COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_ADMIN_USERNAME + " TEXT, "
            + COLUMN_ADMIN_PASSWORD + " TEXT);";


    // Table AdminPasscode
    public static final String TABLE_ADMIN_PASSCODE = "admin_passcode";
    public static final String COLUMN_PASSCODE_ID = "passcode_id";
    public static final String COLUMN_ADMIN_PROFILE_PIC = "adminProfile_pic";
    public static final String COLUMN_PASSCODE = "passcode";
    String CREATE_TABLE_ADMIN_PASSCODE = "CREATE TABLE " + TABLE_ADMIN_PASSCODE + "("
            + COLUMN_PASSCODE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_ADMIN_PROFILE_PIC + " BLOB, "
            + COLUMN_PASSCODE + " TEXT, "
            + COLUMN_ADMIN_USERNAME + " TEXT, "
            + "FOREIGN KEY (" + COLUMN_ADMIN_USERNAME + ") REFERENCES " + TABLE_ADMIN_LOGIN + "(" + COLUMN_ADMIN_USERNAME + "));";


    // Table AdminProfile
    public static final String TABLE_ADMIN_PROFILE = "admin_profile";
    public static final String COLUMN_PROFILE_ID = "profile_id";
    String CREATE_TABLE_ADMIN_PROFILE = "CREATE TABLE " + TABLE_ADMIN_PROFILE + "("
            + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ADMIN_PROFILE_PIC + " BLOB, "
            + COLUMN_ADMIN_USERNAME + " TEXT, "
            + "FOREIGN KEY(" + COLUMN_ADMIN_USERNAME + ") REFERENCES " + TABLE_ADMIN_LOGIN + "(" + COLUMN_ADMIN_USERNAME + "));";


    // Table MonthlyStatistic
    public static final String TABLE_MONTHLY_STATISTIC = "monthly_statistic";
    public static final String COLUMN_MONTH_ID = "month_id";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_MONTHLY_BOOKED_FLIGHT = "monthly_bookedFlight";
    public static final String COLUMN_MONTHLY_BOOKED_HOTEL = "monthly_bookedHotel";
    public static final String COLUMN_MONTHLY_BOOKED_ACTIVITIES = "monthly_bookedActivities";
    public static final String COLUMN_MONTHLY_CANCEL_ACTIVITIES = "monthly_cancelActivities";
    String CREATE_TABLE_MONTHLY_STATISTIC = "CREATE TABLE " + TABLE_MONTHLY_STATISTIC + "("
            + COLUMN_MONTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MONTH + " MONTH, "
            + COLUMN_MONTHLY_BOOKED_FLIGHT + " INT(100), "
            + COLUMN_MONTHLY_BOOKED_HOTEL + " INT(100), "
            + COLUMN_MONTHLY_BOOKED_ACTIVITIES + " INT(100), "
            + COLUMN_MONTHLY_CANCEL_ACTIVITIES + " INT(100));";


    // Table YearlyStatistic
    public static final String TABLE_YEARLY_STATISTIC = "yearly_statistic";
    public static final String COLUMN_YEARS_ID = "years_id";
    public static final String COLUMN_YEARS = "years";
    public static final String COLUMN_YEARLY_BOOKED_FLIGHT = "yearly_bookedFlight";
    public static final String COLUMN_YEARLY_BOOKED_HOTEL = "yearly_bookedHotel";
    public static final String COLUMN_YEARLY_BOOKED_ACTIVITIES = "yearly_bookedActivities";
    public static final String COLUMN_YEARLY_CANCEL_ACTIVITIES = "yearly_cancelActivities";
    String CREATE_TABLE_YEARLY_STATISTIC = "CREATE TABLE " + TABLE_YEARLY_STATISTIC + "("
            + COLUMN_YEARS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_YEARS + " YEAR, "
            + COLUMN_YEARLY_BOOKED_FLIGHT + " INT(100), "
            + COLUMN_YEARLY_BOOKED_HOTEL + " INT(100), "
            + COLUMN_YEARLY_BOOKED_ACTIVITIES + " INT(100), "
            + COLUMN_YEARLY_CANCEL_ACTIVITIES + " INT(100));";


    // cash payment
    public static final String TABLE_PAYMENT = "payment";
    String CREATE_PAYMENT_TABLE = "CREATE TABLE " + TABLE_PAYMENT + "("
            + COLUMN_PAYMENT_ID + " TEXT PRIMARY KEY, "
            + COLUMN_PAYMENT_METHOD + " TEXT,"
            + COLUMN_PAYMENT_AMOUNT + " TEXT" + ");";

    // edit information
    public static final String TABLE_USER_INFO = "user_info";
    public static final String COLUMN_IC_NUMBER = "ic_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_EMERGENCY_NAME = "emergency_contact_name";
    public static final String COLUMN_EMERGENCY_PHONE = "emergency_contact_phone";
    private static final String CREATE_TABLE_EDIT = "CREATE TABLE " + TABLE_USER_INFO + " ("
            + COLUMN_USERNAME + " TEXT PRIMARY KEY, "
            + COLUMN_IC_NUMBER + " TEXT, "
            + COLUMN_DATE_OF_BIRTH + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_PASSWORD + " TEXT, "
            + COLUMN_CONFIRM_PASSWORD + " TEXT, "
            + COLUMN_EMERGENCY_NAME + " TEXT, "
            + COLUMN_EMERGENCY_PHONE + " TEXT);";

    //popular
    public static final String TABLE_POPULAR = "popular";
    public static final String COLUMN_POPULAR_ID = "popular_id";
    public static final String COLUMN_POPULAR_NAME = "popular_name";

    private static final String DATABASE_CREATE_POPULAR = "create table "
            + TABLE_POPULAR + "(" + COLUMN_POPULAR_ID
            + " text primary key, " + COLUMN_POPULAR_NAME
            + " text not null);";

    // user profile
    public static final String TABLE_USER_PROFILE = "user_profile";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_PROFILE_PIC = "user_profile_pic";

    private static final String TABLE_USER_PROFILE_CREATE =
            "CREATE TABLE " + TABLE_USER_PROFILE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_ID + " TEXT, " +
                    COLUMN_USER_PROFILE_PIC + " TEXT);";

    // promotion
    public static final String TABLE_PROMOTION = "promotion";
    public static final String COLUMN_PROMOTION_ID = "promotion_id";
    public static final String COLUMN_PROMOTION_NAME = "promotion_name";
    public static final String COLUMN_DISCOUNT_PERCENTAGE = "discount_percentage";
    private static final String CREATE_TABLE_PROMOTION = "CREATE TABLE " + TABLE_PROMOTION + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PROMOTION_ID + " TEXT UNIQUE,"
            + COLUMN_PROMOTION_NAME + " TEXT,"
            + COLUMN_DISCOUNT_PERCENTAGE + " TEXT"
            + ")";

    // setting
    public static final String TABLE_SETTINGS = "settings";
    public static final String COLUMN_DARK_THEME = "dark_theme";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_TEMPERATURE_UNIT = "temperature_unit";
    public static final String COLUMN_COUNTRY = "country";
    private static final String CREATE_TABLE_SETTINGS = "create table "
            + TABLE_SETTINGS + "("
            + COLUMN_DARK_THEME + " text primary key not null, "
            + COLUMN_UNIT + " text not null, "
            + COLUMN_TEMPERATURE_UNIT + " text not null, "
            + COLUMN_COUNTRY + " text not null);";

    //transaction history
    public static final String TABLE_TRANSACTION_HISTORY = "transaction_history";
    public static final String COLUMN_AIRLINE = "airline";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_HOTEL = "hotel";
    public static final String COLUMN_FLIGHT_MEAL = "flight_meal";
    public static final String COLUMN_AMOUNT = "amount";

    private static final String CREATE_TABLE_TRANSACTION_HISTORY = "create table "
            + TABLE_TRANSACTION_HISTORY + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USERNAME + " text not null, "
            + COLUMN_PHONE_NUMBER + " text not null, "
            + COLUMN_AIRLINE + " text not null, "
            + COLUMN_LOCATION + " text not null, "
            + COLUMN_HOTEL + " text not null, "
            + COLUMN_FLIGHT_MEAL + " text not null, "
            + COLUMN_AMOUNT + " text not null);";

    public dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REGISTER); // register
        db.execSQL(CREATE_TABLE_SIGN_IN); // sign-in
        db.execSQL(CREATE_TABLE_FORGOT_PASSWORD); // forget-password
        db.execSQL(CREATE_TABLE_PROFILE); // profile
        db.execSQL(CREATE_TABLE_ADD_CARD); // add-card
        db.execSQL(CREATE_TABLE_FAVOURITES); // favourite
        db.execSQL(CREATE_TABLE_REVIEW); // review
        db.execSQL(CREATE_TABLE_BOOKING); // booking
        db.execSQL(CREATE_TABLE_SEATS); // seats
        db.execSQL(CREATE_TABLE_SELECT_MEAL); // select meal
        db.execSQL(CREATE_TABLE_FOOD_OPTION); // food option
        db.execSQL(CREATE_TABLE_FOOD_DETAILS); // food details
        db.execSQL(CREATE_TABLE_MY_CART); // my cart
        db.execSQL(CREATE_TABLE_CONFIRM_BOOKING); // confirm booking
        db.execSQL(CREATE_TABLE_ADMIN_LOGIN); // admin login
        db.execSQL(CREATE_TABLE_ADMIN_PASSCODE); // admin passcode
        db.execSQL(CREATE_TABLE_ADMIN_PROFILE); // admin profile
        db.execSQL(CREATE_TABLE_MONTHLY_STATISTIC); // monthly statistic
        db.execSQL(CREATE_TABLE_YEARLY_STATISTIC); // yearly statistic
        db.execSQL(CREATE_TABLE_CHOOSE_PAYMENT); // choose payment
        db.execSQL(CREATE_TABLE_CARD_PAYMENT); // card payment
        db.execSQL(CREATE_PAYMENT_TABLE); // cash payment
        db.execSQL(CREATE_TABLE_EDIT);// EDIT INFORMATION
        db.execSQL(DATABASE_CREATE_POPULAR);// popular
        db.execSQL(TABLE_USER_PROFILE_CREATE);// user profile
        db.execSQL(CREATE_TABLE_PROMOTION); // promotion
        db.execSQL(CREATE_TABLE_SETTINGS);// setting
        db.execSQL(CREATE_TABLE_TRANSACTION_HISTORY);// transaction history
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER); // register
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGN_IN); // sign in
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORGOT_PASSWORD); // forget password
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE); // profile
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_CARD); // add card
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITES); // favourite
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW); // review
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKING); // booking
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEATS); // seats
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELECT_MEAL); // select meal
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_OPTION); // food option
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_DETAILS); // food details
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_CART); // my cart
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFIRM_BOOKING); // confirm booking
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN_LOGIN); // admin login
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN_PASSCODE); // admin passcode
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN_PROFILE); // admin profile
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHLY_STATISTIC); // monthly statistic
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_YEARLY_STATISTIC); // yearly statistic
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOOSE_PAYMENT); // choose payment
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD_PAYMENT); // card payment
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT); // cash payment
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO); // EDIT INFORMATION
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POPULAR); // popular
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROFILE); // user profile
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMOTION); // promotion
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS); // setting
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION_HISTORY); // transaction history
        onCreate(db);
    }
}